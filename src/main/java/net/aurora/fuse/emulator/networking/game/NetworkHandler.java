package net.aurora.fuse.emulator.networking.game;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import net.aurora.fuse.emulator.Aurora;
import net.aurora.fuse.emulator.game.gameclients.GameClient;
import net.aurora.fuse.emulator.utilities.ByteBufUtils;
import net.aurora.fuse.emulator.utilities.HabboEncoding;

/**
 * The NetworkHandler class which contains a new
 * @author josh
 */
public class NetworkHandler extends ChannelInboundHandlerAdapter {

    /**
     * Creates a new instance of the NetworkHandler. Does nothing special.
     */
    public NetworkHandler() {
    }
    
    /**
     * Marks a channel as active and creates a GameClient object.
     * @param ctx The ChannelHandler context.
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Aurora.getGame().getGameClientManager().addGameClient(ctx.channel());
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Aurora.getGame().getGameClientManager().removeGameClient(ctx.channel());
    }
    
    /**
     * Reads a message from the inbound stream.
     * @param ctx The ChannelHandler context.
     * @param message The message.
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object message){
        GameClient client = Aurora.getGame().getGameClientManager().getGameClient(ctx.channel());
        ByteBuf packetBuffer = (ByteBuf)message;
                
        while (packetBuffer.readableBytes() >= 5) {
            int packetLength = HabboEncoding.decodeB64(ByteBufUtils.readBytes(packetBuffer, 3));
            ByteBuf packet = packetBuffer.readBytes(packetLength);
            
            Aurora.getPacketHandler().handle(client, packet);
        }
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable ex) {
        Aurora.LOGGER.info(ex.getMessage());
    }
    
}
