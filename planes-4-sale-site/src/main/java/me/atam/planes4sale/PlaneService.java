package me.atam.planes4sale;

import java.util.List;

public interface PlaneService {

    List<Plane> getPlanesByManufacturer(String manufacturer);

}
