package net.aurora.fuse.emulator.packets.events.purse;

import net.aurora.fuse.emulator.Aurora;
import net.aurora.fuse.emulator.game.gameclients.GameClient;
import net.aurora.fuse.emulator.packets.IPacketEvent;
import net.aurora.fuse.emulator.packets.types.ClientMessage;

/**
 *
 * @author Josh
 */
public class RedeemVoucherEvent implements IPacketEvent {

    @Override
    public void run(GameClient client, ClientMessage request) {
        String code = request.readString();
        
        Aurora.getGame().getVouchers().TryVoucher(client, code);
    }
    
}
