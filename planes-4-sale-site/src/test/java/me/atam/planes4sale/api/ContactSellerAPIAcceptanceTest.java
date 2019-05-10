package me.atam.planes4sale.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.atam.planes4sale.AcceptanceTest;
import me.atam.planes4sale.H2StubbedDatabase;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.*;

import static javax.ws.rs.client.Entity.*;
import static me.atam.planes4sale.H2StubbedDatabase.KNOWN_PLANE_ID;
import static me.atam.planes4sale.H2StubbedDatabase.KNOWN_PLANE_SELLER_EMAIL_ADDRESS;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactSellerAPIAcceptanceTest extends AcceptanceTest {

    @Test
    public void contactingSellerSendsAnEMail() {

        String uuidForEmail = UUID.randomUUID().toString();
        String message = "Here is my messaue!" + uuidForEmail;

        Client client = ClientBuilder.newClient();
        WebTarget searchTarget = client.target(getHostAndPort() ).path("/api/public/plane/" + KNOWN_PLANE_ID + "/contactseller");

        Invocation.Builder invocationBuilder = searchTarget.request(MediaType.APPLICATION_JSON);

        ContactSellerRequest request = new ContactSellerRequest(
                message,
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
                .filter(lead -> ((String) lead.get("message")).contains(uuidForEmail))
                .findFirst();

        assertThat(email.isPresent(), is(true));
        assertThat(email.get().get("planeId"), is(KNOWN_PLANE_ID));
        assertThat(email.get().get("message"), is(message));
        assertThat(email.get().get("sellerEmail"), is(KNOWN_PLANE_SELLER_EMAIL_ADDRESS));
        assertThat(email.get().get("buyerEmail"), is("buyer@buyer.com"));
        assertThat(email.get().get("buyerNumber"), is("555 1234"));
    }

}
