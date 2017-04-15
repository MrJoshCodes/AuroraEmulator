package net.aurora.fuse.emulator.game.gameclients;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import net.aurora.fuse.emulator.packets.types.ServerMessage;

/**
 * The GameClient class holding data of a connected client.
 * @author Josh
 */
public class GameClient {
    /**
     * The channel of the client.
     */
    private final Channel channel;
    
    public GameClient(Channel channel) {
        this.channel = channel;
    }
    
    public void sendMessage(ServerMessage response) {
        ByteBuf packet = response.getPacket();
        packet.writeByte(1);
        channel.writeAndFlush(packet);
    }
}
