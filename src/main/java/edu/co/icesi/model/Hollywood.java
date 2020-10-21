package edu.co.icesi.model;

import edu.co.icesi.db.MySQLConnection;

import java.util.HashMap;

public class Hollywood {

    private HashMap<String, Movie> movies;
    private MySQLConnection connection;

    public Hollywood() {
        this.movies = new HashMap<String, Movie>();
        this.connection = new MySQLConnection();

    }


}
