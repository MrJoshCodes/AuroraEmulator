package net.aurora.fuse.emulator.game.store.vouchers;

import net.aurora.fuse.emulator.game.gameclients.GameClient;

/**
 *
 * @author Josh
 */
public interface IVoucher {
    
    void redeem(GameClient client, String value);
    
}
