package me.atam.planes4sale;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static me.atam.planes4sale.Plane.AIRBUS;
import static me.atam.planes4sale.Plane.BOEING;

public class InMemoryHardCodedPlaneService implements PlaneService {


    private List<Plane> planes = new ArrayList<>();

    public InMemoryHardCodedPlaneService() {

        //Jonathan Rankin
        this.planes.add(new Plane("bfe8a680", BOEING, "777-319/ER", LocalDate.of(2010, Month.DECEMBER, 1), "1836933", "ZK-OKM" ));
        this.planes.add(new Plane("9fc2a9c9", BOEING, "777-31B/ER", LocalDate.of(2015, Month.DECEMBER, 1), "2833243", "B-2049" ));
        this.planes.add(new Plane("fa8983a3", AIRBUS, "A340-541", LocalDate.of(2003, Month.DECEMBER, 1), "1360015", "A6-ERD" ));

    }


    @Override
    public List<Plane> getPlanesByManufacturer(String manufacturer) {
        return planes.stream().filter(p -> p.getManufacturer().equalsIgnoreCase(manufacturer)).collect(Collectors.toList());
    }
}
