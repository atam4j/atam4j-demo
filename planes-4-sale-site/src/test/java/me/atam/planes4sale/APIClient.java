package me.atam.planes4sale;

import me.atam.planes4sale.api.ContactSellerRequest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.client.Entity.entity;

public class APIClient {


    private String hostAndPort;
    Client client = ClientBuilder.newClient();

    public APIClient(String hostAndPort) {
        this.hostAndPort = hostAndPort;
    }

    public Response contactSellerAboutPlane(String id, ContactSellerRequest request) {
        WebTarget searchTarget = client.target(hostAndPort ).path("/api/public/plane/" + id + "/contactseller");
        Invocation.Builder invocationBuilder = searchTarget.request(MediaType.APPLICATION_JSON);
        return invocationBuilder.post(entity(request, MediaType.APPLICATION_JSON_TYPE));

    }

    public Response getAllEmailLeads() {
        WebTarget path = client.target(hostAndPort).path("/api/business/email-leads");
        return path.request(MediaType.APPLICATION_JSON).get();
    }

    public Response getSearchResultsFor(String manufacturer) {
        WebTarget searchTarget = client.target(hostAndPort ).path("/api/public/search").queryParam("manufacturer", manufacturer);
        Invocation.Builder invocationBuilder = searchTarget.request(MediaType.APPLICATION_JSON);
        return invocationBuilder.get();
    }
}
