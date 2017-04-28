package net.aurora.fuse.emulator.game.rooms;

import gnu.trove.map.hash.THashMap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import net.aurora.fuse.emulator.Aurora;
import net.aurora.fuse.emulator.storage.DatabaseQuery;

/**
 *
 * @author Josh
 */
public class RoomManager {
    
    private final THashMap<Integer, Room> rooms;
    
    public RoomManager() {
        rooms = new THashMap<>();
    }
    
    public Room getRoom(int roomId) {
        if (rooms.containsKey(roomId)) {
            return rooms.get(roomId);
        }
        
        try (DatabaseQuery dbQuery = Aurora.getDatabase().create()) {
            dbQuery.setQuery("SELECT * FROM `rooms` WHERE `id` = ? LIMIT 1");
            dbQuery.setInt(1, roomId);
            ResultSet set = dbQuery.getResultSet();
            
            if (set.next()) {
                Room room = new Room(set);
                rooms.put(roomId, room);
                return room;
            }
        } catch (SQLException ex) {
            
        }
        
        return null;
    }
    
    public LinkedList<Room> getRoomsInCategory(int categoryId) {
        LinkedList<Room> roomsInCategory = new LinkedList<>();
        
        try (DatabaseQuery dbQuery = Aurora.getDatabase().create()) {
            dbQuery.setQuery("SELECT * FROM `rooms` WHERE `category_id` = ?");
            dbQuery.setInt(1, categoryId);
            ResultSet set = dbQuery.getResultSet();
            
            while (set.next()) {
                if (rooms.containsKey(set.getInt("id"))) {
                    roomsInCategory.add(rooms.get(set.getInt("id")));
                } else {
                    Room room = new Room(set);
                    roomsInCategory.add(room);
                }
            }
        } catch (SQLException ex) {
            
        }
        
        return roomsInCategory;
    }
    
}
