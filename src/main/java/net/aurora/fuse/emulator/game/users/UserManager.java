package net.aurora.fuse.emulator.game.users;

import gnu.trove.map.hash.THashMap;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.aurora.fuse.emulator.Aurora;
import net.aurora.fuse.emulator.storage.DatabaseQuery;

/**
 *
 * @author Josh
 */
public class UserManager {
    
    private final THashMap<Integer, User> users;
    
    public UserManager() {
        users = new THashMap<>();
    }
    
    public User getUserByName(String username) {
        try (DatabaseQuery dbQuery = Aurora.getDatabase().create()) {
            dbQuery.setQuery("SELECT * FROM `users` WHERE `username` = ? LIMIT 1");
            dbQuery.setString(1, username);
            ResultSet set = dbQuery.getResultSet();
            
            if (set.next()) {
                return new User(set);
            }
        } catch (SQLException ex) {
            
        }
        
        return null;
    }
    
}
