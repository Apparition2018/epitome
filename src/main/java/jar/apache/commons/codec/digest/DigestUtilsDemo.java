package jar.apache.commons.codec.digest;

import l.demo.Demo;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * DigestUtils
 * http://commons.apache.org/proper/commons-codec/apidocs/org/apache/commons/codec/digest/DigestUtils.html
 */
public class DigestUtilsDemo extends Demo {

    @Test
    public void digest() {
        p(DigestUtils.md5Hex(HELLO_WORLD));     // ed076287532e86365e841e92bfc50d8c
        p(DigestUtils.sha256Hex(HELLO_WORLD));  // 7f83b1657ff1fc53b92dc18148a1d65dfc2d4b1fa3d677284addd200126d9069
    }

}
