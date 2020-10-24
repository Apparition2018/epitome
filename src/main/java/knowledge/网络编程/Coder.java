package knowledge.网络编程;

import l.demo.Demo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Coder extends Demo {

    public static void main(String[] args) throws UnsupportedEncodingException {

        // static String	    encode(String s, String enc)
        // 使用指定的编码机制将字符串转换为 application/x-www-form-urlencoded 格式
        String encode = URLEncoder.encode("李白", UTF_8);
        p(encode);

        // static String	    decode(String s, String enc)
        // 使用指定的编码机制对 application/x-www-form-urlencoded 字符串解码
        String decode = URLDecoder.decode(encode, UTF_8);
        p(decode);
    }
}
