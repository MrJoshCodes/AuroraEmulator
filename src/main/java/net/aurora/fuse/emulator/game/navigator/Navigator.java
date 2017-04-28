package net.aurora.fuse.emulator.game.navigator;

import gnu.trove.map.hash.THashMap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import net.aurora.fuse.emulator.Aurora;
import net.aurora.fuse.emulator.packets.types.ServerMessage;
import net.aurora.fuse.emulator.storage.DatabaseQuery;

/**
 * The navigator class holds functions to use to get navigator room listings and categories.
 * @author Josh
 */
public class Navigator {
    
    private final THashMap<Integer, RoomCategory> roomCategories;
    
    public Navigator() {
        roomCategories = new THashMap<>();
        
        cacheCategories();
    }
    
    private void cacheCategories() {
        try (DatabaseQuery dbQuery = Aurora.getDatabase().create()) {
            dbQuery.setQuery("SELECT * FROM `room_categories`");
            ResultSet set = dbQuery.getResultSet();
            
            while (set.next()) {
                roomCategories.put(set.getInt("id"), new RoomCategory(set));
            }
            
            Aurora.LOGGER.log(Level.INFO, "Loaded ''{0}'' room categories.", roomCategories.size());
        } catch (SQLException ex) {
            Aurora.LOGGER.log(Level.SEVERE, "Cannot cache room categories: {0}", ex.getMessage());
        }
    }

    public RoomCategory getRoomCategory(Integer categoryId) {
        return roomCategories.get(categoryId);
    }
    
    public LinkedList<RoomCategory> getSubCategories(int categoryId) {
        LinkedList<RoomCategory> subCategories = new LinkedList<>();
        
        for (RoomCategory category : roomCategories.values()) {
            if (category.getParentId() == categoryId) {
                subCategories.add(category);
            }
        }
        
        return subCategories;
    }
    
    public void serializeCategory(ServerMessage response, RoomCategory category, boolean hideFull) {
        response.writeVL64(hideFull);
        response.writeVL64(category.getId());
        response.writeVL64(category.getType().equals(RoomCategoryType.PRIVATE) ? 2 : 0);
        response.writeString(category.getName(), 2);
        response.writeVL64(0);
        response.writeVL64(10000);
        response.writeVL64(category.getParentId());
        
        if (category.getType().equals(RoomCategoryType.PRIVATE)) {
            response.writeVL64(0); // room count
        }
    }
    
    public void serializeSubCategory(ServerMessage response, RoomCategory category) {
        response.writeVL64(category.getId());
        response.writeVL64(0);
        response.writeString(category.getName(), 2);
        response.writeVL64(0); // current users
        response.writeVL64(10000); // max users
        response.writeVL64(category.getParentId());
    }
    
}
