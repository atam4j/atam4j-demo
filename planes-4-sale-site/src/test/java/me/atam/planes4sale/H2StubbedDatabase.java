package me.atam.planes4sale;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2StubbedDatabase {

    private static final String DB_DRIVER = "org.h2.Driver";
    public static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "planes";
    private static final String DB_PASSWORD = "password";
    public static final String KNOWN_PLANE_ID = "bfe8a680";

    public static void resetAndRecreatePlanesDatabase() {

        try (Connection connection = getDBConnection()){
            connection.setAutoCommit(true);

            try(Statement statement = connection.createStatement()){
                statement.execute("DROP TABLE IF EXISTS planes");
                statement.execute("CREATE TABLE planes (id varchar(255) primary key, manufacturer varchar(255), model varchar(255), manufactureDate date, imageId varchar(255), reg varchar(255))");
            }

            try(Statement statement = connection.createStatement()){
                statement.execute("INSERT INTO planes (id, manufacturer, model, manufactureDate, imageId, reg) VALUES('bfe8a680', 'BOEING', '777-319/ER', DATE '2010-12-01', '1836933', 'ZK-OKM')");
                statement.execute("INSERT INTO planes (id, manufacturer, model, manufactureDate, imageId, reg) VALUES('9fc2a9c9', 'BOEING', '777-31B/ER', DATE '2015-12-01', '2833243','B-2049')");
                statement.execute("INSERT INTO planes (id, manufacturer, model, manufactureDate, imageId, reg) VALUES('fa8983a3', 'AIRBUS', 'A340-541',   DATE '2003-12-01', '1360015', 'A6-ERD')");

                /*
this.planes.add(new Plane("bfe8a680", BOEING, "777-319/ER", LocalDate.of(2010, Month.DECEMBER, 1), "1836933", "ZK-OKM" ));
this.planes.add(new Plane("9fc2a9c9", BOEING, "777-31B/ER", LocalDate.of(2015, Month.DECEMBER, 1), "2833243", "B-2049" ));
this.planes.add(new Plane("fa8983a3", AIRBUS, "A340-541", LocalDate.of(2003, Month.DECEMBER, 1), "1360015", "A6-ERD" ));
                 */


            }

            connection.commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

}
