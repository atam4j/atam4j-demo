package me.atam.planes4sale;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class HomepageAcceptanceTest extends BrowserBasedAcceptanceTest {

    @Test
    public void homepageHasCorrectTitleAndHeading() {
        driver.get("http://localhost:8080/");

        assertThat(driver.getTitle(), CoreMatchers.is("Planes 4 Sale"));
    }
}
