package me.atam.planes4sale;

import io.dropwizard.views.View;

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
