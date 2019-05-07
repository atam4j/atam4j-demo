package me.atam.planes4sale;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;

public class SQLPlaneService implements PlaneService {

    private String databaseConnectionString;
    private String databaseUsername;
    private String databasePassword;

    public SQLPlaneService() {
        this.databaseConnectionString = databaseConnectionString;
        this.databaseUsername = databaseUsername;
        this.databasePassword = databasePassword;
    }

    @Override
    public List<Plane> getPlanesByManufacturer(String manufacturer) {
        //Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        //Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/planes", "planes", "password");

        Jdbi jdbi = Jdbi.create(databaseConnectionString, databaseUsername, databasePassword);
        jdbi.installPlugin(new SqlObjectPlugin());

        return jdbi.withExtension(JDBIPlaneService.class, dao -> dao.findNameByManufacturer(manufacturer.toLowerCase()));
    }
}
