package me.atam.planes4sale.api.monitoring;

import me.atam.planes4sale.APIClient;
import me.atam.planes4sale.AcceptanceTest;
import me.atam.planes4sale.api.ContactSellerRequest;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static me.atam.planes4sale.H2StubbedDatabase.KNOWN_HIDDEN_PLANE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactSellerAPIMonitorAcceptanceTest extends AcceptanceTest {

    APIClient apiClient = new APIClient(getHostAndPort());

    @Test
    public void contactingSellerSendsAnEmail() {

        String uuidForEmail = UUID.randomUUID().toString();
        String message = "Here is my messaue!" + uuidForEmail;
        ContactSellerRequest request = new ContactSellerRequest(
                message,
                "buyer@buyer.com",
                "555 1234");

        Response response = apiClient.contactSellerAboutPlane(KNOWN_HIDDEN_PLANE.getId(), request);
        assertThat(response.getStatus(), is(200));

        Response apiResponse = apiClient.getAllEmailLeads();
        assertThat(apiResponse.getStatus(), is(200));
        List<Map<String,Object>> emailLeads = apiResponse.readEntity(List.class);
        Optional<Map<String, Object>> email = emailLeads.stream()
                .filter(lead -> ((String) lead.get("message")).contains(uuidForEmail))
                .findFirst();

        assertThat(email.isPresent(), is(true));
        assertThat(email.get().get("planeId"), is(KNOWN_HIDDEN_PLANE.getId()));
        assertThat(email.get().get("message"), is(message));
        assertThat(email.get().get("sellerEmail"), is(KNOWN_HIDDEN_PLANE.getSellerEmail()));
        assertThat(email.get().get("buyerEmail"), is("buyer@buyer.com"));
        assertThat(email.get().get("buyerNumber"), is("555 1234"));
    }

}
