package me.atam.planes4sale.api;

import me.atam.atam4j.Monitor;
import me.atam.planes4sale.APIClient;
import me.atam.planes4sale.AcceptanceTest;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

public class SearchAPIAcceptanceTest extends AcceptanceTest {

    private APIClient apiClient = new APIClient(getHostAndPort());

    //This test is not good enough for build time, but great for monitoring
    @Test
    public void canSearchForBoeings() {

        Response apiResponse = apiClient.getSearchResultsFor("boeing");

        assertThat(apiResponse.getStatus(), is(200));
        List<Map<String,Object>> planes = apiResponse.readEntity(List.class);
        assertThat(planes.size(), is(greaterThan(0)));

        Map<String,Object> plane = planes.get(0);
        assertThat(plane.get("manufacturer"), is("Boeing"));
        checkHasAttributeWithSNonNulltringValue(plane, "id");
        checkHasAttributeWithSNonNulltringValue(plane, "manufacturer");
        checkHasAttributeWithSNonNulltringValue(plane, "imageId");
        checkHasAttributeWithSNonNulltringValue(plane, "reg");

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

    @Test
    public void canSearchForAirbus() {
        Response apiResponse = apiClient.getSearchResultsFor("airbus");
        assertThat(apiResponse.getStatus(), is(200));

        List<Map<String,Object>> planes = apiResponse.readEntity(List.class);
        assertThat(planes.size(), is(greaterThan(0)));
        Map<String,Object> plane = planes.get(0);

        assertThat(plane.get("manufacturer"), is("Airbus"));
        checkHasAttributeWithSNonNulltringValue(plane, "id");
        checkHasAttributeWithSNonNulltringValue(plane, "manufacturer");
        checkHasAttributeWithSNonNulltringValue(plane, "imageId");
        checkHasAttributeWithSNonNulltringValue(plane, "reg");
    }



    private void checkHasAttributeWithSNonNulltringValue(Map<String, Object> plane, String attribute) {
        assertThat(plane.get(attribute), is(notNullValue()));
        assertThat(plane.get(attribute), instanceOf(String.class));
        assertThat(((String) plane.get(attribute)).length(), greaterThan(1 ));
    }
}
