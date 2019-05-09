package me.atam.planes4sale.api;

import me.atam.planes4sale.api.business.EmailLead;
import me.atam.planes4sale.api.business.JDBIEmailLeadsService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/public/plane")
public class ContactSellerResource {


    private JDBIEmailLeadsService emailLeadsService;

    public ContactSellerResource(JDBIEmailLeadsService emailLeadsService) {
        this.emailLeadsService = emailLeadsService;
    }

    @Path("/{planeId}/contactseller")
    @POST
    public Response contactSeller(@PathParam("planeId") String planeId){

        emailLeadsService.addEmailLead(new EmailLead("dsfasdfasdf", "asfsdfdsf", "asdfasdf", "asdfasdasdf"));
        System.out.println("Contact seller about " + planeId);
        return Response.ok().build();
    }


}
