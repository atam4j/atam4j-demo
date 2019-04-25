package me.atam.planes4sale;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HomepageResource {


    @GET
    public HomepageView getHomepage(){
        return new HomepageView();
    }

}
