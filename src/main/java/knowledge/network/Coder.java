package knowledge.network;

import l.demo.Demo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Coder extends Demo {

    public static void main(String[] args) throws UnsupportedEncodingException {

        // static String	    encode(String s, String enc)
        // 使用指定的编码机制将字符串转换为 application/x-www-form-urlencoded 格式
        String encode = URLEncoder.encode(MY_NAME, UTF_8);
        p(encode); // %E6%A2%81%E6%9D%B0%E8%BE%89

        // static String	    decode(String s, String enc)
        // 使用指定的编码机制对 application/x-www-form-urlencoded 字符串解码
        String decode = URLDecoder.decode(encode, UTF_8);
        p(decode);
    }
}
