package net.aurora.fuse.emulator.game.users;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Josh
 */
public class User {
    
    private int id;
    private String username;
    private String password;
    private String figure;
    private String gender;
    private String motto;
    private int coins;
    private int tickets;
    private int film;
    private int rank;
    
    public User() {
        id = 0;
        username = "";
        password = "";
        figure = "";
        gender = "M";
        motto = "";
        coins = 50;
        tickets = 2;
        film = 0;
        rank = 1;
    }
    
    public User(ResultSet set) {
        try {
            id = set.getInt("id");
            username = set.getString("username");
            password = set.getString("password");
            figure = set.getString("figure");
            gender = set.getString("gender");
            motto = set.getString("motto");
            coins = set.getInt("coins");
            tickets = set.getInt("tickets");
            film = set.getInt("film");
            rank = set.getInt("rank");
        } catch (SQLException ex) {
            
        }
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }

    public String getFigure() {
        return figure;
    }

    public String getGender() {
        return gender;
    }

    public String getMotto() {
        return motto;
    }

    public int getCoins() {
        return coins;
    }

    public int getTickets() {
        return tickets;
    }

    public int getFilm() {
        return film;
    }

    public int getRank() {
        return rank;
    }
    
    
}
