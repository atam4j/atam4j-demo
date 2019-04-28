package me.atam.planes4sale;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class HomepageAcceptanceTest extends BrowserBasedAcceptanceTest {

    @Test
    public void homepageHasCorrectTitleAndHeading() {
        if (TestRunMode.getMode() == TestRunMode.Mode.BUILD) {
            driver.get("http://localhost:8080/");
        } else {
            driver.get("http://planes-4-sale-site:8080/");
        }




        assertThat(driver.getTitle(), CoreMatchers.is("Planes 4 Sale"));
    }
}
