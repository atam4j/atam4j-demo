package me.atam.planes4sale;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;

public class HomepageAcceptanceTest extends BrowserBasedAcceptanceTest {

    @Test
    public void homepageHasCorrectTitleAndButtons() {

        driver.get(getHomePageAddress());

        assertThat(driver.getTitle(), CoreMatchers.is("Planes 4 Sale"));
        WebElement boeingSearchButton = driver.findElementById("boeingSearchButton");
        assertThat(boeingSearchButton.getText(), CoreMatchers.is("Search For Boeing Planes"));

        WebElement airbusSearchButton = driver.findElementById("airbusSearchButton");
        assertThat(airbusSearchButton.getText(), CoreMatchers.is("Search For Airbus Planes"));
    }




}
