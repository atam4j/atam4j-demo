package me.atam.planes4sale.browser;

import org.junit.Test;
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
        waitForElementToBeVisible(driver, "plane-1");
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


    }

}
