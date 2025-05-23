package jar.hutool.codec;

import cn.hutool.core.codec.Caesar;
import cn.hutool.core.codec.Morse;
import cn.hutool.core.codec.Rot;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

/**
 * Codec
 *
 * @author ljh
 * @since 2020/11/19 14:58
 */
public class CodecDemo extends Demo {

    private static String encode;
    private static String decode;
    int offset = 1;

    /**
     * <a href="https://github.com/zhaorenjie110/SymmetricEncryptionAndDecryption">凯撒密码</a>
     * <p>一种最简单且最广为人知的加密技术，它是一种替换加密的技术，明文中的所有字母都在字母表上向后（或向前）按照一个固定数目进行偏移后被替换成密文。
     *
     * @see <a href="https://plus.hutool.cn/apidocs/cn/hutool/core/codec/Caesar.html">Caesar api</a>
     */
    @Test
    public void testCaesar() {
        encode = Caesar.encode(HELLO_WORLD, offset);
        p(encode);  // hFMMP wPSME!

        decode = Caesar.decode(encode, offset);
        p(decode);
    }

    /**
     * <a href="https://github.com/orclight/jencrypt">ROT密码</a>
     * <p>凯撒密码的一种变体
     *
     * @see <a href="https://www.cnblogs.com/swordcreater/p/12562077.html">rot位移密码详解</a>
     * @see <a href="https://plus.hutool.cn/apidocs/cn/hutool/core/codec/Rot.html">Rot api</a>
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
     * <a href="https://github.com/TakWolf/Java-MorseCoder">摩斯密码</a>
     * <p>一种时通时断的信号代码，通过不同的排列顺序来表达不同的英文字母、数字和标点符号。
     *
     * @see <a href="https://plus.hutool.cn/apidocs/cn/hutool/core/codec/Morse.html">Morse api</a>
     */
    @Test
    public void testMorse() {
        Morse morse = new Morse();

        encode = morse.encode(HELLO_WORLD);
        p(encode);  // ...././.-../.-../---/-...../.--/---/.-./.-../-../-.-.--/

        decode = morse.decode(encode);
        p(decode);
    }
}
