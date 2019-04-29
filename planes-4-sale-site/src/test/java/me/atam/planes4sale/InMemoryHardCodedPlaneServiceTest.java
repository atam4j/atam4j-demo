package me.atam.planes4sale;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class InMemoryHardCodedPlaneServiceTest {

    private PlaneService inMemoryPlaneService = new InMemoryHardCodedPlaneService();

    @Test
    public void getPlanesByManufacturerReturnsBoeings() {
        List<Plane> boeingPlanes = inMemoryPlaneService.getPlanesByManufacturer("Boeing");
        assertThat(boeingPlanes.size(), CoreMatchers.is(2));
        assertThat(boeingPlanes.get(0).getManufacturer(), CoreMatchers.is("Boeing"));
        assertThat(boeingPlanes.get(1).getManufacturer(), CoreMatchers.is("Boeing"));
    }

    @Test
    public void getPlanesByManufacturerReturnsAirbus() {
        List<Plane> boeingPlanes = inMemoryPlaneService.getPlanesByManufacturer("Airbus");
        assertThat(boeingPlanes.size(), CoreMatchers.is(1));
        assertThat(boeingPlanes.get(0).getManufacturer(), CoreMatchers.is("Airbus"));
    }

    @Test
    public void getPlanesByManufacturerReturnsEmptyListWhenNone() {
        List<Plane> boeingPlanes = inMemoryPlaneService.getPlanesByManufacturer("DOES_NOT_EXIST");
        assertThat(boeingPlanes.size(), CoreMatchers.is(0));
    }
}