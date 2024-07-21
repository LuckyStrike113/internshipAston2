package by.servletsCRUDApi.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static Connection connection;
    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void createConnection(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/internship_aston2";
            connection = DriverManager.getConnection(url, "root", "root");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    };
    public static Connection getConnection(){
        if (connection == null){
            createConnection();
        }
        return connection;
    }

}
