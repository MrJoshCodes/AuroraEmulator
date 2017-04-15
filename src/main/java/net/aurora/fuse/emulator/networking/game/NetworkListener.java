package net.aurora.fuse.emulator.networking.game;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Class to initialize a network server for the basic game.
 * @author Josh
 */
public class NetworkListener {
    /**
     * The main server bootstrap object.
     */
    private final ServerBootstrap bootstrap;
    
    /**
     * Creates a new instance of the NetworkListener class, setting up the server bootstrap.
     */
    public NetworkListener() {
        bootstrap = new ServerBootstrap();
        bootstrap.group(new NioEventLoopGroup(1), new NioEventLoopGroup(10));
        
        bootstrap.channel(NioServerSocketChannel.class);
        
        // Adds a new childHandler and initializes the default SocketChannel.
        // Also adds the NetworkHandler to the pipeline.
        // To learn more about this, see the Netty documentation.
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel sc) {
                sc.pipeline().addLast(new NetworkHandler());
            }
        });
        
        bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.childOption(ChannelOption.SO_REUSEADDR, true);
        bootstrap.childOption(ChannelOption.SO_RCVBUF, 5120);
        bootstrap.childOption(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(5120));
        bootstrap.childOption(ChannelOption.ALLOCATOR, new PooledByteBufAllocator());
    }
    
    /**
     * Starts listening to a port.
     * @param port The port to listen on.
     */
    public void start(int port) {
        bootstrap.bind(port);
    }
}
