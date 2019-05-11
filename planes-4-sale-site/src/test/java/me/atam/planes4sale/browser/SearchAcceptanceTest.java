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
        checkPlaneAsExpected(searchResultsPage.getPlaneView("plane-0"), KNOWN_BOEING_2);
        checkPlaneAsExpected(searchResultsPage.getPlaneView("plane-1"), KNOWN_BOEING_1);
    }

    @Test
    public void canSearchForAirbus() {
        SearchResultsPage searchResultsPage = SearchResultsPage.load(getHostAndPort(), "airbus", driver);
        checkPlaneAsExpected(searchResultsPage.getPlaneView("plane-0"), KNOWN_AIRBUS);
    }

    protected void checkPlaneAsExpected(SearchResultsPage.PlaneView planeView, Plane plane) {
        assertThat(planeView.isDisplayed(), is(true));
        assertThat(planeView.getManufacturer(), is(plane.getManufacturer()));
        assertThat(planeView.getModel(), is(plane.getModel()));
        assertThat(planeView.getManufactureDate(), is(plane.getManufactureDate().format(DateTimeFormatter.ISO_DATE)));
        assertThat(planeView.getReg(), is(plane.getReg()));
    }
}
