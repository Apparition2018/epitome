package knowledge.加解密和消息摘要.消息摘要算法;

import l.demo.Demo;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * MessageDigest    消息摘要
 * MessageDigest 为应用程序提供信息摘要算法的功能。
 * 消息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
 * 消息摘要算法是不可逆的，理论上无法通过反向运算取得原数据内容，因此它通常只能被用来做数据完整性验证。
 * 常见消息摘要有：
 * MD2, MD4, MD5, SHA, SHA-1, SHA-256, SHA-384, SHA-512,
 * HmacMD5, HmacSHA1, HmacSHA256, HmacSHA384, HmacSHA512, HAVAL
 * https://jdk6.net/security/MessageDigest.html
 * https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#MessageDigest
 *
 * @author Arsenal
 * created on 2020/11/8 19:04
 */
public class MessageDigestDemo extends Demo {

    private static MessageDigest md5;

    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 一般消息摘要
     */
    public static void testMessageDigest(final String MESSAGE_DIGEST) throws NoSuchAlgorithmException {
        // static MessageDigest     getInstance(String algorithm[, Provider/String provider])
        // 返回实现指定摘要算法的 MessageDigest 对象
        MessageDigest messageDigest = MessageDigest.getInstance(MESSAGE_DIGEST);

        byte[] bytes;
        if (new Random().nextBoolean()) {
            // void	                update(byte[] input[, int offset, int len])
            // 使用指定的 byte 数组，从指定的偏移量开始更新摘要；类似 StringBuilder 的 append()，追加模式
            messageDigest.update(HELLO_WORLD.getBytes(StandardCharsets.UTF_8));

            // byte[]               digest()
            // 通过执行诸如填充之类的最终操作完成哈希计算，返回16位长度的哈希值
            // digest() 调用后，MessageDigest 就被重置，即不能连续调用该方法计算原数据的哈希值，可以手动调用 reset() 重置输入源
            bytes = messageDigest.digest();
        } else {
            // byte[]               digest(byte[] input)
            // 使用指定的 byte[] 对摘要进行最后更新，然后完成摘要计算
            // 等于以上两个步骤
            bytes = messageDigest.digest(HELLO_WORLD.getBytes(StandardCharsets.UTF_8));
        }

        // 加密后的 bytes[] 再转成16进制字符串
        // int                       getDigestLength()
        // 返回以字节为单位的摘要长度，如果提供者不支持此操作并且实现是不可复制的，则返回 0
        p(MESSAGE_DIGEST + ": " + new BigInteger(1, bytes).toString(16));
    }

    @Test
    public void testMessageDigest() {

        // static boolean	isEqual(byte[] digesta, byte[] digestb)
        // 比较两个摘要的相等性
        p(MessageDigest.isEqual(md5.digest("1".getBytes()), md5.digest("2".getBytes())));

        // String	        getAlgorithm()      返回标识算法的独立于实现细节的字符串
        p(md5.getAlgorithm());                  // "MD5"

        // String	        toString()          返回此信息摘要对象的字符串表示形式
        p(md5.toString());                      // MD5 Message Digest from SUN, <initialized>

        // Provider	        getProvider()       返回此信息摘要对象的提供者
        p(md5.getProvider());                   // SUN version 1.8

        // void	            reset()             重置摘要以供再次使用
        md5.reset();
    }

    /**
     * 加盐消息摘要
     * Salt + SRC
     */
    @Test
    public void testSaltMessageDigest() {
        byte[] bytes = md5.digest(("Salt" + HELLO_WORLD).getBytes(StandardCharsets.UTF_8));
        p("Salt Message Digest：" + new BigInteger(1, bytes).toString(md5.getDigestLength()));
    }

    /**
     * 获取文件消息摘要
     */
    @Test
    public void testFileMessageDigest() throws IOException {
        FileInputStream fis = new FileInputStream(DEMO_FILE_PATH);
        byte[] buf = new byte[1024];
        int len;
        while ((len = fis.read(buf)) != -1) {
            md5.update(buf, 0, len);
        }
        byte[] bytes = md5.digest();
        p("File Message Digest：" + new BigInteger(1, bytes).toString(md5.getDigestLength()));
        fis.close();
    }

    /**
     * MD5 即 Message-Digest Algorithm 5（信息-摘要算法5），用于确保信息传输完整一致。
     * MD5 是输入不定长度信息，输出固定长度 128-bits 的算法。
     * 严格意义上来讲，MD5 以及 SHA1 并不属于加密算法，也不属于签名算法，而是一种摘要算法，用于数据完整性校验等
     * <p>
     * 特点：
     * 1.压缩性：任意长度的数据，算出的MD5值长度都是固定的，即 128 位。
     * 2.容易计算：从原数据计算出 MD5 值很容易。
     * 3.抗修改性：对原数据进行任何改动，哪怕只修改1个字节，所得到的 MD5 值都有很大区别。
     * 4.弱抗碰撞：已知原数据和其 MD5 值，想找到一个具有相同MD5值的数据（即伪造数据）是非常困难的。
     * 5.强抗碰撞：想找到两个不同的数据，使它们具有相同的MD5值，是非常困难的。
     * 4.5要特别介绍一下。MD5 使用的是散列函数（也称哈希函数），一定概率上也存在哈希冲突（也称哈希碰撞），即多个不同的原数据对应一个相同的 MD5 值。
     * 不过，经过 MD4、MD3 等几代算法的优化，MD5 已经充分利用散列的分散性高度避免碰撞的发生。
     * 可以看出，MD5 是一种不可逆的算法，也就说，你无法通过得到的 MD5 值逆向算出原数据内容。正是凭借这些特点，MD5 被广泛使用。
     * <p>
     * 应用：
     * 1.比如，客户端与服务器的 HTTP 通信，通信双方可以将报文内容做一个 MD5 计算，并将计算所得 MD5 值一并传递给彼此，
     * 这样，接收方可以通过对报文内容再次做 MD5 计算得到一个 MD5 值，与传递报文中的 MD5 值做比较，验证数据是否完整，或者是否中途被拦截篡改过。
     * 2.再比如，网络云盘中的文件秒传功能也运用到 MD5 算法。服务器存储文件的时候，同时记录每一个文件的 MD5 值，不同文件对应着不同的 MD5 值。
     * 这样，遇到用户上传文件时，将上传文件的 MD5 值与服务器上所有存储的 MD5 值做比较，如果相同，则说明用户上传的文件已经在服务器存有。
     * 这样，只需要在数据库表中添加一个记录，映射到对应的文件，而不用重复上传，实现所谓秒传的功能。
     * <p>
     * https://blog.csdn.net/iblade/article/details/73288822
     */
    private static class MD5Demo extends Demo {

        public static void main(String[] args) throws NoSuchAlgorithmException {
            testMessageDigest("MD5");
            p("Spring MD5：" + DigestUtils.md5DigestAsHex(HELLO_WORLD.getBytes()));
            p("commons-codec MD5：" + org.apache.commons.codec.digest.DigestUtils.md5Hex(HELLO_WORLD));
        }

    }

    /**
     * SHA (Secure Hash Algorithm，安全散列算法）
     * <p>
     * SHA 家族的五个算法，分别是 SHA-1、SHA-224、SHA-256、SHA-384，和 SHA-512。后四者有时并称为 SHA-2。由美国国家安全局 (NSA) 所设计。
     * <p>
     * 算法           输出位数    状态位     状态数     一个word长度(bits) 循环次数       碰撞攻击
     * SHA-0 			160     160         2^64 − 1        32			80		    是
     * SHA-1			160     160         2^64 − 1        32			80	    存在263的攻击
     * SHA-256/224  256/224     256         2^64 − 1        32			64	      尚未出现
     * SHA-512/384	512/384     512         2^128 − 1	    64			80	      尚未出现
     * <p>
     * SHA-1 和 MD5 比较：
     * 同：二者均由 MD4 导出，SHA-1 和 MD5 彼此很相似
     * 异：
     * 1.对强行攻击的安全性：SHA-1 摘要比 MD5 摘要长32 位
     * 2.对密码分析的安全性：由于 MD5 的设计，易受密码分析的攻击，SHA-1 显得不易受这样的攻击
     * 3.速度：MD5 比 SHA-1 快
     */
    public static class SHADemo {

        public static void main(String[] args) throws NoSuchAlgorithmException {
            testMessageDigest("SHA-256");
            p("commons-codec SHA：" + org.apache.commons.codec.digest.DigestUtils.sha256Hex(HELLO_WORLD));
        }
    }
}
