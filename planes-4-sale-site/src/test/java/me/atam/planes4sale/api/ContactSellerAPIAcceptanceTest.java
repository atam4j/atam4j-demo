package me.atam.planes4sale.api;

import me.atam.planes4sale.AcceptanceTest;
import me.atam.planes4sale.H2StubbedDatabase;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static javax.ws.rs.client.Entity.*;
import static me.atam.planes4sale.H2StubbedDatabase.KNOWN_PLANE_ID;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactSellerAPIAcceptanceTest extends AcceptanceTest {



    @Test
    public void contactingSellerSendsAnEMail() {

        String uuidForEmail = UUID.randomUUID().toString();

        Client client = ClientBuilder.newClient();
        WebTarget searchTarget = client.target(getHostAndPort() ).path("/api/public/plane/" + KNOWN_PLANE_ID + "/contactseller");

        Invocation.Builder invocationBuilder = searchTarget.request(MediaType.APPLICATION_JSON);

        ContactSellerRequest request = new ContactSellerRequest(
                KNOWN_PLANE_ID,
                "Here is my messaue!" + uuidForEmail,
                "buyer@buyer.com",
                "555 1234");

        Response response = invocationBuilder.post(entity(request, MediaType.APPLICATION_JSON_TYPE));
        assertThat(response.getStatus(), is(200));



        searchTarget = client.target(getHostAndPort() ).path("/api/business/email-leads");

        invocationBuilder = searchTarget.request(MediaType.APPLICATION_JSON);

        Response apiResponse = invocationBuilder.get();
        assertThat(apiResponse.getStatus(), is(200));

        List<Map<String,Object>> emailLeads = apiResponse.readEntity(List.class);

        Optional<Map<String, Object>> email = emailLeads.stream()
                .filter(lead -> lead.get("planeId").equals(KNOWN_PLANE_ID))
                .filter(lead -> ((String) lead.get("message")).contains(uuidForEmail))
                .findFirst();

        assertThat(email.isPresent(), is(true));




//





    }

}
