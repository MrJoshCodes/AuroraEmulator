package net.aurora.fuse.emulator.packets.events.purse;

import net.aurora.fuse.emulator.game.gameclients.GameClient;
import net.aurora.fuse.emulator.packets.IPacketEvent;
import net.aurora.fuse.emulator.packets.types.ClientMessage;
import net.aurora.fuse.emulator.packets.types.ServerMessage;

/**
 *
 * @author Josh
 */
public class GetCreditsEvent implements IPacketEvent {

    @Override
    public void run(GameClient client, ClientMessage request) {
        ServerMessage response = new ServerMessage(6);
        response.writeString(String.valueOf(client.getUser().getCoins()), 0);
        client.sendMessage(response);
    }
    
}
