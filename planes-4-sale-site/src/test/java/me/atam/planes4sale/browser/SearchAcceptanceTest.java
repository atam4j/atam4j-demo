package me.atam.planes4sale.browser;

import me.atam.atam4j.Monitor;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Monitor
public class SearchAcceptanceTest extends BrowserBasedAcceptanceTest {

    @Test
    public void canSearchForBoeings() {
        driver.get(getHostAndPort());
        WebElement boeingSearchButton = driver.findElementById("boeingSearchButton");
        boeingSearchButton.click();
        assertThat(driver.getTitle(), is("Planes 4 Sale - Search Results"));

        assertThat(driver.findElementById("plane-1").isDisplayed(), is(true));
        assertThat(getManufacturerFromPlane("plane-1"), is("Boeing"));
        //at this point - the test is cheating - we don't know that this will be here!
        assertThat(getModelFromPlane("plane-1"), is("777-31B/ER"));
        assertThat(getManufactureDateFromPlane("plane-1"), is("2015-12-01"));
        assertThat(getRegFromPlane("plane-1"), is("B-2049"));

    }


    private String getManufacturerFromPlane(String plane) {
        return gettAttributeFromPlane(plane, "manufacturer");
    }

    private String getModelFromPlane(String plane) {
        return gettAttributeFromPlane(plane, "model");
    }

    private String getManufactureDateFromPlane(String plane) {
        return gettAttributeFromPlane(plane, "manufactureDate");
    }

    private String getRegFromPlane(String plane) {
        return gettAttributeFromPlane(plane, "reg");
    }


    private String gettAttributeFromPlane(String plane, String attribute) {
        return driver.findElementByXPath("//*[@id=\"" + plane + "\"]/td[@class=\"" + attribute + "\"]").getText();
    }
}
