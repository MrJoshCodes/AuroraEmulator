package net.aurora.fuse.emulator.game;

import net.aurora.fuse.emulator.game.gameclients.GameClientManager;
import net.aurora.fuse.emulator.game.navigator.Navigator;
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
    
    private final Navigator navigator;
    
    /**
     * Creates a new instance of the Game class and cache some database stuff and create other instances.
     */
    public Game() {
        gameClientManager = new GameClientManager();
        userManager = new UserManager();
        
        voucherManager = new VoucherManager();
        
        navigator = new Navigator();
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
    
    public Navigator getNavigator() {
        return navigator;
    }
}
