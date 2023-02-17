package jar.hutool.util;

import cn.hutool.core.util.HexUtil;
import l.demo.Demo;

import java.awt.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

/**
 * <a href="https://hutool.cn/docs/#/core/工具类/16进制工具-HexUtil">HexUtil</a>   16进制工具
 * <p>16进制一般针对无法显示的一些二进制进行显示，常用于：
 * <pre>
 * 1 图片的字符串表现形式
 * 2 加密解密
 * 3 编码转换
 * </pre>
 * <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/util/HexUtil.html">HexUtil api</a>
 *
 * @author ljh
 * @since 2020/11/9 10:10
 */
public class HexUtilDemo extends Demo {

    public static void main(String[] args) {
        char[] encode = HexUtil.encodeHex(HELLO_WORLD.getBytes(StandardCharsets.UTF_8));
        byte[] decode = HexUtil.decodeHex(encode);

        String encodeStr = HexUtil.encodeHexStr(HELLO_WORLD);
        String decodeStr = HexUtil.decodeHexStr(encodeStr);

        String encodeColor = HexUtil.encodeColor(Color.BLACK);
        Color color = HexUtil.decodeColor(encodeColor);


        // int/long → 16进制字符串
        String hexStr = HexUtil.toHex(16);
        // 16进制字符串 → BigInteger
        BigInteger bigInteger = HexUtil.toBigInteger(hexStr);

        // char/int → unicode 字符串
        // 常用于特殊字符（例如汉字）转 Unicode 形式，转换的字符串如果 u 后不足4位，则前面用0填充
        String unicodeHex = HexUtil.toUnicodeHex(MY_CY.toCharArray()[0]);
    }
}
