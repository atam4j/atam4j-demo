package me.atam.planes4sale.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/public/plane")
public class ContactSellerResource {

    @Path("/{planeId}/contactseller")
    @POST
    public Response contactSeller(@PathParam("planeId") String planeId){
        System.out.println("Contact seller about " + planeId);
        return Response.ok().build();
    }


}
