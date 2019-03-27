package knowledge.api.net.coder;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CoderDemo {

    /**
     * static String	encode(String s)
     * 已过时。 结果字符串可能因平台默认编码不同而不同。因此，改用 encode(String,String) 方法指定编码
     *
     * static String	encode(String s, String enc)
     * 使用指定的编码机制将字符串转换为 application/x-www-form-urlencoded 格式
     */
    @Test
    public void encode() throws UnsupportedEncodingException {
        String str = "李白";

        String result = URLEncoder.encode(str, "UTF-8");
        System.out.println(result);

        str = URLDecoder.decode(result, "UTF-8");
        System.out.println(str);
    }

    /**
     * static String	decode(String s, String enc)
     * 使用指定的编码机制对 application/x-www-form-urlencoded 字符串解码
     */
    @Test
    public void decode() throws UnsupportedEncodingException {
        encode();
    }

}
