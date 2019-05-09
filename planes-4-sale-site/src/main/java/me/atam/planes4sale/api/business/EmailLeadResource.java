package me.atam.planes4sale.api.business;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path("/business/email-leads")
public class EmailLeadResource {


    @GET
    public List<EmailLead> getAllEmailLeads(){
        List result = new ArrayList<EmailLead>();
        result.add(new EmailLead("buyer@buyer.com", "123", "hi", "seller@emails.com"));
        result.add(new EmailLead("buyer@buyer.com", "444", "hi", "seller@emails.com"));
        result.add(new EmailLead("buyer@buyer.com", "555", "hi", "seller@emails.com"));
        result.add(new EmailLead("buyer@buyer.com", "777", "hi", "seller@emails.com"));
        return result;
    }
    //business/email-leads

}
