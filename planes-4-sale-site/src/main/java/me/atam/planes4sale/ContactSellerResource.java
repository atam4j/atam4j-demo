package me.atam.planes4sale;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/plane")
public class ContactSellerResource {

    @Path("/{planeId}/contactseller")
    @POST
    public Response contactSeller(@PathParam("planeId") String planeId){
        System.out.println("Contact seller about " + planeId);
        return Response.ok().build();
    }


    @GET
    @Path("/stuff")
    public Response stuff(){
        return Response.ok().build();
    }

}
