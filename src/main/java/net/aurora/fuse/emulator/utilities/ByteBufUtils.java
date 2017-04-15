package net.aurora.fuse.emulator.utilities;

import io.netty.buffer.ByteBuf;

/**
 * The ByteBufUtils to make reading from ByteBuf easier.
 * @author Josh
 */
public class ByteBufUtils {
    
    public static byte[] readBytes(ByteBuf buffer, int length) {
        byte[] arr = new byte[length];
        buffer.readBytes(arr);
        
        return arr;
    }
    
    public static byte[] getBytes(ByteBuf buffer, int pos, int length) {
        byte[] arr = new byte[length];
        buffer.getBytes(pos, arr);
        
        return arr;
    }
    
    public static byte[] getRemainingBytes(ByteBuf buffer) {
        return getBytes(buffer, buffer.readerIndex(), buffer.readableBytes());
    }
    
}
