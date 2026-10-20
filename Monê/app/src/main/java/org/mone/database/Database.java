package org.mone.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    
private static final String URL = "jdbc:mysql://localhost:3306/mone";
private static final String USER = "root";
private static final String PASSWORD = "krakatoa";

private Database() {

}

public static Connection getConnection() throws SQLException {
    Connection connection = null;
    connection = DriverManager.getConnection(URL, USER, PASSWORD);

return connection;
}
}
