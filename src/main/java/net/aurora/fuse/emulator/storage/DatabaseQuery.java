package net.aurora.fuse.emulator.storage;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseQuery implements Closeable {

    private Connection _connection;
    private PreparedStatement _stmt;

    public DatabaseQuery(Connection connection) {
        try {
            _connection = connection;
        } catch (Exception ex) {
            //...
        }
    }

    public void setQuery(String query) {
        try {
            _stmt = _connection.prepareStatement(query);
        } catch (SQLException ex) {
            //...
        }
    }

    public void setInt(int index, int i) {
        try {
            _stmt.setInt(index, i);
        } catch (SQLException ex) {

        }
    }

    public void setString(int index, String i) {
        try {
            _stmt.setString(index, i);
        } catch (SQLException ex) {

        }
    }

    public void setBoolean(int index, boolean i) {
        try {
            _stmt.setBoolean(index, i);
        } catch (SQLException ex) {

        }
    }

    public void setDate(int index, Date i) {
        try {
            _stmt.setDate(index, i);
        } catch (SQLException ex) {

        }
    }

    public void setDouble(int index, double i) {
        try {
            _stmt.setDouble(index, i);
        } catch (SQLException ex) {

        }
    }

    public void execute() {
        try {
            _stmt.execute();
        } catch (SQLException ex) {

        }
    }

    public int insert() {
        try {
            if (_stmt.execute()) {
                return _stmt.getGeneratedKeys().getInt(1);
            }

            return -1;
        } catch (SQLException ex) {
            return -1;
        }
    }

    public ResultSet getResultSet() {
        try {
            return _stmt.executeQuery();
        } catch (SQLException ex) {
            return null;
        }
    }

    public String getString() {
        try {
            ResultSet set = getResultSet();

            if (set != null && set.next()) {
                return set.getString(0);
            }

            return null;
        } catch (SQLException ex) {
            return null;
        }
    }

    public int getInt() {
        try {
            ResultSet set = getResultSet();

            if (set != null && set.next()) {
                return set.getInt(1);
            }

            return -1;
        } catch (SQLException ex) {
            return -1;
        }
    }

    @Override
    public void close() {
        try {
            _stmt.close();
            _connection.close();
        } catch (SQLException ex) {
            
        }
    }

    public void setObject(int index, Object value) {
        try {
            _stmt.setObject(index, value);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}