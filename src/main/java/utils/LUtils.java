package utils;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

public class LUtils {

    public LUtils() {
        // 防止用户通过实力对象访问，而不是通过类名访问
        throw new Error("Don't instantiate " + getClass());
    }

    // 简写 System.out.println()
    public static <T> void p(T obj) {
        if (obj == null) return;
        // 数组
        if (obj.getClass().isArray()) {
            if (obj instanceof long[]) {
                System.out.println(Arrays.toString((long[]) obj));
                return;
            }
            if (obj instanceof int[]) {
                System.out.println(Arrays.toString((int[]) obj));
                return;
            }
            if (obj instanceof short[]) {
                System.out.println(Arrays.toString((short[]) obj));
                return;
            }
            if (obj instanceof char[]) {
                System.out.println(Arrays.toString((char[]) obj));
                return;
            }
            if (obj instanceof byte[]) {
                System.out.println(Arrays.toString((byte[]) obj));
                return;
            }
            if (obj instanceof boolean[]) {
                System.out.println(Arrays.toString((boolean[]) obj));
                return;
            }
            if (obj instanceof float[]) {
                System.out.println(Arrays.toString((float[]) obj));
                return;
            }
            System.out.println(Arrays.toString((Object[]) obj));
            return;
        }
        // 日期时间
        if (obj instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            System.out.println(sdf.format(obj));
            return;
        }
        System.out.println(obj);
    }

    // 判断操作系统是否为Windows
    public static boolean isWindows() {
        String os = System.getProperty("os.name");
        return os != null && os.toLowerCase().contains("windows");
    }

    // 获取IP地址
    public static String getHostAddress() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    // 16进制字符串 → 字节数组
    public static byte[] hex2Byte(String s) {
        byte[] src = s.toLowerCase().getBytes();
        byte[] ret = new byte[src.length / 2];
        for (int i = 0; i < src.length; i += 2) {
            byte hi = src[i];
            byte low = src[i + 1];
            hi = (byte) ((hi >= 'a' && hi <= 'f') ? 0x0a + (hi - 'a')
                    : hi - '0');
            low = (byte) ((low >= 'a' && low <= 'f') ? 0x0a + (low - 'a')
                    : low - '0');
            ret[i / 2] = (byte) (hi << 4 | low);
        }
        return ret;
    }

    // byte[] → 16进制字符串，通过位运算
    public static String bytes2Hex1(byte[] bytes) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F'};
        char[] out = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            byte c = bytes[i];
            out[i * 2] = Digit[(c >>> 4) & 0X0F];
            out[i * 2 + 1] = Digit[c & 0X0F];
        }

        return new String(out);
    }

    // byte[] → 16进制字符串，通过BigInteger
    public static String bytes2Hex2(byte[] bytes) {
        BigInteger bi = new BigInteger(1, bytes);
        return bi.toString(16);
    }

    // byte[] → 16进制字符串，通过...
    public static String bytes2Hex3(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            int temp = 0xff & aByte; // 此处为什么添加Oxff?， https://blog.csdn.net/iblade/article/details/73288822
            String hexStr = Integer.toHexString(temp);
            if (hexStr.length() == 1) { // 如果是十六进制的0f，默认只显示f，此时要补上0
                sb.append("0").append(hexStr);
            } else {
                sb.append(hexStr);
            }
        }
        return sb.toString();
    }

}