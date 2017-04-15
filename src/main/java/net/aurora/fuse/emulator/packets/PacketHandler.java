package net.aurora.fuse.emulator.packets;

import gnu.trove.map.hash.THashMap;
import io.netty.buffer.ByteBuf;
import java.util.logging.Level;
import net.aurora.fuse.emulator.Aurora;
import net.aurora.fuse.emulator.game.gameclients.GameClient;
import net.aurora.fuse.emulator.packets.events.handshake.GenerateKeyEvent;
import net.aurora.fuse.emulator.packets.types.ClientMessage;

/**
 * The PacketHandler class to handle incoming packets.
 * @author Josh
 */
public class PacketHandler {
    
    /**
     * A THashMap containing all the packet events.
     */
    private final THashMap<Integer, IPacketEvent> packetEvents;
    
    /**
     * Creates a new instance of the PacketHandler class and adds all packet events.
     */
    public PacketHandler() {
        packetEvents = new THashMap<>();
        
        packetEvents.put(202, new GenerateKeyEvent());
    }
    
    /**
     * Tries to handle a packet event, if it's added.
     * @param client The client to handle the packet.
     * @param packet The packet to handle.
     */
    public void handle(GameClient client, ByteBuf packet) {
        ClientMessage request = new ClientMessage(packet);
        
        if (packetEvents.containsKey(request.getPacketID())) {
            packetEvents.get(request.getPacketID()).run(client, request);
        } else {
            Aurora.LOGGER.log(Level.INFO, "Unregistered -> ''{0}''.", request.getContent());
        }
    }
    
}
