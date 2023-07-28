package jar.apache.commons.codec.network;

import l.demo.Demo;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * <a href="https://commons.apache.org/proper/commons-codec/userguide.html#Network_Encoders">Network Encoders</a>
 *
 * @author ljh
 * @since 2020/11/15 0:18
 */
public class NetworkEncodersDemo extends Demo {

    @Test
    public void testURLCodec() throws UnsupportedEncodingException, DecoderException {
        URLCodec urlCodec = new URLCodec();

        String encode = urlCodec.encode(MY_CY, StandardCharsets.UTF_8.name());
        p(encode); // %E4%B8%AD%E5%9B%BD

        p(urlCodec.decode(encode, StandardCharsets.UTF_8.name())); // 中国
    }
}
