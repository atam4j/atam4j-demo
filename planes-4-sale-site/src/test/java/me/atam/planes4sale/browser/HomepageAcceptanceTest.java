package me.atam.planes4sale.browser;

import me.atam.atam4j.Monitor;
import me.atam.planes4sale.BrowserBasedAcceptanceTest;
import me.atam.planes4sale.HomePage;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;

@Monitor
public class HomepageAcceptanceTest extends BrowserBasedAcceptanceTest {

    @Test
    public void homepageLoadsAndHasButtons() {
        //this has EVENT issues
        HomePage homePage = HomePage.load(driver, getHostAndPort());

        assertThat(homePage.getTitle(), CoreMatchers.is("Planes 4 Sale"));
        WebElement boeingSearchButton = homePage.getBoeingSearchButton();
        assertThat(boeingSearchButton.getText(), CoreMatchers.is("Search For Boeing Planes"));

        WebElement airbusSearchButton = homePage.getAirbusSearchButton();
        assertThat(airbusSearchButton.getText(), CoreMatchers.is("Search For Airbus Planes"));
    }
}
