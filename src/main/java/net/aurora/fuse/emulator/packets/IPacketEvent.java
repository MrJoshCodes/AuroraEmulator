package net.aurora.fuse.emulator.packets;

import net.aurora.fuse.emulator.game.gameclients.GameClient;
import net.aurora.fuse.emulator.packets.types.ClientMessage;

/**
 * The interface for all packet events.
 * @author Josh
 */
public interface IPacketEvent {
    
    void run(GameClient client, ClientMessage request);
    
}
