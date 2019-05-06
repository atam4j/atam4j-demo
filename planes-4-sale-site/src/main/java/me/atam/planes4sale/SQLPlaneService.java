package me.atam.planes4sale;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SQLPlaneService implements PlaneService {
    @Override
    public List<Plane> getPlanesByManufacturer(String manufacturer) {

        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        jdbi.installPlugin(new SqlObjectPlugin());

        // Jdbi implements your interface based on annotations
        String id = jdbi.withExtension(JDBIPlaneService.class, dao -> {

            String someId = dao.findNameByManufacturer("boeing");

            return someId;
        });

        List<Plane> stuff = new ArrayList<>();
        LocalDate date = LocalDate.of(1, 1, 1);
        stuff.add(new Plane(id,"","", date,"",""));

        return stuff;
    }
}
