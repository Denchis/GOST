import java.util.LinkedList;
import java.util.List;

public class Analysis {
    public static void experiment1(byte[] password) {
        byte y0 = GOST.hash(password)[0];

         //8 bit
        long iter8 = 0;
        int count = 0;
        long iter8_avg = 0;
        for (int i = 0; i < 1000; ++i) {
            while (y0 != GOST.hash(Utils.longToBytes(iter8))[0]) {
                ++iter8;
                count++;
            }
            iter8_avg += count;
            count = 0;
            iter8++;
        }
        iter8_avg /= 1000;
        count = 0;

        System.out.println(
                "8bit matches average (1000 tests): " + iter8_avg);

         //12 bit
        byte y1 = GOST.hash(password)[1];
        long iter12 = 0;
        long iter12_avg = 0;
        for (int i = 0; i < 250; ++i) {
            while (y0 != GOST.hash(Utils.longToBytes(iter12))[0] ||
                    (y1 & 0b00001111) != (GOST.hash(Utils.longToBytes(iter12))[1] & 0b00001111 )) {
                ++iter12;
                count++;
            }
            iter12_avg += count;
            count = 0;
            iter12++;
        }
        iter12_avg /= 250;
        count = 0;

        System.out.println(
                "12bit matches average (250 tests): " + iter12_avg);

        // 16 bit
        long iter16 = 0;
        long iter16_avg = 0;
        for (int i = 0; i < 10; ++i) {
            while (y0 != GOST.hash(Utils.longToBytes(iter16))[0] ||
                    y1 != GOST.hash(Utils.longToBytes(iter16))[1]) {
                ++iter16;
                count++;
            }
            iter16_avg += count;
            count = 0;
            iter16++;
        }
        iter16_avg /= 10;
        count = 0;

        System.out.println(
                "16bit matches average (10 tests): " + iter16_avg);

        // 20 bit
        byte y2 = GOST.hash(password)[2];
        long iter20 = 0;
        long iter20_avg = 0;
        for(int i = 0; i < 5; ++i) {
            while (y0 != GOST.hash(Utils.longToBytes(iter20))[0] ||
                    y1 != GOST.hash(Utils.longToBytes(iter20))[1] ||
                    (y2 & 0b00001111) != (GOST.hash(Utils.longToBytes(iter20))[2] & 0b00001111)) {
                ++iter20;
                count++;
            }
            iter20++;
            iter20_avg += count;
            count = 0;
        } iter20_avg /= 5;

        System.out.println(
                "20bit matches average (5 tests): " + iter20_avg);

        // 24 bit
        long iter24 = 0;
        long iter24_avg = 0;
        while (y0 != GOST.hash(Utils.longToBytes(iter24))[0] ||
                y1 != GOST.hash(Utils.longToBytes(iter24))[1] ||
                y2 != (GOST.hash(Utils.longToBytes(iter24))[2])) {
            ++iter24;
        }
        iter24_avg /= 5;

        System.out.println(
                "24bit matches average (1 test): " + iter24_avg);
    }

    public static void experiment2() {
        // 8bit
        List<Byte> images8 = new LinkedList<>();
        boolean found = false;

        long key8 = 0;
        long key8_avg = 0;
        int count = 1;
        for(int i = 0; i < 1000; ++i) {
            while (!found) {
                byte buf = GOST.hash(Utils.longToBytes(key8))[0];
                if (images8.contains(buf))
                    found = true;
                images8.add(buf);
                ++key8;
                ++count;
            }
            key8_avg += count;
            found = false;
            count = 1;
            ++key8;
        } key8_avg /= 1000;

        System.out.println(
                "8bit collision (1000 tests): " + key8_avg);

        images8.clear();

        // 12 bit
        List<Byte[]> images12 = new LinkedList<>();
        found = false;

        long key12 = 0;
        long key12_avg = 0;
        count = 1;
        for(int i = 0; i < 1000; ++i) {
            while (!found) {
                Byte[] buf = {0, 0};
                buf[0] = GOST.hash(Utils.longToBytes(key12))[0];
                buf[1] = GOST.hash(Utils.longToBytes(key12))[1];
                for(Byte[] b : images12) {
                    if (b[0].equals(buf[0]) && (b[1] & 0b00001111) == (buf[1] & 0b00001111)) {
                        found = true;
                        break;
                    }
                }
                images12.add(buf);
                ++key12;
                ++count;
            }
            found = false;
            key12_avg += count;
            count = 1;
            ++key12;
        } key12_avg /= 1000;

        System.out.println(
                "12bit collision (1000 tests): " + key12_avg);

        images12.clear();

        // 16 bit
        List<Byte[]> images16 = new LinkedList<>();
        found = false;

        long key16 = 0;
        long key16_avg = 0;
        count = 1;
        for(int i = 0; i < 1000; ++i) {
            while (!found) {
                Byte[] buf = {0, 0};
                buf[0] = GOST.hash(Utils.longToBytes(key16))[0];
                buf[1] = GOST.hash(Utils.longToBytes(key16))[1];
                for(Byte[] b : images16) {
                    if (b[0].equals(buf[0]) && b[1].equals(buf[1])) {
                        found = true;
                        break;
                    }
                }
                images16.add(buf);
                ++key16;
                ++count;
            }
            found = false;
            key16_avg += count;
            count = 1;
            ++key16;
        } key16_avg /= 1000;

        System.out.println(
                "16bit collision (1000 tests): "  + key16_avg);

        images16.clear();

        // 20
        List<Byte[]> images20 = new LinkedList<>();
        found = false;

        long key20 = 0;
        long key20_avg = 0;
        count = 1;
        for(int i = 0; i < 1000; ++i) {
            while (!found) {
                Byte[] buf = {0, 0, 0};
                buf[0] = GOST.hash(Utils.longToBytes(key20))[0];
                buf[1] = GOST.hash(Utils.longToBytes(key20))[1];
                buf[2] = GOST.hash(Utils.longToBytes(key20))[2];

                for(Byte[] b : images20) {
                    if (b[0].equals(buf[0]) && b[1].equals(buf[1])
                    && ((b[2] & 0b00001111) == (buf[2] & 0b00001111))) {
                        found = true;
                        break;
                    }
                }
                images20.add(buf);
                ++key20;
                ++count;
            }
            found = false;
            key20_avg += count;
            count = 1;
            ++key20;
        }

        key20_avg /= 1000;

        System.out.println(
                "20bit collision (1000 tests): " + key20_avg);

        images20.clear();

        // 24 bit
        List<Byte[]> images24 = new LinkedList<>();
        found = false;

        long key24 = 0;
        long key24_avg = 0;
        count = 1;
        for(int i = 0; i < 1000; ++i) {
            while (!found) {
                Byte[] buf = {0, 0, 0};
                buf[0] = GOST.hash(Utils.longToBytes(key24))[0];
                buf[1] = GOST.hash(Utils.longToBytes(key24))[1];
                buf[2] = GOST.hash(Utils.longToBytes(key24))[2];

                for(Byte[] b : images24) {
                    if (b[0].equals(buf[0]) && b[1].equals(buf[1])
                            && b[2].equals(buf[2])) {
                        found = true;
                        break;
                    }
                }
                images24.add(buf);
                ++key24;
                ++count;
            }
            key24_avg += count;
            found = false;
            count = 1;
            ++key24;
        } key24_avg /= 1000;

        System.out.println(
                "24bit collision (1000 tests): " + key24_avg);

        images24.clear();
    }
}
