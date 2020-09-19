package jm.task.core.jdbc.util;



import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static class ConnectionUtils {

        private static Connection connection;
        private static final String URL = "jdbc:mysql://localhost:3306/mydb?useSSL=false&serverTimezone=UTC";
        private static final String USER = "root";
        private static final String PASS = "root";

        public static Connection getConnection() {

            try {
                Driver driver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(driver);
                connection = DriverManager.getConnection(URL, USER, PASS);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return connection;
        }
    }
}
