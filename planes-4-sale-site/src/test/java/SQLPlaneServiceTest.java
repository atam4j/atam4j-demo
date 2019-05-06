import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// H2 In-Memory Database Example shows about storing the database contents into memory.

public class SQLPlaneServiceTest {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    @Test
    public void stuff() {
        insertWithStatement();
        printAllPlanes(getDBConnection());
    }


    private static void insertWithStatement() {

        try (Connection connection = getDBConnection()){
            connection.setAutoCommit(true);

            try(Statement statement = connection.createStatement()){
                statement.execute("CREATE TABLE PLANES (id varchar(255) primary key, manufacturer varchar(255), model varchar(255), manufactureDate varchar(255), imageId varchar(255))");
            }

            try(Statement statement = connection.createStatement()){
                statement.execute("INSERT INTO PLANES (id, manufacturer, model, manufactureDate, imageId) VALUES('123', 'Boeing', '747-400', '-', '1234.jpg')");
            }

            printAllPlanes(connection);


            //stmt.execute("DROP TABLE PLANES");

            connection.commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void printAllPlanes(Connection connection) {
        try(Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery("select * from PLANES");
            System.out.println("H2 In-Memory Database inserted through Statement");
            while (rs.next()) {
                System.out.println("Id " + rs.getString("id") + " Manufacturer " + rs.getString("manufacturer") + " Model " + rs.getString("model"));
            }
        }
        catch (Exception e) {
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

