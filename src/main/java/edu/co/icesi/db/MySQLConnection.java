package edu.co.icesi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {

    private Connection connection;

    public MySQLConnection() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/semana10", "root", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void closeDB() {
        try {
            this.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}

