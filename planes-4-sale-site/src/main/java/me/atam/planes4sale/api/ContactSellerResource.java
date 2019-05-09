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
    private EmailSendingService emailSendingService;

    public ContactSellerResource(JDBIEmailLeadsService emailLeadsService, EmailSendingService emailSendingService) {
        this.emailLeadsService = emailLeadsService;
        this.emailSendingService = emailSendingService;
    }

    @Path("/{planeId}/contactseller")
    @POST
    public Response contactSeller(@PathParam("planeId") String planeId, ContactSellerRequest request){

        //TODO get seller mail
        EmailLead emailLead = request.toEmailLead(planeId, "sellerMail");
        emailSendingService.sendEmail(emailLead);
        emailLeadsService.addEmailLead(emailLead);
        return Response.ok().build();
    }


}
