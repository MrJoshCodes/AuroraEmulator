package net.aurora.fuse.emulator.game.navigator;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The RoomCategory class holds information about a certain public/private category.
 * @author Josh
 */
public class RoomCategory {
    
    private int id;
    private String name;
    private int parentId;
    private RoomCategoryType type;
    private boolean canTrade;
    private boolean visible;
    
    public RoomCategory(ResultSet set) {
        try {
            id = set.getInt("id");
            name = set.getString("name");
            parentId = set.getInt("parent_id");
            type = RoomCategoryType.valueOf(set.getString("type").toUpperCase());
            canTrade = set.getBoolean("can_trade");
            visible = set.getBoolean("is_visible");
        } catch (SQLException ex) {
            
        }
    }
    
    /**
     * Gets the ID of the room category.
     * @return id
     */
    public int getId() {
        return id;
    }
    
    /**
     * Gets the name of the room category displayed in the navigator.
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the ID of the parent the child is placed in.
     * @return parentId
     */
    public int getParentId() {
        return parentId;
    }
    
    /**
     * Gets whether the RoomCategory is a public or a private category.
     * @return type
     */
    public RoomCategoryType getType() {
        return type;
    }
    
    /**
     * Gets the tradeability of the category.
     * @return canTrade
     */
    public boolean canTrade() {
        return canTrade;
    }
    
    /**
     * Gets whether the category is visible in the navigator. 
     * Note: Staffs can always see these categories.
     * @return visible;
     */
    public boolean isVisible() {
        return visible;
    }
    
}
