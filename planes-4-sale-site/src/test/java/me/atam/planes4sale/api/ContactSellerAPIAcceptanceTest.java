package me.atam.planes4sale.api;

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
import java.util.Optional;
import java.util.UUID;

import static javax.ws.rs.client.Entity.entity;
import static me.atam.planes4sale.H2StubbedDatabase.KNOWN_BOEING_1;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactSellerAPIAcceptanceTest extends AcceptanceTest {

    @Test
    public void contactingSellerSendsAnEMail() {

        String uuidForEmail = UUID.randomUUID().toString();
        String message = "Here is my messaue!" + uuidForEmail;

        Client client = ClientBuilder.newClient();
        WebTarget searchTarget = client.target(getHostAndPort() ).path("/api/public/plane/" + KNOWN_BOEING_1.getId() + "/contactseller");

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
        assertThat(email.get().get("planeId"), is(KNOWN_BOEING_1.getId()));
        assertThat(email.get().get("message"), is(message));
        assertThat(email.get().get("sellerEmail"), is(KNOWN_BOEING_1.getSellerEmail()));
        assertThat(email.get().get("buyerEmail"), is("buyer@buyer.com"));
        assertThat(email.get().get("buyerNumber"), is("555 1234"));
    }

}
