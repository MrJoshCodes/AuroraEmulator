package net.aurora.fuse.emulator.game;

import net.aurora.fuse.emulator.game.gameclients.GameClientManager;
import net.aurora.fuse.emulator.game.store.vouchers.VoucherManager;
import net.aurora.fuse.emulator.game.users.UserManager;

/**
 * The game class holding all game instances.
 * @author Josh
 */
public class Game {
    private final GameClientManager gameClientManager;
    private final UserManager userManager;
    
    private final VoucherManager voucherManager;
    
    /**
     * Creates a new instance of the Game class and cache some database stuff and create other instances.
     */
    public Game() {
        gameClientManager = new GameClientManager();
        userManager = new UserManager();
        
        voucherManager = new VoucherManager();
    }
    
    public GameClientManager getGameClientManager() {
        return gameClientManager;
    }
    
    public UserManager getUsers() {
        return userManager;
    }
    
    public VoucherManager getVouchers() {
        return voucherManager;
    }
}
