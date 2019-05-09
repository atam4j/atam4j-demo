package me.atam.planes4sale.api;

import io.dropwizard.views.View;
import me.atam.planes4sale.Plane;

import java.util.List;

public class SearchResultsView extends View {

    private List<Plane> planes;

    public SearchResultsView(List<Plane> planes) {
        super("search.ftl");
        this.planes = planes;
    }

    public List<Plane> getPlanes() {
        return planes;
    }
}
