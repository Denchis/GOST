import java.math.BigInteger;
import java.nio.ByteBuffer;

public class GOST {
//    private static final byte[][] S = {
//            {0xA, 0x4, 0x5, 0x6, 0x8, 0x1, 0x3, 0x7, 0xD, 0xC, 0xE, 0x0, 0x9, 0x2, 0xB, 0xF},
//            {0x5, 0xF, 0x4, 0x0, 0x2, 0xD, 0xB, 0x9, 0x1, 0x7, 0x6, 0x3, 0xC, 0xE, 0xA, 0x8},
//            {0x7, 0xF, 0xC, 0xE, 0x9, 0x4, 0x1, 0x0, 0x3, 0xB, 0x5, 0x2, 0x6, 0xA, 0x8, 0xD},
//            {0x4, 0xA, 0x7, 0xC, 0x0, 0xF, 0x2, 0x8, 0xE, 0x1, 0x6, 0x5, 0xD, 0xB, 0x9, 0x3},
//            {0x7, 0x6, 0x4, 0xB, 0x9, 0xC, 0x2, 0xA, 0x1, 0x8, 0x0, 0xE, 0xF, 0xD, 0x3, 0x5},
//            {0x7, 0x6, 0x2, 0x4, 0xD, 0x9, 0xF, 0x0, 0xA, 0x1, 0x5, 0xB, 0x8, 0xE, 0xC, 0x3},
//            {0xD, 0xE, 0x4, 0x1, 0x7, 0x0, 0x5, 0xA, 0x3, 0xC, 0x8, 0xF, 0x6, 0x2, 0x9, 0xB},
//            {0x1, 0x3, 0xA, 0x9, 0x5, 0xB, 0x4, 0xF, 0x8, 0x6, 0x7, 0xE, 0xD, 0x0, 0x2, 0xC}
//    };

//    private static final char[][] S1 = {
//            {'4', 0xA, 0x9, 0x2, 0xD, 0x8, 0x0, 0xE, 0x6, 0xB, 0x1, 0xC, 0x7, 0xF, 0x5, 0x3},
//            {0xE, 0xB, 0x4, 0xC, 0x6, 0xD, 0xF, 0xA, 0x2, 0x3, 0x8, 0x1, 0x0, 0x7, 0x5, 0x9},
//            {0x5, 0x8, 0x1, 0xD, 0xA, 0x3, 0x4, 0x2, 0xE, 0xF, 0xC, 0x7, 0x6, 0x0, 0x9, 0xB},
//            {0x7, 0xD, 0xA, 0x1, 0x0, 0x8, 0x9, 0xF, 0xE, 0x4, 0x6, 0xC, 0xB, 0x2, 0x5, 0x3},
//            {0x6, 0xC, 0x7, 0x1, 0x5, 0xF, 0xD, 0x8, 0x4, 0xA, 0x9, 0xE, 0x0, 0x3, 0xB, 0x2},
//            {0x4, 0xB, 0xA, 0x0, 0x7, 0x2, 0x1, 0xD, 0x3, 0x6, 0x8, 0x5, 0x9, 0xC, 0xF, 0xE},
//            {0xD, 0xB, 0x4, 0x1, 0x3, 0xF, 0x5, 0x9, 0x0, 0xA, 0xE, 0x7, 0x6, 0x8, 0x2, 0xC},
//            {0x1, 0xF, 0xD, 0x0, 0x5, 0x7, 0xA, 0x4, 0x9, 0x2, 0x3, 0xE, 0x6, 0xB, 0x8, 0xC},
//    };
    private static final char[][] S1 = {
        	{'4','a','9','2','d','8','0','e','6','b','1','c','7','f','5','3'},
        	{'e','b','4','c','6','d','f','a','2','3','8','1','0','7','5','9'},
        	{'5','8','1','d','a','3','4','2','e','f','c','7','6','0','9','b'},
        	{'7','d','a','1','0','8','9','f','e','4','6','c','b','2','5','3'},
        	{'6','c','7','1','5','f','d','8','4','a','9','e','0','3','b','2'},
        	{'4','b','a','0','7','2','1','d','3','6','8','5','9','c','f','e'},
        	{'d','b','4','1','3','f','5','9','0','a','e','7','6','8','2','c'},
        	{'1','f','d','0','5','7','a','4','9','2','3','e','6','b','8','c'}
};

    private static final byte[][] S = {
            {0x4, 0xA, 0x9, 0x2, 0xD, 0x8, 0x0, 0xE, 0x6, 0xB, 0x1, 0xC, 0x7, 0xF, 0x5, 0x3},
            {0xE, 0xB, 0x4, 0xC, 0x6, 0xD, 0xF, 0xA, 0x2, 0x3, 0x8, 0x1, 0x0, 0x7, 0x5, 0x9},
            {0x5, 0x8, 0x1, 0xD, 0xA, 0x3, 0x4, 0x2, 0xE, 0xF, 0xC, 0x7, 0x6, 0x0, 0x9, 0xB},
            {0x7, 0xD, 0xA, 0x1, 0x0, 0x8, 0x9, 0xF, 0xE, 0x4, 0x6, 0xC, 0xB, 0x2, 0x5, 0x3},
            {0x6, 0xC, 0x7, 0x1, 0x5, 0xF, 0xD, 0x8, 0x4, 0xA, 0x9, 0xE, 0x0, 0x3, 0xB, 0x2},
            {0x4, 0xB, 0xA, 0x0, 0x7, 0x2, 0x1, 0xD, 0x3, 0x6, 0x8, 0x5, 0x9, 0xC, 0xF, 0xE},
            {0xD, 0xB, 0x4, 0x1, 0x3, 0xF, 0x5, 0x9, 0x0, 0xA, 0xE, 0x7, 0x6, 0x8, 0x2, 0xC},
            {0x1, 0xF, 0xD, 0x0, 0x5, 0x7, 0xA, 0x4, 0x9, 0x2, 0x3, 0xE, 0x6, 0xB, 0x8, 0xC},
    };


    public static byte hexStringToByteArray1(char s) {
        byte data = 0;
        switch (s){
            case ('1'):
                data = 1;
                break;
            case ('2'):
                data = 2;
                break;
            case ('3'):
                data = 3;
                break;
            case ('4'):
                data = 4;
                break;
            case ('5'):
                data = 5;
                break;
            case ('6'):
                data = 6;
                break;
            case ('7'):
                data = 7;
                break;
            case ('8'):
                data = 8;
                break;
            case ('9'):
                data = 9;
                break;
            case ('a'):
                data = 10;
                break;
            case ('b'):
                data = 11;
                break;
            case ('c'):
                data = 12;
                break;
            case ('d'):
                data = 13;
                break;
            case ('e'):
                data = 14;
                break;
            case ('f'):
                data = 15;
                break;
            case ('0'):
                data = 0;
                break;

        }
        return data;
    }

    public  static String ShiftRightString(String key, int n) {
        StringBuilder shafting = new StringBuilder();
        shafting.append(key.substring(n,32)).append(key.substring(0,n));
        return shafting.toString();
    }
    public static String GetBitsStrofChar(int c){
        StringBuilder code = new StringBuilder();
        code.append(Integer.toBinaryString(c));
        while (code.length() < 32){
            code.insert(0,"0");
        }
        return code.toString();
    }


    private static byte[] E_f(byte[] sample, byte[] key) {
        byte[] result = new byte[4];
        //System.out.println("test_key: " + byteArrayToHex(key));
        //System.out.println("test_sample: " + byteArrayToHex(sample));
        int _sample = 0;
        int _key = 0;

//        for (int i = 0; i < sample.length; ++i) {
//            _sample ^= sample[i];
//            if (i != sample.length-1)
//                _sample <<= 8;
//
//            _key ^= key[i];
//            if (i != sample.length-1)
//                _key <<= 8;
//        }
        _key =   ((0xFF & key[0]) << 24)
                | ((0xFF & key[1]) << 16)
                | ((0xFF & key[2]) << 8)
                | (0xFF & key[3]);
        _sample =   ((0xFF & sample[0]) << 24)
                | ((0xFF & sample[1]) << 16)
                | ((0xFF & sample[2]) << 8)
                | (0xFF & sample[3]);
        //System.out.println("key " +_key);
        //System.out.println("sample " + _sample);
        byte[] bytes = ByteBuffer.allocate(4).putInt(_key).array();
//        for (byte b : bytes) {
//            System.out.format("0x%x ", b);
//        }

        BigInteger bg = BigInteger.valueOf(_sample);
        BigInteger bg1 = BigInteger.valueOf(_key);
        BigInteger mod = BigInteger.valueOf(65536);
        mod = mod.multiply(mod);

        int sum = bg.add(bg1).mod(mod).intValue();
        //int sum = _sample + _key;
        //System.out.println("sum_E_f: " + sum);
        byte[] bytess = ByteBuffer.allocate(4).putInt(sum).array();
//        for (byte b : bytesr) {
//            System.out.format("0x%x ", b);
//        }
        int newSum = 0;

        String str1 = byteArrayToHex(bytess);
        //System.out.println("str1=" + str1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++){
            byte k = hexStringToByteArray1(str1.charAt(i));
            sb.append(S1[7-i][k]);
            //System.out.println("SSS: "+(7-i)+ " " + k);
        }
        String str2 = sb.toString();
        //System.out.println("test: " + str2);
        byte[] bytes2 = hexStringToByteArray(str2);

        int _bytes2 = ByteBuffer.wrap(bytes2).getInt();


        byte[] bytes11 = ByteBuffer.allocate(4).putInt(_bytes2).array();
        //System.out.print("posle perestanovki111= " + byteArrayToHex(bytes11));


        byte[] bytes1123 = ByteBuffer.allocate(4).putInt(_bytes2).array();
        //System.out.print("\n posle perestanovki= " + byteArrayToHex(bytes1123));

        //_bytes2 = (_bytes2 << 11);
        String str = GetBitsStrofChar(_bytes2);
        str = ShiftRightString(str,11);
        _bytes2 = getCode(str);

//        masks = new int[4];
//        masks[0] = 0b00000000000000000000000011111111;
//        masks[1] = 0b00000000000000001111111100000000;
//        masks[2] = 0b00000000111111110000000000000000;
//        masks[3] = 0b11111111000000000000000000000000;

        result = ByteBuffer.allocate(4).putInt(_bytes2).array();
//        for (int i = 0; i < result.length; ++i)
//            result[i] = (byte) ((_bytes2 & masks[i]) >> i * 8);
        //System.out.println("E_f= " + byteArrayToHex(result));
        return result;
    }

    public static int getCode(String k){
        int buf = 0;
        for (int i =0; i < k.length(); i++){
            if (k.charAt(i) == '1'){
                buf = buf | 1<<k.length()-i-1;
            }
        }
        return (int)buf;
    }


    private static byte[] E(byte[] sample, byte[] key) {
        byte[] result = new byte[8];

        byte[] A = new byte[4];
        byte[] B = new byte[4];

        System.arraycopy(sample, 0, B, 0, 4);
        System.arraycopy(sample, 4, A, 0, 4);
        //System.out.println("A=" + byteArrayToHex(A) + " B = " + byteArrayToHex(B));

        byte[][] keys = new byte[8][4];

        for (int i = 0; i < 8; ++i) {
            System.arraycopy(key, i * 4, keys[7-i], 0, 4);
            //System.out.println("key" + i + "=" + byteArrayToHex(keys[7-i]) + "  h= " + byteArrayToHex(sample));
        }

        byte[] tmp;
        for (int round = 0; round < 24; ++round) {
            //System.out.println("round" + round + "=" + byteArrayToHex(A) + "  h= " + byteArrayToHex(keys[round % 8]));
            byte[] tmp1 = E_f(A, keys[round % 8]);
            tmp = Utils.xor(B, tmp1);
            System.arraycopy(A, 0, B, 0, A.length);
            System.arraycopy(tmp, 0, A, 0, tmp.length);
            //System.out.println("testA: " + byteArrayToHex(A));
            //System.out.println("testB: " + byteArrayToHex(B));
        }

        for (int round = 24; round < 32; round++) {
            tmp = Utils.xor(B, E_f(A, keys[7-(round % 8)]));
            System.arraycopy(A, 0, B, 0, A.length);
            System.arraycopy(tmp, 0, A, 0, tmp.length);
            //System.out.println("testA: " + byteArrayToHex(A));
            //System.out.println("testB: " + byteArrayToHex(B));
        }

        System.arraycopy(A, 0, result, 0, A.length);
        System.arraycopy(B, 0, result, 4, B.length);

        //System.out.println("S =" + byteArrayToHex(A) + "+" + byteArrayToHex(B));
        return result;
    }

    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static int phi(int original) {
        int i = original % 4;
        int k = original / 4;
        return 8 * i + k;
    }

    private static byte[] P(byte[] sample) {
        byte[] result = new byte[sample.length];

        for (int i = 0; i < 32; ++i)
            result[i] = sample[phi(i)];

        return result;
    }

    private static byte[] A(byte[] sample) {
        byte[] result = new byte[sample.length];

        for (int i = 0; i < 8; ++i)
            result[i] = (byte) (sample[i + 16] ^ sample[i + 24]);

        for (int i = 0; i < 24; ++i)
            result[i+8] = sample[i];
//        System.arraycopy(sample, 8, result, 0, 24);

        return result;
    }

    public static byte[] f(byte[] Hin, byte[] m) {
        final byte[][] C = new byte[3][32];

        // KEYS GENERATION
       C[1] = hexStringToByteArray("ff00ffff000000ffff0000ff00ffff0000ff00ff00ff00ffff00ff00ff00ff00");
//        C[1] = new byte[]{
//                0x00, (byte) 0xFF, 0x00, (byte) 0xFF, 0x00,
//                (byte) 0xFF, 0x00, (byte) 0xFF, (byte) 0xFF,
//                0x00, (byte) 0xFF, 0x00, (byte) 0xFF, 0x00,
//                (byte) 0xFF, 0x00, 0x00, (byte) 0xFF, (byte) 0xFF,
//                0x00, (byte) 0xFF, 0x00, 0x00, (byte) 0xFF,
//                (byte) 0xFF, 0x00, 0x00, 0x00, (byte) 0xFF,
//                (byte) 0xFF, 0x00, (byte) 0xFF
//        };

        byte[] U = new byte[32];
        byte[] V = new byte[32];
        byte[] W = new byte[32];
        byte[][] K = new byte[4][32];
       // System.out.println("m_do" + ":= " + byteArrayToHex(m));
        System.arraycopy(Hin, 0, U, 0, Hin.length);
        System.arraycopy(m, 0, V, 0, m.length);

        for (int i = 0; i < 32; ++i)
            W[i] = (byte) (U[i] ^ V[i]);
        K[0] = P(W);

        for (int i = 1; i < 4; ++i) {
            U = Utils.xor(A(U), C[i - 1]);
            V = A(A(V));
            W = Utils.xor(U, V);
            K[i] = P(W);
        }

        // CIPHER TRANSITION
        byte[][] h = new byte[4][8];
        byte[][] s = new byte[4][8];

        //System.out.println("Hin_posle" + ":= " + byteArrayToHex(m));
        for (int i = 0; i < 4; ++i)
            System.arraycopy(Hin, i * 8, h[i], 0, 8);

//        for (int i = 0; i < 4; ++i)
//            System.out.println("h" + i + ":= " + byteArrayToHex(h[i]));

        for (int i = 0; i < 4; ++i) {
            s[i] = E(h[3-i], K[i]);
        }

        byte[] S32 = new byte[32];
        for (int i = 0; i < 4; ++i)
            System.arraycopy(s[3-i], 0, S32, i * 8, 8);
        //System.out.println("do peremesh: "+ byteArrayToHex(S32));
        S32 = psi(S32, 12);
        //System.out.println("psi1: "+ byteArrayToHex(S32));
        for (int i = 0; i < 32; ++i)
            S32[i] ^= m[i];

        S32 = psi(S32, 1);
        for (int i = 0; i < 32; ++i)
            S32[i] ^= Hin[i];

        S32 = psi(S32, 61);
        //System.out.println("posle peremesh:=" + byteArrayToHex(S32));
        return S32;
    }

        private static byte[] psi(byte[] sample, int rounds) {
        byte[] res = new byte[32];
        System.arraycopy(sample,0,res, 0,sample.length);
        for (; rounds > 0; --rounds){
            res = psi(res);
        }
        return res;
    }

    private static byte[] psi(byte[] sample){
        byte[] res = new byte[32];
        for (int i = 0; i < 2; i ++){
            res[i] = (byte)((int)sample[i] ^ (int)sample[30+i] ^
                    (int)sample[28+i] ^ (int)sample[26+i] ^ (int)sample[24+i] ^ (int)sample[6+i]);
        }
        for (int i = 0; i < 30; i++){
            res[i+2] = sample[i];
        }
        return res;
    }

//    private static void psi(byte[] sample, int rounds) {
//        for (; rounds >= 0; --rounds)
//            psi(sample);
//    }
//
//    private static void psi(byte[] sample) {
//        byte[] y = new byte[sample.length];
//
//        byte value = 0;
//        for (byte b : sample) value ^= b;
//
//        System.arraycopy(sample, 1, y, 0, sample.length - 1);
//        y[sample.length - 1] = value;
//
//        System.arraycopy(y, 0, sample, 0, y.length);
//
//    }



    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static byte[][] invert(byte[][]blocks){
        byte[][] res = new byte[blocks.length][blocks[0].length];
        for (int i = 0; i < blocks.length; i++){
            for (int j = 0; j < blocks[0].length; j++){
                res[i][j] = blocks[i][blocks[0].length-j-1];
            }
        }
        return res;
    }

    public static byte[] invert(byte[]blocks){
        byte[] res = new byte[blocks.length];
        for (int i = 0; i < blocks.length; i++){
            res[i] = blocks[blocks.length-i-1];
        }
        return res;
    }

    public static byte[] hash(byte[] buf) {
        int n;
        //buf = invert(buf);
        byte[][] blocks;
        byte[] sum = new byte[32];
        int L = 0;
        byte[] H = new byte[32];
        int len = buf.length;
        n = (len <= 32) ? 1 : (len/32 + 1);
        blocks = new byte[n][32];
        //System.out.println("dlina: " + buf.length + " n = " + n);
        byte[] newBuf = new byte[32 * n];
        System.arraycopy(buf, 0, newBuf, 0, buf.length);

        for (int i = 0; i < n; ++i)
            System.arraycopy(newBuf, i * 32, blocks[i], 0, 32);

        //System.out.println("len: " + blocks.length + " len1: " + blocks[0].length);
        blocks = invert(blocks);
        //System.out.println("antiM= " + byteArrayToHex(blocks[0]));
        //System.out.println("H= " + byteArrayToHex(H) + " ");
        //System.out.println("M= " + byteArrayToHex(blocks[0]) + " ");

        for (int i = 0; i <= n - 1; ++i) {
            H = f(H, blocks[i]);
            if (i != (n-1)) {
                L += 256;
            }
            else {
                L += buf.length * 8-L;
            }
            for (int j = 0; j < 32; ++j)
                sum[j] = (byte) (sum[j] + blocks[i][j]);
        }
        byte[] tmpbytes = ByteBuffer.allocate(4).putInt(L).array();
        byte[] Lbytes = new byte[32];
        for (int i = 28; i < 32; i++){
            Lbytes[i] = tmpbytes[i-28];
        }
       // System.out.println("H= " + byteArrayToHex(H) + " ");
        //System.out.println("L= " + byteArrayToHex(Lbytes) + " ");
        //H = f(H, blocks[blocks.length - 1]);
       //Lbytes = hexStringToByteArray("000000000000000000000000000000000000000000000000000000000000000");
        H = f(H, Lbytes);
        //H = hexStringToByteArray("2b6ec233c7bc89e42abc26925fea7285dd3848d1c6ac997a24f74e2b09a3aef7");
        //System.out.println("H= " + byteArrayToHex(H) + " ");
        //System.out.println("sum= " + byteArrayToHex(sum) + " ");
        H = f(H, sum);

        return H;
    }
}

//This is message, length=32 bytes