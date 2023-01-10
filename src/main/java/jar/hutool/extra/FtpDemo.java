package jar.hutool.extra;

import cn.hutool.extra.ftp.Ftp;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * <a href="https://hutool.cn/docs/#/extra/CommonsNet封装/FTP封装-Ftp">Ftp</a>  FTP 封装
 * <p>需要引入 commons-net:commons-net
 * <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/extra/ftp/Ftp.html">Ftp api</a>
 *
 * @author ljh
 * @since 2020/11/9 16:11
 */
public class FtpDemo {

    @Test
    public void testFtp() throws IOException {
        Ftp ftp = new Ftp("");
        ftp.close();
    }
}
