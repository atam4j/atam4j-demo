package me.atam.planes4sale.browser;

import me.atam.planes4sale.BrowserBasedAcceptanceTest;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;

import static org.eclipse.jetty.util.IO.copyFile;
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

        waitForElementToBeVisible("plane-0");

        assertThat(messageTextBox.isDisplayed(), is(false));
        assertThat(emailAddressTextBox.isDisplayed(), is(false));
        assertThat(closePopupButton.isDisplayed(), is(false));
        assertThat(phoneNumberTextBox.isDisplayed(), is(false));

//        messageTextBox.sendKeys("A nice new message");
//        emailAddressTextBox.sendKeys("thebuyer@buyer.com");
//        phoneNumberTextBox.sendKeys("07777777771");


    }

    protected void waitForPopupToFadeInOrOut() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
