package me.atam.planes4sale;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;

public class SearchAcceptanceTest extends BrowserBasedAcceptanceTest {

    @Test
    public void canSearchForBoeings() {
        driver.get(getHomePageAddress());
        WebElement boeingSearchButton = driver.findElementById("boeingSearchButton");
        boeingSearchButton.click();
        assertThat(driver.getTitle(), CoreMatchers.is("Planes 4 Sale - Search Results"));
    }
}
