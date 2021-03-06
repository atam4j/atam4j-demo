package me.atam.planes4sale.api;

import me.atam.planes4sale.JDBIPlaneService;
import me.atam.planes4sale.api.business.EmailLead;
import me.atam.planes4sale.api.business.JDBIEmailLeadsService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/public/plane")
public class ContactSellerResource {


    private final JDBIPlaneService planeService;
    private JDBIEmailLeadsService emailLeadsService;
    private EmailSendingService emailSendingService;

    public ContactSellerResource(JDBIEmailLeadsService emailLeadsService, EmailSendingService emailSendingService, JDBIPlaneService planeService) {
        this.emailLeadsService = emailLeadsService;
        this.emailSendingService = emailSendingService;
        this.planeService = planeService;
    }

    @Path("/{planeId}/contactseller")
    @POST
    public Response contactSeller(@PathParam("planeId") String planeId, ContactSellerRequest request){
        EmailLead emailLead = request.toEmailLead(planeId, planeService.getPlaneSellersEmail(planeId));
        emailSendingService.sendEmail(emailLead);
        emailLeadsService.addEmailLead(emailLead);
        return Response.ok().build();
    }


}
