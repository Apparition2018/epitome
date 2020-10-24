package knowledge.加解密和消息摘要.消息摘要算法;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA (Secure Hash Algorithm，安全散列算法）
 * <p>
 * SHA 家族的五个算法，分别是 SHA-1、SHA-224、SHA-256、SHA-384，和 SHA-512。后四者有时并称为 SHA-2。由美国国家安全局 (NSA) 所设计。
 * <p>
 * 算法    一个word长度(bits) 循环次数       碰撞攻击
 * SHA-0 			32			80		    是
 * SHA-1			32			80	    存在263的攻击
 * SHA-256/224		32			64	      尚未出现
 * SHA-512-384		64			80	      尚未出现
 * <p>
 * SHA-1 和 MD5 比较：
 * 同：二者均由 MD4 导出，SHA-1 和 MD5 彼此很相似
 * 异：
 * 1.对强行攻击的安全性：SHA-1 摘要比 MD5 摘要长32 位
 * 2.对密码分析的安全性：由于 MD5 的设计，易受密码分析的攻击，SHA-1 显得不易受这样的攻击
 * 3.速度：MD5 比 SHA-1 快
 */
public class SHADemo {

    public static void main(String[] args) {

        try {
            String src = "Hello World!";
            System.out.println("加密前数据：" + src);

            MessageDigest sha = MessageDigest.getInstance("SHA-384");

            sha.update(src.getBytes(StandardCharsets.UTF_8));

            byte[] bytes = sha.digest();

            System.out.println("SHA-512加密：" + new BigInteger(1, bytes).toString(32));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }

}
