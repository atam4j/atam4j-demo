package me.atam.planes4sale.browser.monitor;

import me.atam.planes4sale.BrowserBasedAcceptanceTest;
import me.atam.planes4sale.browser.SearchResultsPage;
import me.atam.planes4sale.browser.SearchResultsPage.PlaneView;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static me.atam.planes4sale.H2StubbedDatabase.KNOWN_AIRBUS;
import static me.atam.planes4sale.H2StubbedDatabase.KNOWN_HIDDEN_PLANE;
import static me.atam.planes4sale.browser.SearchResultsPage.ContactSellerPopup;
import static me.atam.planes4sale.browser.SearchResultsPage.load;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactSellerMonitorAcceptanceTest extends BrowserBasedAcceptanceTest {


    @Test
    public void canContactSellerAndEmailLeadAvailable() {
        String uuid = UUID.randomUUID().toString();
        String message = " This message has a UUID: " + uuid;
        String buyerEmailAddress = "phill.barber@buyer.com";
        String buyerPhoneNumber = "01717171717";

        SearchResultsPage searchResultsPage = load(getHostAndPort(), KNOWN_HIDDEN_PLANE.getManufacturer(), driver);
        PlaneView planeView = searchResultsPage.getPlaneView("plane-0");
        ContactSellerPopup contactSellerPopup = planeView.loadContactSellerPopup();

        WebElement messageTextBox = contactSellerPopup.getMessageTextBox();
        messageTextBox.clear();
        messageTextBox.sendKeys(message);

        WebElement emailAddressTextBox = contactSellerPopup.getEmailAddressTextBox();
        emailAddressTextBox.clear();
        emailAddressTextBox.sendKeys(buyerEmailAddress);

        WebElement phoneNumberTextBox = contactSellerPopup.getPhoneNumberTextBox();
        phoneNumberTextBox.clear();
        phoneNumberTextBox.sendKeys(buyerPhoneNumber);

        contactSellerPopup.sendMessage();

        //checkpopup has gone
        assertThat(messageTextBox.isDisplayed(), is(false));
        assertThat(emailAddressTextBox.isDisplayed(), is(false));
        assertThat(phoneNumberTextBox.isDisplayed(), is(false));



        Client client = ClientBuilder.newClient();
        WebTarget searchTarget = client.target(getHostAndPort() ).path("/api/business/email-leads");
        Invocation.Builder invocationBuilder = searchTarget.request(MediaType.APPLICATION_JSON);
        Response apiResponse = invocationBuilder.get();
        assertThat(apiResponse.getStatus(), is(200));
        List<Map<String,Object>> emailLeads = apiResponse.readEntity(List.class);
        Optional<Map<String, Object>> email = emailLeads.stream()
                .filter(lead -> ((String) lead.get("message")).contains(uuid))
                .findFirst();

        assertThat(email.isPresent(), is(true));
        assertThat(email.get().get("planeId"), is(KNOWN_HIDDEN_PLANE.getId()));
        assertThat(email.get().get("message"), is(message));
        assertThat(email.get().get("sellerEmail"), is(KNOWN_HIDDEN_PLANE.getSellerEmail()));
        assertThat(email.get().get("buyerEmail"), is(buyerEmailAddress));
        assertThat(email.get().get("buyerNumber"), is(buyerPhoneNumber));
    }
}
