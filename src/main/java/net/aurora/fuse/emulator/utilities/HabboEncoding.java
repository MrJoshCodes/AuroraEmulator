package net.aurora.fuse.emulator.utilities;

/**
 * Taken from Ion by Nillus
 */
/**
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <crowlie@hybridcore.net> wrote this file. As long as you retain this notice
 * you can do whatever you want with this stuff. If we meet some day, and you
 * think this stuff is worth it, you can buy me a beer in return Crowley.
 */
public class HabboEncoding {

    public static byte B64_NEGATIVE = 64; // '@'
    public static byte B64_POSITIVE = 65; // 'A'

    public final static byte WIRE_NEGATIVE = 72;
    public final static byte WIRE_POSITIVE = 73;
    public final static int MAX_INTEGER_BYTE_AMOUNT = 6;

    public static byte[] encodeB64(int i, int numBytes) {
        byte[] result = new byte[numBytes];

        for (int j = 1; j <= numBytes; j++) {
            int k = ((numBytes - j) * 6);
            result[j - 1] = (byte) (0x40 + ((i >> k) & 0x3f));
        }

        return result;
    }

    public static int decodeB64(byte[] data) {
        int i = 0;
        int j = 0;

        for (int k = (data.length - 1); k >= 0; k--) {
            int x = (data[k] - 0x40);

            if (j > 0) {
                x *= (int) Math.pow(64.0, (double) j);
            }

            i += x;
            j++;
        }

        return i;
    }

    public static byte[] encodeWire(int i) {
        byte[] wf = new byte[MAX_INTEGER_BYTE_AMOUNT];

        int pos = 0;
        int numBytes = 1;
        int startPos = pos;
        int negativeMask = i >= 0 ? 0 : 4;
        i = Math.abs(i);

        wf[pos++] = (byte) (64 + (i & 3));

        for (i >>= 2; i != 0; i >>= MAX_INTEGER_BYTE_AMOUNT) {
            numBytes++;
            wf[pos++] = (byte) (64 + (i & 0x3f));
        }

        wf[startPos] = (byte) (wf[startPos] | numBytes << 3 | negativeMask);
        byte[] bytes = new byte[numBytes];

        System.arraycopy(wf, 0, bytes, 0, numBytes);

        return bytes;
    }
    
    public static int[] decodeWire(byte[] bytes) {
        int[] returnValue = new int[2];
        int pos = 0;
        int v = bytes[pos] & 3;

        boolean negative = (bytes[pos] & 4) == 4;
        returnValue[1] = bytes[pos] >> 3 & 7;

        pos++;

        int shiftAmount = 2;

        for (int b = 1; b < returnValue[1]; b++) {
            v |= (bytes[pos] & 0x3f) << shiftAmount;
            shiftAmount = 2 + 6 * b;
            pos++;
        }

        if (negative) {
            v *= -1;
        }
        
        returnValue[0] = v;

        return returnValue;
    }
}
