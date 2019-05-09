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

import static javax.ws.rs.client.Entity.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactSellerAPIAcceptanceTest extends AcceptanceTest {



    @Test
    public void contactingSellerSendsAnEMail() {

        Client client = ClientBuilder.newClient();
        WebTarget searchTarget = client.target(getHostAndPort() ).path("/api/plane/" + H2StubbedDatabase.KNOWN_PLANE_ID + "/contactseller");

        Invocation.Builder invocationBuilder = searchTarget.request(MediaType.APPLICATION_JSON);

        ContactSellerRequest request = new ContactSellerRequest(
                H2StubbedDatabase.KNOWN_PLANE_ID,
                "THE MESSAGE!",
                "buyer@buyer.com",
                "555 1234");

        Response response = invocationBuilder.post(entity(request, MediaType.APPLICATION_JSON_TYPE));
        assertThat(response.getStatus(), is(200));


//




    }

}
