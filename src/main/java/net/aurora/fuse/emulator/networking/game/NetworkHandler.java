package net.aurora.fuse.emulator.networking.game;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * The NetworkHandler class which contains a new
 * @author josh
 */
public class NetworkHandler extends ChannelInboundHandlerAdapter{

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
        Aurora.getInstance().getGame().getGameClientController().createGameClient(ctx);
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Aurora.getInstance().getGame().getGameClientController().removeGameClient(ctx);
    }
    
    /**
     * Reads a message from the inbound stream.
     * @param ctx The ChannelHandler context.
     * @param message The message.
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object message){
        // TODO: Here some code...
    }
    
}
