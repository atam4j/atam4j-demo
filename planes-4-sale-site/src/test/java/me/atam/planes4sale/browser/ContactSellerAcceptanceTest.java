package me.atam.planes4sale.browser;

import me.atam.planes4sale.BrowserBasedAcceptanceTest;
import me.atam.planes4sale.browser.SearchResultsPage.PlaneView;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static me.atam.planes4sale.browser.SearchResultsPage.ContactSellerPopup;
import static me.atam.planes4sale.browser.SearchResultsPage.load;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactSellerAcceptanceTest extends BrowserBasedAcceptanceTest {

    @Test
    public void popupLoadsAndCloses() {
        SearchResultsPage searchResultsPage = load(getHostAndPort(), "airbus", driver);
        PlaneView planeView = searchResultsPage.getPlaneView("plane-0");
        ContactSellerPopup contactSellerPopup = planeView.loadContactSellerPopup();

        WebElement messageTextBox = contactSellerPopup.getMessageTextBox();
        assertThat(messageTextBox.getAttribute("value"), is("Hi, I like the look of your plane.  Can we talk?"));

        WebElement emailAddressTextBox = contactSellerPopup.getEmailAddressTextBox();
        assertThat(emailAddressTextBox.getAttribute("value"), is("youremail@address.com"));

        WebElement phoneNumberTextBox = contactSellerPopup.getPhoneNumberTextBox();
        assertThat(phoneNumberTextBox.getAttribute("value"), is(""));

        contactSellerPopup.close();

        //checkpopup has gone
        assertThat(messageTextBox.isDisplayed(), is(false));
        assertThat(emailAddressTextBox.isDisplayed(), is(false));
        assertThat(phoneNumberTextBox.isDisplayed(), is(false));
    }
}
