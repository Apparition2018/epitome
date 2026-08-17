package knowledge.network;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.ljh.commons.Demo.MY_CY;
import static com.ljh.commons.Demo.p;

/**
 * URLEncoder & URLDecoder
 *
 * @author ljh
 * @since 2020/11/17 19:09
 */
public class Coder {

    public static void main(String[] args) throws UnsupportedEncodingException {
        // static String	    encode(String s, String enc)
        // 使用指定的编码机制将字符串转换为 application/x-www-form-urlencoded 格式
        String encode = URLEncoder.encode(MY_CY, StandardCharsets.UTF_8);
        p(encode); // %E4%B8%AD%E5%9B%BD

        // static String	    decode(String s, String enc)
        // 使用指定的编码机制对 application/x-www-form-urlencoded 字符串解码
        String decode = URLDecoder.decode(encode, StandardCharsets.UTF_8);
        p(decode);
    }
}
