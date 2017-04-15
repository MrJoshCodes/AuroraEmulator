package net.aurora.fuse.emulator.packets.types;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.aurora.fuse.emulator.utilities.HabboEncoding;

/**
 * The ServerMessage class is used to construct a packet.
 * @author Josh
 */
public class ServerMessage {
    
    private final ByteBuf packet;
    
    public ServerMessage(int packetID) {
        packet = Unpooled.buffer();
        packet.writeBytes(HabboEncoding.encodeB64(packetID, 2));
    }
    
    /**
     * Adds a VL64 encoded integer to the packet.
     * @param value The integer to encode and add
     */
    public void writeVL64(int value) {
        packet.writeBytes(HabboEncoding.encodeWire(value));
    }
    
    /**
     * Adds a VL64 encoded boolean to the packet.
     * @param value The boolean to encode and add
     */
    public void writeVL64(boolean value) {
        packet.writeBytes(HabboEncoding.encodeWire(value ? 1 : 0));
    }
    
    /**
     * Adds a string ending with a specific delimiter.
     * @param value The string to add
     * @param delim The delimiter to use
     */
    public void writeString(String value, int delim) {
        packet.writeBytes(value.getBytes());
        packet.writeByte(delim);
    }
    
    /**
     * Gets the full packet.
     * @return The full packet.
     */
    public ByteBuf getPacket() {
        return packet;
    }
    
}
