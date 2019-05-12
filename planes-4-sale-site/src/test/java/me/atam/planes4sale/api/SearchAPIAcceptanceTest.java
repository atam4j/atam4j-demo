package me.atam.planes4sale.api;

import me.atam.planes4sale.APIClient;
import me.atam.planes4sale.AcceptanceTest;
import me.atam.planes4sale.Plane;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

import static me.atam.planes4sale.H2StubbedDatabase.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
/*
    Relies on "KNOWN" planes in the database
 */
public class SearchAPIAcceptanceTest extends AcceptanceTest {

    private APIClient apiClient = new APIClient(getHostAndPort());

    @Test
    public void canSearchForBoeings() {
        Response apiResponse = apiClient.getSearchResultsFor("boeing");

        assertThat(apiResponse.getStatus(), is(200));
        List<Map<String,Object>> planes = apiResponse.readEntity(List.class);
        assertThat(planes.size(), is(2));
        //returned in exact order - newest planes first!
        checkPlaneMatchesExpectedPlane(KNOWN_BOEING_2_IN_STUB_DB, planes.get(0));
        checkPlaneMatchesExpectedPlane(KNOWN_BOEING_1_IN_STUB_DB, planes.get(1));

    }

    @Test
    public void canSearchForAirbus() {
        Response apiResponse = apiClient.getSearchResultsFor("airbus");

        assertThat(apiResponse.getStatus(), is(200));
        List<Map<String,Object>> planes = apiResponse.readEntity(List.class);
        assertThat(planes.size(), is(greaterThan(0)));
        checkPlaneMatchesExpectedPlane(KNOWN_AIRBUS_IN_STUB_DB, planes.get(0));
    }


    @Test
    public void searchDoesNotExposeSellerEmailAddress() {
        Response apiResponse = apiClient.getSearchResultsFor("boeing");

        assertThat(apiResponse.getStatus(), is(200));
        List<Map<String,Object>> planes = apiResponse.readEntity(List.class);
        assertThat(planes.size(), is(greaterThan(0)));
        Map<String,Object> plane = planes.get(0);
        assertThat(plane.containsKey("sellerEmail"), is(false));
    }




    private void checkPlaneMatchesExpectedPlane(Plane expectedPlane, Map<String, Object> plane) {
        assertThat(plane.get("manufacturer"), is(expectedPlane.getManufacturer()));
        assertThat(plane.get("id"), is(expectedPlane.getId()));
        assertThat(plane.get("imageId"), is(expectedPlane.getImageId()));
        assertThat(plane.get("reg"), is(expectedPlane.getReg()));
    }
}
