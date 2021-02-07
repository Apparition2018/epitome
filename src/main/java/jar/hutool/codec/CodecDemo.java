package jar.hutool.codec;

import cn.hutool.core.codec.BCD;
import cn.hutool.core.codec.Caesar;
import cn.hutool.core.codec.Morse;
import cn.hutool.core.codec.Rot;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

/**
 * Codec
 *
 * @author ljh
 * created on 2020/11/19 14:58
 */
public class CodecDemo extends Demo {
    
    private static String encode;
    private static String decode;
    int offset = 1;

    /**
     * 凯撒密码
     * 一种最简单且最广为人知的加密技术，它是一种替换加密的技术，明文中的所有字母都在字母表上向后（或向前）按照一个固定数目进行偏移后被替换成密文。
     * 算法来自：https://github.com/zhaorenjie110/SymmetricEncryptionAndDecryption
     * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/codec/Caesar.html
     */
    @Test
    public void testCaesar() {
        encode = Caesar.encode(HELLO_WORLD, offset);
        p(encode);  // hFMMP wPSME!

        decode = Caesar.decode(encode, offset);
        p(decode);
    }

    /**
     * ROT密码
     * 凯撒密码的一种变体。
     * 算法来自：https://github.com/orclight/jencrypt
     * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/codec/Rot.html
     * rot位移密码详解：https://www.cnblogs.com/swordcreater/p/12562077.html
     */
    @Test
    public void testRot() {
        
        encode = Rot.encode(HELLO_WORLD + 123, offset, true);
        p(encode);  // Ifmmp Xpsme!234

        decode = Rot.decode(encode, offset, true);
        p(decode);

        encode = Rot.encode13(HELLO_WORLD + 123, true);
        p(encode);  // Uryyb Jbeyq!456

        decode = Rot.decode13(encode, true);
        p(decode);
    }

    /**
     * 摩斯密码
     * 一种时通时断的信号代码，通过不同的排列顺序来表达不同的英文字母、数字和标点符号。
     * 参考：https://github.com/TakWolf/Java-MorseCoder
     * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/codec/Morse.html
     */
    @Test
    public void testMorse() {
        Morse morse = new Morse();

        encode = morse.encode(HELLO_WORLD);
        p(encode);  // ...././.-../.-../---/-...../.--/---/.-./.-../-../-.-.--/

        decode = morse.decode(encode);
        p(decode);
    }

    /**
     * BCD (Binary-Coded Decimal)   二进码十进数
     * 一种二进制的数字编码形式，用二进制编码的十进制代码。
     * 这种编码形式利用了四个位元来储存一个十进制的数码，使二进制和十进制之间的转换得以快捷的进行。
     * 这种编码技巧最常用于会计系统的设计里。
     * 关于 BCD 编码 BCD 与十进制转换：https://www.iteye.com/blog/cuisuqiang-1429956
     * https://hutool.cn/docs/#/core/%E8%AF%AD%E8%A8%80%E7%89%B9%E6%80%A7/%E4%BA%8C%E8%BF%9B%E7%A0%81%E5%8D%81%E8%BF%9B%E6%95%B0-BCD
     * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/codec/BCD.html
     */
    @Test
    public void testBCD() {
        // ASCII String → BCD
        byte[] bcd = BCD.strToBcd("0");
        // BCD → ASCII String
        String asciiStr = BCD.bcdToStr(bcd);
    }

}
