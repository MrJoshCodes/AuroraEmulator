package net.aurora.fuse.emulator.game;

import net.aurora.fuse.emulator.game.gameclients.GameClientManager;
import net.aurora.fuse.emulator.game.users.UserManager;

/**
 * The game class holding all game instances.
 * @author Josh
 */
public class Game {
    private final GameClientManager gameClientManager;
    private final UserManager userManager;
    
    /**
     * Creates a new instance of the Game class and cache some database stuff and create other instances.
     */
    public Game() {
        gameClientManager = new GameClientManager();
        userManager = new UserManager();
    }
    
    public GameClientManager getGameClientManager() {
        return gameClientManager;
    }
    
    public UserManager getUsers() {
        return userManager;
    }
}
