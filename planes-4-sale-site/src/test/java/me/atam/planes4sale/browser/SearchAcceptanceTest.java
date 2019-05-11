package me.atam.planes4sale.browser;

import me.atam.atam4j.Monitor;
import me.atam.planes4sale.BrowserBasedAcceptanceTest;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchAcceptanceTest extends BrowserBasedAcceptanceTest {

    @Test
    public void canSearchForBoeings() {
        SearchResultsPage searchResultsPage = SearchResultsPage.load(getHostAndPort(), "boeing", driver);

        SearchResultsPage.PlaneView planeView = searchResultsPage.getPlaneView("plane-0");
        assertThat(planeView.isDisplayed(), is(true));
        assertThat(planeView.getManufacturer(), is("Boeing"));
        assertThat(planeView.getModel(), is("777-31B/ER"));
        assertThat(planeView.getManufactureDate(), is("2015-12-01"));
        assertThat(planeView.getReg(), is("B-2049"));
    }
}
