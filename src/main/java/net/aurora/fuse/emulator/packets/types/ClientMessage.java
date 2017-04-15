package net.aurora.fuse.emulator.packets.types;

import io.netty.buffer.ByteBuf;
import net.aurora.fuse.emulator.utilities.ByteBufUtils;
import net.aurora.fuse.emulator.utilities.HabboEncoding;

/**
 * The ClientMessage class is used to read a packet.
 * @author Josh
 */
public final class ClientMessage {
    
    private final ByteBuf packet;
    private final String content;
    private final int packetID;
    
    public ClientMessage(ByteBuf packet) {
        this.packet = packet;
        this.content = new String(ByteBufUtils.getRemainingBytes(packet));
        this.packetID = readB64();
    }
    
    /**
     * Reads a wire/VL64 decoded integer from the packet.
     * @return The decoded wire/VL64 integer.
     */
    public int readVL64() {
        int[] result = HabboEncoding.decodeWire(ByteBufUtils.getRemainingBytes(packet));
        packet.readBytes(result[1]);
        
        return result[0];
    }
    
    /**
     * Reads a B64 decoded integer from the packet.
     * @return The decoded B64 integer.
     */
    public int readB64() {
        return HabboEncoding.decodeB64(ByteBufUtils.readBytes(packet, 2));
    }
    
    /**
     * Reads a B64-prefixed string from the packet.
     * @return The string.
     */
    public String readString() {
        return new String(ByteBufUtils.readBytes(packet, readB64()));
    }
    
    /**
     * Gets the full packet.
     * @return A string containing the full packet.
     */
    public String getContent() {
        return content;
    }
    
    /**
     * Gets the packet ID.
     * @return The packet ID of the current request.
     */
    public int getPacketID() {
        return packetID;
    }
    
}
