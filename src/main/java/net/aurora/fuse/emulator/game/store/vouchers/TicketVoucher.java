package net.aurora.fuse.emulator.game.store.vouchers;

import net.aurora.fuse.emulator.game.gameclients.GameClient;
import net.aurora.fuse.emulator.packets.events.handshake.InfoRetrieveEvent;

/**
 *
 * @author Josh
 */
public class TicketVoucher implements IVoucher {

    @Override
    public void redeem(GameClient client, String value) {
        int intValue = Integer.parseInt(value);
        
        client.getUser().addTickets(intValue);
        client.getUser().updateTickets();
        
        new InfoRetrieveEvent().run(client, null);
    }
    
}
