package net.aurora.fuse.emulator.packets.events.handshake;

import net.aurora.fuse.emulator.game.gameclients.GameClient;
import net.aurora.fuse.emulator.packets.IPacketEvent;
import net.aurora.fuse.emulator.packets.types.ClientMessage;
import net.aurora.fuse.emulator.packets.types.ServerMessage;

/**
 *
 * @author Josh
 */
public class GenerateKeyEvent implements IPacketEvent {

    @Override
    public void run(GameClient client, ClientMessage request) {
        // TODO: Encryption?
        client.sendFigureSets();
              
        //RAHIIIKHJIPAIQAdd-MM-yyyy
        ServerMessage response = new ServerMessage(257);
        response.writeVL64(6);
        response.writeVL64(0);
        response.writeVL64(1);
        response.writeVL64(1);
        response.writeVL64(1);
        response.writeVL64(3);
        response.writeVL64(0);
        response.writeVL64(2);
        response.writeVL64(1);
        response.writeVL64(4);
        response.writeVL64(1);
        response.writeVL64(5);
        response.writeString("dd-MM-yyyy", 2);
        client.sendMessage(response);
    }
    
}
