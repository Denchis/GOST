import java.nio.ByteBuffer;

public class Utils {
    static final String HEXES = "0123456789ABCDEF";

    public static String convert(byte[] hash) {
        if (hash == null || hash.length < 32)
            return null;
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; ++i) {
            final byte b = hash[31 - i];
            sb.append(HEXES.charAt((b & 0xF0) >> 4))
                    .append(HEXES.charAt((b & 0x0F)));
        }
        return sb.toString();
    }

    public static void print(byte[] a) {
        for (byte b : a)
            System.out.print(b + " ");
        System.out.print("\n");
    }

    public static byte[] xor(byte[] b1, byte[] b2) {
        byte[] result = new byte[b1.length];

        for (int i = 0; i < result.length; ++i)
            result[i] = (byte) (b1[i] ^ b2[i]);

        return result;
    }

    public static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        byte[] result = new byte[32];
        System.arraycopy(buffer.array(), 0, result, 0, buffer.array().length);
        return result;
    }
}
