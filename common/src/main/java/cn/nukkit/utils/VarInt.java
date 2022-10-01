package cn.nukkit.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Tool class for VarInt or VarLong operations.
 * <p>
 * Some code from http://wiki.vg/Protocol.
 *
 * @author MagicDroidX
 * @author lmlstarqaq
 */
public final class VarInt {

    private VarInt() {
        //no instance
    }

    /**
     * @param v Signed int
     * @return Unsigned encoded int
     */
    public static long encodeZigZag32(int v) {
        // Note:  the right-shift must be arithmetic
        return ((v << 1) ^ (v >> 31)) & 0xFFFFFFFFL;
    }

    /**
     * @param v Unsigned encoded int
     * @return Signed decoded int
     */
    public static int decodeZigZag32(long v) {
        return (int) (v >> 1) ^ -(int) (v & 1);
    }

    /**
     * @param v Signed long
     * @return Unsigned encoded long
     */
    public static long encodeZigZag64(long v) {
        return (v << 1) ^ (v >> 63);
    }

    /**
     * @param v Signed encoded long
     * @return Unsigned decoded long
     */
    public static long decodeZigZag64(long v) {
        return (v >>> 1) ^ -(v & 1);
    }

    private static long read(InputStream stream, int maxSize) throws IOException {
        long value = 0;
        int size = 0;
        int b;
        while (((b = stream.read()) & 0x80) == 0x80) {
            value |= (long) (b & 0x7F) << (size++ * 7);
            if (size >= maxSize) {
                throw new IllegalArgumentException("VarLong too big");
            }
        }

        return value | ((long) (b & 0x7F) << (size * 7));
    }

    /**
     * @param stream InputStream
     * @return Signed int
     */
    public static int readVarInt(InputStream stream) throws IOException {
        return decodeZigZag32(readUnsignedVarInt(stream));
    }

    /**
     * @param stream InputStream
     * @return Unsigned int
     */
    public static long readUnsignedVarInt(InputStream stream) throws IOException {
        return read(stream, 5);
    }

    /**
     * @param stream InputStream
     * @return Signed long
     */
    public static long readVarLong(InputStream stream) throws IOException {
        return decodeZigZag64(readUnsignedVarLong(stream));
    }

    /**
     * @param stream InputStream
     * @return Unsigned long
     */
    public static long readUnsignedVarLong(InputStream stream) throws IOException {
        return read(stream, 10);
    }

    private static void write(OutputStream stream, long value) throws IOException {
        do {
            byte temp = (byte) (value & 0b01111111);
            // Note: >>> means that the sign bit is shifted with the rest of the number rather than being left alone
            value >>>= 7;
            if (value != 0) {
                temp |= 0b10000000;
            }
            stream.write(temp);
        } while (value != 0);
    }

    /**
     * @param stream OutputStream
     * @param value  Signed int
     */
    public static void writeVarInt(OutputStream stream, int value) throws IOException {
        writeUnsignedVarInt(stream, encodeZigZag32(value));
    }

    /**
     * @param stream OutputStream
     * @param value  Unsigned int
     */
    public static void writeUnsignedVarInt(OutputStream stream, long value) throws IOException {
        write(stream, value);
    }


    /**
     * @param stream OutputStream
     * @param value  Signed long
     */
    public static void writeVarLong(OutputStream stream, long value) throws IOException {
        writeUnsignedVarLong(stream, encodeZigZag64(value));
    }

    /**
     * @param stream OutputStream
     * @param value  Unsigned long
     */
    public static void writeUnsignedVarLong(OutputStream stream, long value) throws IOException {
        write(stream, value);
    }
}