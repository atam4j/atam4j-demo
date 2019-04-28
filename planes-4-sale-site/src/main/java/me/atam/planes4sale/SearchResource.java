package me.atam.planes4sale;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/search")
@Produces(MediaType.TEXT_HTML)
public class SearchResource {


    @GET
    public SearchResultsView getSearchResults(){
        return new SearchResultsView();
    }

}