package jar.apache.commons.codec.network;

import l.demo.Demo;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

/**
 * URLCodec
 * http://commons.apache.org/proper/commons-codec/apidocs/org/apache/commons/codec/net/URLCodec.html
 *
 * @author Arsenal
 * created on 2020/11/15 0:18
 */
public class URLCodecDemo extends Demo {

    @Test
    public void testURLCodec() throws UnsupportedEncodingException, DecoderException {
        URLCodec urlCodec = new URLCodec();
        String encode = urlCodec.encode(MY_NAME, UTF_8);
        p(encode); // %E6%A2%81%E6%9D%B0%E8%BE%89

        p(urlCodec.decode(encode, UTF_8));
    }
}
