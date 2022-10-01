package cn.nukkit.math;


import java.math.BigInteger;

/**
 * @author MagicDroidX (Nukkit Project)
 */
public class NukkitMath {
    private static final Byte ZERO_BYTE = 0;
    private static final Integer ZERO_INTEGER = 0;
    private static final Short ZERO_SHORT = 0;
    private static final Long ZERO_LONG = 0L;

    public static boolean isZero(Number storage) {
        return ZERO_BYTE.equals(storage)
                || ZERO_INTEGER.equals(storage)
                || ZERO_SHORT.equals(storage)
                || ZERO_LONG.equals(storage)
                || BigInteger.ZERO.equals(storage);
    }

    public static int floorDouble(double n) {
        int i = (int) n;
        return n >= i ? i : i - 1;
    }

    public static int ceilDouble(double n) {
        int i = (int) n;
        return n > i ? i + 1 : i;
    }

    public static int floorFloat(float n) {
        int i = (int) n;
        return n >= i ? i : i - 1;
    }

    public static int ceilFloat(float n) {
        int i = (int) n;
        return n > i ? i + 1 : i;
    }

    public static int randomRange(NukkitRandom random) {
        return randomRange(random, 0);
    }

    public static int randomRange(NukkitRandom random, int start) {
        return randomRange(random, 0, 0x7fffffff);
    }

    public static int randomRange(NukkitRandom random, int start, int end) {
        return start + (random.nextInt() % (end + 1 - start));
    }

    public static double round(double d) {
        return round(d, 0);
    }

    public static double round(double d, int precision) {
        double pow = Math.pow(10, precision);
        return ((double) Math.round(d * pow)) / pow;
    }

    public static double clamp(double value, double min, double max) {
        return value < min ? min : (value > max ? max : value);
    }

    public static int clamp(int value, int min, int max) {
        return value < min ? min : (value > max ? max : value);
    }

    public static float clamp(float value, float min, float max) {
        return value < min ? min : (value > max ? max : value);
    }

    public static double getDirection(double diffX, double diffZ) {
        diffX = Math.abs(diffX);
        diffZ = Math.abs(diffZ);

        return Math.max(diffX, diffZ);
    }

    public static int bitLength(byte data) {
        if (data < 0) {
            return 32;
        }

        if (data == 0) {
            return 1;
        }

        int bits = 0;
        while (data != 0) {
            data >>>= 1;
            bits++;
        }

        return bits;
    }

    public static int bitLength(int data) {
        if (data < 0) {
            return 32;
        }

        if (data == 0) {
            return 1;
        }

        int bits = 0;
        while (data != 0) {
            data >>>= 1;
            bits++;
        }

        return bits;
    }

    public static int bitLength(long data) {
        if (data < 0) {
            return 64;
        }

        if (data == 0) {
            return 1;
        }

        int bits = 0;
        while (data != 0) {
            data >>>= 1;
            bits++;
        }

        return bits;
    }

    public static int bitLength(BigInteger data) {
        if (data.compareTo(BigInteger.ZERO) < 0) {
            throw new UnsupportedOperationException("Negative BigIntegers are not supported (nearly infinite bits)");
        }

        return data.bitLength();
    }

}
