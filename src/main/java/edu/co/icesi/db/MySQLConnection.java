package edu.co.icesi.db;

import java.sql.*;
import java.util.ArrayList;

public class MySQLConnection {

    private Connection connection;

    public MySQLConnection() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HollywoodOrganizer", "root", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //createTables();
    }

    public void closeDB() {
        try {
            this.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void createTables() {

        try {
            Statement statement = connection.createStatement();

            statement.execute("Create table genres(id INT PRIMARY KEY AUTO_INCREMENT, genre VARCHAR(100))");
            statement.execute("CREATE TABLE movies(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), genreId INT, FOREIGN KEY (genreId) REFERENCES genres(id))");
            statement.execute("CREATE TABLE actors(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100))");
            statement.execute("CREATE TABLE movies_actors(id INT PRIMARY KEY AUTO_INCREMENT, movie_id INT, actor_id INT, FOREIGN KEY (movie_id) REFERENCES movies(id), FOREIGN KEY (actor_id) REFERENCES actors(id))");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void addMovie(String nam) {

        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO movies(name) VALUES ('$NAME')".replace("$NAME", nam);
            System.out.println(sql);
            statement.execute(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addActor(String name) {

        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO actors(name) VALUES ('$NAME')".replace("$NAME", name);
            statement.execute(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addGenre(String name) {

        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO genres(genre) VALUES ('$GENRE')".replace("$GENRE", name);
            statement.execute(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getMovieId(String mName) {

        int index = -1;

        try {

            Statement statement = connection.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * from movies");

            while (rset.next()) {
                String name = rset.getString(rset.findColumn("name"));
                if (name.equals(mName)) {
                    index = rset.getInt("id");
                    break;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return index;

    }

    public int getActorId(String mName) {

        int index = -1;

        try {

            Statement statement = connection.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * from actors");

            while (rset.next()) {
                String name = rset.getString(rset.findColumn("name"));
                if (name.equals(mName)) {
                    index = rset.getInt("id");
                    break;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return index;

    }

    public int getGenreId(String mName) {

        int index = -1;

        try {

            Statement statement = connection.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * from genres");

            while (rset.next()) {
                String name = rset.getString(rset.findColumn("genre"));
                if (name.equals(mName)) {
                    index = rset.getInt("id");
                    break;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return index;

    }


    public ArrayList<String> listMovies() {
        ArrayList<String> movies = new ArrayList<String>();
        try {

            Statement statement = connection.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * from movies");

            while (rset.next()) {
                String name = rset.getString(rset.findColumn("name"));
                movies.add(name);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return movies;
    }

    public ArrayList<String> listActors() {
        ArrayList<String> actors = new ArrayList<String>();
        try {

            Statement statement = connection.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * from actors");

            while (rset.next()) {
                String name = rset.getString(rset.findColumn("name"));
                actors.add(name);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return actors;
    }

    public ArrayList<String> listGenres() {
        ArrayList<String> genres = new ArrayList<String>();
        try {

            Statement statement = connection.createStatement();
            ResultSet rset = statement.executeQuery("SELECT * from genres");

            while (rset.next()) {
                String name = rset.getString(rset.findColumn("genre"));
                genres.add(name);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return genres;
    }

    public void linkMovieAndActor(String movie, String actor) {

        int idMovie = this.getMovieId(movie);
        int idActor = this.getActorId(actor);

        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO movies_actors(movie_id, actor_id) values ('$idMovie','$idActor')"
                    .replace("$idMovie", idMovie + "")
                    .replace("$idActor", idActor + "");
            statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void linkMovieAndGenre(String movie, String genre) {

        int idMovie = this.getMovieId(movie);
        int idGenre = this.getGenreId(genre);

        try {
            Statement statement = connection.createStatement();
            String sql = "UPDATE movies SET genreId = '$idGenre' WHERE id = '$idMovie'"
                    .replace("$idMovie", idMovie + "")
                    .replace("$idGenre", idGenre + "");
            statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public ArrayList<String> getMovieGenres(String movieName) {

        ArrayList<String> genres = new ArrayList<String>();
        int idMovie = this.getMovieId(movieName);

        try {

            Statement statement = connection.createStatement();
            String sql = "SELECT genres.genre FROM(genres INNER JOIN movies ON genres.id = movies.genreId) " +
                    "WHERE movies.id = '$idMovie'"
                            .replace("$idMovie", idMovie + "");
            ResultSet rset = statement.executeQuery(sql);

            while (rset.next()) {
                String genre = rset.getString(1);
                genres.add(genre);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return genres;
    }

    public ArrayList<String> getMovieActors(String movieName) {

        ArrayList<String> actors = new ArrayList<String>();

        int idMovie = this.getMovieId(movieName);

        try {

            Statement statement = connection.createStatement();
            String sql = "SELECT actors.name FROM(actors INNER JOIN movies_actors ON actors.id = movies_actors.actor_id) " +
                    "INNER JOIN movies ON movies_actors.movie_id = movies.id WHERE movies.id = '$idMovie'"
                            .replace("$idMovie", idMovie + "");
            ResultSet rset = statement.executeQuery(sql);

            while (rset.next()) {
                String name = rset.getString(1);
                actors.add(name);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return actors;
    }

    public void deleteMovie(String movie) {

        int idMovie = this.getMovieId(movie);

        try {
            Statement statement = connection.createStatement();
            String sql1 = "DELETE FROM movies_actors WHERE movie_id = '$idMovie'"
                    .replace("$idMovie", idMovie + "");

            String sql2 = "DELETE FROM movies WHERE id = '$idMovie'"
                    .replace("$idMovie", idMovie + "");

            statement.execute(sql1);
            statement.execute(sql2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void deleteActor(String actor) {

        int idActor = this.getActorId(actor);

        try {
            Statement statement = connection.createStatement();
            String sql1 = "DELETE FROM movies_actors WHERE actor_id = '$idActor'"
                    .replace("$idActor", idActor + "");

            String sql2 = "DELETE FROM actors WHERE id = '$idActor'"
                    .replace("$idActor", idActor + "");

            statement.execute(sql1);
            statement.execute(sql2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public ArrayList<String> moviesIdByGenre(int idGenre) {

        ArrayList<String> movies = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rset = statement.executeQuery("SELECT movies.name FROM movies INNER JOIN genres ON movies.genreId = genres.id WHERE genres.id ='$idGenre'"
                    .replace("$idGenre", idGenre + ""));
            while (rset.next()) {
                String id = rset.getString(1);
                System.out.println(id);
                movies.add(""+id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return movies;
    }

    public void deleteGenre(String genre) {

        int idGenre = this.getGenreId(genre);

        try {
            Statement statement = connection.createStatement();

            ArrayList<String> movies = moviesIdByGenre(idGenre);


            for(String nameM: movies){
                this.deleteMovie(nameM);
            }

            String sql2 = "DELETE FROM genres WHERE id = '$idGenre'"
                    .replace("$idGenre", idGenre + "");

            statement.execute(sql2);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



}

