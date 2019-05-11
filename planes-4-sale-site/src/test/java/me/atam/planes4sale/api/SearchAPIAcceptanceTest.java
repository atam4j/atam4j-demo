package me.atam.planes4sale.api;

import me.atam.atam4j.Monitor;
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

@Monitor
public class SearchAPIAcceptanceTest extends AcceptanceTest {
    //This test is not good enough for build time, but great for monitoring
    @Test
    public void canSearchForBoeings() {

        Client client = ClientBuilder.newClient();
        WebTarget searchTarget = client.target(getHostAndPort() ).path("/api/public/search").queryParam("manufacturer", "boeing");

        Invocation.Builder invocationBuilder = searchTarget.request(MediaType.APPLICATION_JSON);

        Response apiResponse = invocationBuilder.get();
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

        Client client = ClientBuilder.newClient();
        WebTarget searchTarget = client.target(getHostAndPort() ).path("/api/public/search").queryParam("manufacturer", "boeing");

        Invocation.Builder invocationBuilder = searchTarget.request(MediaType.APPLICATION_JSON);

        Response apiResponse = invocationBuilder.get();
        assertThat(apiResponse.getStatus(), is(200));

        List<Map<String,Object>> planes = apiResponse.readEntity(List.class);
        assertThat(planes.size(), is(greaterThan(0)));

        Map<String,Object> plane = planes.get(0);
        assertThat(plane.containsKey("sellerEmail"), is(false));
    }

    @Test
    public void canSearchForAirbus() {
        //This test is not good enough for build time, but great for monitoring
        Client client = ClientBuilder.newClient();
        WebTarget searchTarget = client.target(getHostAndPort() ).path("/api/public/search").queryParam("manufacturer", "airbus");

        Invocation.Builder invocationBuilder = searchTarget.request(MediaType.APPLICATION_JSON);

        Response apiResponse = invocationBuilder.get();
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
