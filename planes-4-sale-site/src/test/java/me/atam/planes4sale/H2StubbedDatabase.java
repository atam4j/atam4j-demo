package me.atam.planes4sale;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class H2StubbedDatabase {

    private static final String DB_DRIVER = "org.h2.Driver";
    public static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    public static final String DB_USER = "planes";
    public static final String DB_PASSWORD = "password";

    //todo - make known planes and write code to insert them - Will make tests easier to read!
    public static final String KNOWN_PLANE_ID = "bfe8a680";
    public static final String KNOWN_PLANE_SELLER_EMAIL_ADDRESS = "seller.of.plane.bfe8a680@email.com";
    
    public static final Plane KNOWN_PLANE_1 = new Plane("bfe8a680", "Boeing", "777-319/ER", LocalDate.of(2010, 12, 1), "1836933", "ZK-OKM" );
    public static final Plane KNOWN_PLANE_2= new Plane("9fc2a9c9", "Boeing", "777-31B/ER", LocalDate.of( 2015, 12, 1), "2833243","B-2049" );
    public static final Plane KNOWN_PLANE_3 = new Plane("fa8983a3", "Airbus", "A340-541",   LocalDate.of( 2003, 12,1), "1360015", "A6-ERD" );


    public static void main(String[] args) {
        String s = "INSERT INTO planes (id, manufacturer, model, manufactureDate, imageId, reg, sellerEmail) VALUES('bfe8a680', 'Boeing', '777-319/ER', DATE '2010-12-01', '1836933', 'ZK-OKM', 'seller.of.plane.bfe8a680@email.com'";
        System.out.println(s);

        String b = String.format ("INSERT INTO planes (id, manufacturer, model, manufactureDate, imageId, reg, sellerEmail) VALUES('%s', '%s', '%s', DATE '%s', '%s', '%s', 'seller.of.plane.%s@email.com'", KNOWN_PLANE_1.getId(), KNOWN_PLANE_1.getManufacturer(), KNOWN_PLANE_1.getModel(), KNOWN_PLANE_1.getManufactureDate().format(DateTimeFormatter.ISO_DATE), KNOWN_PLANE_1.getImageId(), KNOWN_PLANE_1.getReg(), KNOWN_PLANE_1.getId());
        System.out.println(b);

    }


    public static void resetAndRecreatePlanesDatabase() {

        try (Connection connection = getDBConnection()){
            connection.setAutoCommit(true);

            try(Statement statement = connection.createStatement()){
                statement.execute("DROP TABLE IF EXISTS planes");
                statement.execute("CREATE TABLE planes (id varchar(255) primary key, manufacturer varchar(255), model varchar(255), manufactureDate date, imageId varchar(255), reg varchar(255), sellerEmail varchar(255))");
                statement.execute("DROP TABLE IF EXISTS emailleads");
                statement.execute("CREATE TABLE emailleads (id varchar(255) primary key, buyerNumber varchar(255), buyerEmail varchar(255), planeId varchar(255), message varchar(1000), sellerEmail varchar(255));");
            }

            try(Statement statement = connection.createStatement()){


                statement.execute(getInsertStatementForPlane(KNOWN_PLANE_1));
                statement.execute(getInsertStatementForPlane(KNOWN_PLANE_2));
                statement.execute(getInsertStatementForPlane(KNOWN_PLANE_3));

            }

            connection.commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected static String getInsertStatementForPlane(Plane plane) {
        return String.format ("INSERT INTO planes (id, manufacturer, model, manufactureDate, imageId, reg, sellerEmail) VALUES('%s', '%s', '%s', DATE '%s', '%s', '%s', 'seller.of.plane.%s@email.com')", plane.getId(), plane.getManufacturer(), plane.getModel(), plane.getManufactureDate().format(DateTimeFormatter.ISO_DATE), plane.getImageId(), plane.getReg(), plane.getId());
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
