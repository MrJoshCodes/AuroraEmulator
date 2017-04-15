package net.aurora.fuse.emulator.game;

import net.aurora.fuse.emulator.game.gameclients.GameClientManager;

/**
 * The game class holding all game instances.
 * @author Josh
 */
public class Game {
    private final GameClientManager gameClientManager;
    
    /**
     * Creates a new instance of the Game class and cache some database stuff and create other instances.
     */
    public Game() {
        gameClientManager = new GameClientManager();
    }
    
    public GameClientManager getGameClientManager() {
        return gameClientManager;
    }
}
