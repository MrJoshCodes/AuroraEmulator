package net.aurora.fuse.emulator.game.rooms;

import java.sql.ResultSet;
import java.sql.SQLException;
import net.aurora.fuse.emulator.packets.types.ServerMessage;

/**
 * The Room class holds information about a single room.
 * @author Josh
 */
public class Room {
    
    private int id;
    private String name;
    private String description;
    private String state;
    private int ownerId;
    private String model;
    private int categoryId;
    private int wallpaper;
    private int floor;
    private boolean superUsers;
    private boolean showOwner;
    private String password;
    private int maxUsers;
    private int currentUsers;
    private String ccts; // TODO: Maybe make a new table although I don't think it's necessary.
    
    public Room(ResultSet set) {
        try {
            id = set.getInt("id");
            name = set.getString("name");
            description = set.getString("description");
            state = set.getString("state");
            ownerId = set.getInt("owner_id");
            model = set.getString("model");
            categoryId = set.getInt("category_id");
            wallpaper = set.getInt("wallpaper");
            floor = set.getInt("floor");
            superUsers = set.getBoolean("super_users");
            showOwner = set.getBoolean("show_owner");
            password = set.getString("password");
            maxUsers = set.getInt("max_users");
            currentUsers = set.getInt("current_users");
            ccts = set.getString("ccts");
        } catch (SQLException ex) {
            
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getState() {
        return state;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getModel() {
        return model;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getWallpaper() {
        return wallpaper;
    }

    public int getFloor() {
        return floor;
    }

    public boolean isSuperUsers() {
        return superUsers;
    }

    public boolean isShowOwner() {
        return showOwner;
    }

    public String getPassword() {
        return password;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public int getCurrentUsers() {
        return currentUsers;
    }

    public String getCcts() {
        return ccts;
    }

    public void serializePublic(ServerMessage response) {
        response.writeVL64(id);
        response.writeVL64(1);
        response.writeString(name, 2);
        response.writeVL64(currentUsers);
        response.writeVL64(maxUsers);
        response.writeVL64(categoryId);
        response.writeString(description, 2);
        response.writeVL64(id);
        response.writeVL64(0);
        response.writeString(ccts, 2);
        response.writeVL64(0);
        response.writeVL64(1);
    }

    public void serializePrivate(ServerMessage response) {
    }
    
    
    
}
