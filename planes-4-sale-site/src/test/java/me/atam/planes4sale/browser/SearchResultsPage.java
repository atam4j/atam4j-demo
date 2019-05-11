package me.atam.planes4sale.browser;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import static me.atam.planes4sale.BrowserBasedAcceptanceTest.waitForElementToBeVisible;

public class SearchResultsPage {
    final RemoteWebDriver driver;

    public static SearchResultsPage load(String hostAndPort, String manufacturer, RemoteWebDriver driver) {
        return new SearchResultsPage(hostAndPort, manufacturer, driver);
    }

    public SearchResultsPage(String hostAndPort, String manufacturer, RemoteWebDriver driver) {
        this.driver = driver;
        driver.get(hostAndPort + "/search?manufacturer=" + manufacturer);
        waitForElementToBeVisible(driver, "plane-0");
    }

    public PlaneView getPlaneView(String s) {
        return new PlaneView(s, driver);
    }

    public static class PlaneView {
        String planeId;
        RemoteWebDriver driver;

        public PlaneView(String planeId, RemoteWebDriver driver) {
            this.planeId = planeId;
            this.driver = driver;
        }


        public boolean isDisplayed() {
            return driver.findElementById("plane-1").isDisplayed();
        }


        public String getManufacturer() {
            return gettAttributeFromPlane("manufacturer");
        }

        public String getModel() {
            return gettAttributeFromPlane("model");
        }

        public String getManufactureDate() {
            return gettAttributeFromPlane("manufactureDate");
        }

        public String getReg() {
            return gettAttributeFromPlane("reg");
        }


        private String gettAttributeFromPlane(String attribute) {
            return driver.findElementByXPath("//*[@id=\"" + planeId + "\"]/td[@class=\"" + attribute + "\"]").getText();
        }


        public ContactSellerPopup loadContactSellerPopup() {
            driver.findElementById("contactSellerButton").click();
            waitForPopupToFadeInOrOut();
            return new ContactSellerPopup(driver);
        }


    }

    public static class ContactSellerPopup{
        RemoteWebDriver driver;

        public ContactSellerPopup(RemoteWebDriver driver) {
            this.driver = driver;
        }

        public WebElement getMessageTextBox() {
            return driver.findElementById("message");
        }

        public WebElement getEmailAddressTextBox() {
            return driver.findElementById("emailAddress");
        }

        public WebElement getPhoneNumberTextBox() {
            return driver.findElementById("phoneNumber");
        }

        public void close() {
            WebElement closePopupButton = driver.findElementById("closePopup");
            closePopupButton.click();
            waitForPopupToFadeInOrOut();
        }

        public void sendMessage() {
            driver.findElementById("sendMessage").click();
            waitForPopupToFadeInOrOut();
        }
    }

    private static void waitForPopupToFadeInOrOut() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
