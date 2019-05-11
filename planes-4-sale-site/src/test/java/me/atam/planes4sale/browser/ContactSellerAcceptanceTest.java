package me.atam.planes4sale.browser;

import me.atam.planes4sale.BrowserBasedAcceptanceTest;
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
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactSellerAcceptanceTest extends BrowserBasedAcceptanceTest {

    @Test
    public void popupLoadsAndCloses() throws IOException {
        driver.get(getHostAndPort() + "/search?manufacturer=airbus");
        waitForElementToBeVisible("plane-0");
        driver.findElementById("contactSellerButton").click();
        waitForPopupToFadeInOrOut();

        WebElement messageTextBox = driver.findElementById("message");
        assertThat(messageTextBox.getAttribute("value"), is("Hi, I like the look of your plane.  Can we talk?"));

        WebElement emailAddressTextBox = driver.findElementById("emailAddress");
        assertThat(emailAddressTextBox.getAttribute("value"), is("youremail@address.com"));

        WebElement phoneNumberTextBox = driver.findElementById("phoneNumber");
        assertThat(phoneNumberTextBox.getAttribute("value"), is(""));

        waitForElementToBeVisible("closePopup");
        WebElement closePopupButton = driver.findElementById("closePopup");

        closePopupButton.click();

        waitForPopupToFadeInOrOut();

        //checkpopup has gone
        assertThat(messageTextBox.isDisplayed(), is(false));
        assertThat(emailAddressTextBox.isDisplayed(), is(false));
        assertThat(closePopupButton.isDisplayed(), is(false));
        assertThat(phoneNumberTextBox.isDisplayed(), is(false));
    }



    @Test
    public void canContactSellerAndEmailLeadAvailable() throws IOException {
        String uuid = UUID.randomUUID().toString();
        String message = " This message has a UUID: " + uuid;
        String buyerEmailAddress = "phill.barber@buyer.com";
        String buyerPhoneNumber = "01717171717";

        //load search results
        driver.get(getHostAndPort() + "/search?manufacturer=" + KNOWN_AIRBUS.getManufacturer());
        waitForElementToBeVisible("plane-0");
        driver.findElementById("contactSellerButton").click();
        waitForPopupToFadeInOrOut();

        WebElement messageTextBox = driver.findElementById("message");
        messageTextBox.clear();

        messageTextBox.sendKeys(message);
        WebElement emailAddressTextBox = driver.findElementById("emailAddress");
        emailAddressTextBox.clear();

        emailAddressTextBox.sendKeys(buyerEmailAddress);
        WebElement phoneNumberTextBox = driver.findElementById("phoneNumber");
        phoneNumberTextBox.clear();

        phoneNumberTextBox.sendKeys(buyerPhoneNumber);
        WebElement sendMessage = driver.findElementById("sendMessage");

        sendMessage.click();

        waitForPopupToFadeInOrOut();

        //checkpopup has gone
        assertThat(messageTextBox.isDisplayed(), is(false));
        assertThat(emailAddressTextBox.isDisplayed(), is(false));
        assertThat(sendMessage.isDisplayed(), is(false));
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
        assertThat(email.get().get("planeId"), is(KNOWN_AIRBUS.getId()));
        assertThat(email.get().get("message"), is(message));
        assertThat(email.get().get("sellerEmail"), is(KNOWN_AIRBUS.getSellerEmail()));
        assertThat(email.get().get("buyerEmail"), is(buyerEmailAddress));
        assertThat(email.get().get("buyerNumber"), is(buyerPhoneNumber));

    }







    protected void waitForPopupToFadeInOrOut() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
