package me.atam.planes4sale;

import org.junit.BeforeClass;

import java.sql.*;

public class H2StubbedDatabase {

    private static final String DB_DRIVER = "org.h2.Driver";
    public static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    public static final String KNOWN_PLANE_ID = "123";

    @BeforeClass
    public static void createTableAndPopulateWithDummyData() {

        try (Connection connection = getDBConnection()){
            connection.setAutoCommit(true);

            try(Statement statement = connection.createStatement()){
                statement.execute("CREATE TABLE planes (id varchar(255) primary key, manufacturer varchar(255), model varchar(255), manufactureDate date, imageId varchar(255))");
            }

            try(Statement statement = connection.createStatement()){
                statement.execute("INSERT INTO planes (id, manufacturer, model, manufactureDate, imageId) VALUES('" + KNOWN_PLANE_ID + "', 'Boeing', '747-400', sysdate, '1234.jpg')");
            }

            printAllPlanes(connection);

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

    private static void printAllPlanes(Connection connection) {
        try(Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery("select * from planes");
            System.out.println("H2 In-Memory Database inserted through Statement");
            while (rs.next()) {
                System.out.println("Id " + rs.getString("id") + " Manufacturer " + rs.getString("manufacturer") + " Model " + rs.getString("model"));
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
