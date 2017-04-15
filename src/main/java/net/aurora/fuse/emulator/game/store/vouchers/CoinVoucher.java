package net.aurora.fuse.emulator.game.store.vouchers;

import net.aurora.fuse.emulator.game.gameclients.GameClient;
import net.aurora.fuse.emulator.packets.events.purse.GetCreditsEvent;

/**
 *
 * @author Josh
 */
public class CoinVoucher implements IVoucher {

    @Override
    public void redeem(GameClient client, String value) {
        int intValue = Integer.parseInt(value);
        
        client.getUser().addCoins(intValue);
        client.getUser().updateCoins();
        
        new GetCreditsEvent().run(client, null);
    }
    
}
