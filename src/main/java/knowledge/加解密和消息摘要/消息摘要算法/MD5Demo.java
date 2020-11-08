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

/**
 * MD5 即 Message-Digest Algorithm 5（信息-摘要算法5），用于确保信息传输完整一致。
 * MD5 是输入不定长度信息，输出固定长度 128-bits 的算法。
 * 严格意义上来讲，MD5 以及 SHA1 并不属于加密算法，也不属于签名算法，而是一种摘要算法，用于数据完整性校验等
 * <p>
 * 特点：
 * 1、压缩性：任意长度的数据，算出的MD5值长度都是固定的，即 128 位。
 * 2、容易计算：从原数据计算出 MD5 值很容易。
 * 3、抗修改性：对原数据进行任何改动，哪怕只修改1个字节，所得到的 MD5 值都有很大区别。
 * 4、弱抗碰撞：已知原数据和其 MD5 值，想找到一个具有相同MD5值的数据（即伪造数据）是非常困难的。
 * 5、强抗碰撞：想找到两个不同的数据，使它们具有相同的MD5值，是非常困难的。
 * 4、5要特别介绍一下。MD5 使用的是散列函数（也称哈希函数），一定概率上也存在哈希冲突（也称哈希碰撞），即多个不同的原数据对应一个相同的 MD5 值。
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
public class MD5Demo extends Demo {

    @Test
    public void testMD5() throws NoSuchAlgorithmException {

        // 获取MessageDigest对象，参数为MD5字符串，表示这是一个MD5算法（还有SHA1算法等）
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        // update(bytes[])类似StringBuilder对象的append()方法，追加模式，属于一个累计更改的过程
        md5.update(HELLO_WORLD.getBytes(StandardCharsets.UTF_8));

        // digest()被调用后，MessageDigest对象就被重置，即不能连续再次调用该方法计算原数据的MD5值（可以手动调用reset()方法重置输入源）
        // digest()返回16位长度的哈希值，由byte[]承接
        byte[] bytes = md5.digest();

        // 等于以上两个步骤
        // byte[] bytes = md5.digest(HELLO_WORLD.getBytes(StandardCharsets.UTF_8));

        // 加密后的bytes[]再转成16进制字符串
        p("MD5：" + new BigInteger(1, bytes).toString(16));

    }

    /**
     * 加盐加密
     * primary key + SRC
     * 例如：id + password
     */
    @Test
    public void testSaltEncryption() throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = md5.digest(("id" + HELLO_WORLD).getBytes(StandardCharsets.UTF_8)); // 加盐
        p("Salt MD5：" + new BigInteger(1, bytes).toString(16));
    }

    /**
     * 获取文件 MD5
     */
    @Test
    public void test4() throws IOException, NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        FileInputStream fis = new FileInputStream(DEMO_FILE_PATH);
        byte[] buf = new byte[1024];
        int len;
        while ((len = fis.read(buf)) != -1) {
            md5.update(buf, 0, len);
        }
        byte[] bytes = md5.digest();
        p("File MD5：" + new BigInteger(1, bytes).toString(16));
        fis.close();
    }

    /**
     * spring md5
     * commons-codec md5
     */
    @Test
    public void test5() {
        p("Spring MD5：" + DigestUtils.md5DigestAsHex(HELLO_WORLD.getBytes()));
        p("commons-codec MD5：" + org.apache.commons.codec.digest.DigestUtils.md5Hex(HELLO_WORLD));
    }

}
