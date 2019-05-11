package me.atam.planes4sale.browser.monitor;

import me.atam.atam4j.Monitor;
import me.atam.planes4sale.BrowserBasedAcceptanceTest;
import me.atam.planes4sale.browser.SearchResultsPage;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyString;

@Monitor
public class SearchAcceptanceMonitorTest extends BrowserBasedAcceptanceTest {

    @Test
    public void canSearchForBoeings() {
        SearchResultsPage searchResultsPage = SearchResultsPage.load(getHostAndPort(), "boeing", driver);
        SearchResultsPage.PlaneView planeView = searchResultsPage.getPlaneView("plane-0");
        assertThat(planeView.isDisplayed(), is(true));
        assertThat(planeView.getManufacturer(), is("Boeing"));
        assertThat(planeView.getModel(), containsString("7")); //I think every Boeing model has a 7 in it
        assertThat(planeView.getManufactureDate(), is(not((isEmptyString()))));
        assertThat(planeView.getReg(), is(not((isEmptyString()))));
    }

}
