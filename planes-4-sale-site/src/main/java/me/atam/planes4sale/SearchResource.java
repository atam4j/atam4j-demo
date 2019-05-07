package me.atam.planes4sale;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {

    private JDBIPlaneService planeService;

    public SearchResource(JDBIPlaneService planeService) {
        this.planeService = planeService;
    }

    @GET
    public List<Plane> getSearchResults(@QueryParam("manufacturer") String manufacturer){
        return planeService.findNameByManufacturer(manufacturer);
    }

}