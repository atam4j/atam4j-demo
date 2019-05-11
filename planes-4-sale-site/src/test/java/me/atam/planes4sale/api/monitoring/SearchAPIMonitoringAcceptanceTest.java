package me.atam.planes4sale.api.monitoring;

import me.atam.atam4j.Monitor;
import me.atam.planes4sale.APIClient;
import me.atam.planes4sale.AcceptanceTest;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@Monitor
/*
    Relies on at least one Boeing and one Airbus
 */
public class SearchAPIMonitoringAcceptanceTest extends AcceptanceTest {

    private APIClient apiClient = new APIClient(getHostAndPort());

    @Test
    public void canSearchForBoeings() {
        Response apiResponse = apiClient.getSearchResultsFor("boeing");

        assertThat(apiResponse.getStatus(), is(200));
        List<Map<String,Object>> planes = apiResponse.readEntity(List.class);
        assertThat(planes.size(), is(greaterThan(0)));
        Map<String, Object> plane = planes.get(0);
        assertThat(plane.get("manufacturer"), is("Boeing"));
        checkHasAttributeWithNonNullStringValue(plane ,"manufacturer");
        checkHasAttributeWithNonNullStringValue(plane ,"id");
        checkHasAttributeWithNonNullStringValue(plane ,"imageId");
        checkHasAttributeWithNonNullStringValue(plane ,"reg");
        checkHasAttributeWithNonNullStringValue(plane ,"manufactureDate");
    }

    @Test
    public void canSearchForAirbus() {
        Response apiResponse = apiClient.getSearchResultsFor("airbus");

        assertThat(apiResponse.getStatus(), is(200));
        List<Map<String,Object>> planes = apiResponse.readEntity(List.class);
        assertThat(planes.size(), is(greaterThan(0)));
        Map<String, Object> plane = planes.get(0);
        assertThat(plane.get("manufacturer"), is("Airbus"));
        checkHasAttributeWithNonNullStringValue(plane ,"manufacturer");
        checkHasAttributeWithNonNullStringValue(plane ,"id");
        checkHasAttributeWithNonNullStringValue(plane ,"imageId");
        checkHasAttributeWithNonNullStringValue(plane ,"reg");
        checkHasAttributeWithNonNullStringValue(plane ,"manufactureDate");
    }


    private void checkHasAttributeWithNonNullStringValue(Map<String, Object> plane, String attribute) {
        assertThat(plane.get(attribute), is(notNullValue()));
        assertThat(plane.get(attribute), instanceOf(String.class));
        assertThat(((String) plane.get(attribute)).length(), greaterThan(1 ));
    }
}
