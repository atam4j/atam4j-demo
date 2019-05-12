package me.atam.planes4sale.browser;

import me.atam.planes4sale.BrowserBasedAcceptanceTest;
import me.atam.planes4sale.Plane;
import org.junit.Test;

import java.time.format.DateTimeFormatter;

import static me.atam.planes4sale.H2StubbedDatabase.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
/*
    Relies on "KNOWN" planes in the database
 */
public class SearchAcceptanceTest extends BrowserBasedAcceptanceTest {

    @Test
    public void canSearchForBoeings() {
        SearchResultsPage searchResultsPage = SearchResultsPage.load(getHostAndPort(), "boeing", driver);
        checkPlaneAsExpected(searchResultsPage.getPlaneView("plane-0"), KNOWN_BOEING_2_IN_STUB_DB);
        checkPlaneAsExpected(searchResultsPage.getPlaneView("plane-1"), KNOWN_BOEING_1_IN_STUB_DB);
    }

    @Test
    public void canSearchForAirbus() {
        SearchResultsPage searchResultsPage = SearchResultsPage.load(getHostAndPort(), "airbus", driver);
        checkPlaneAsExpected(searchResultsPage.getPlaneView("plane-0"), KNOWN_AIRBUS_IN_STUB_DB);
    }

    protected void checkPlaneAsExpected(SearchResultsPage.PlaneView planeOnThePage, Plane expectedPlane) {
        assertThat(planeOnThePage.isDisplayed(), is(true));
        assertThat(planeOnThePage.getManufacturer(), is(expectedPlane.getManufacturer()));
        assertThat(planeOnThePage.getModel(), is(expectedPlane.getModel()));
        assertThat(planeOnThePage.getManufactureDate(), is(expectedPlane.getManufactureDate().format(DateTimeFormatter.ISO_DATE)));
        assertThat(planeOnThePage.getReg(), is(expectedPlane.getReg()));
    }
}
