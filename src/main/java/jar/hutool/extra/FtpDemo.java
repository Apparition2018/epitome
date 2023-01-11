package jar.hutool.extra;

import cn.hutool.extra.ftp.Ftp;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * <a href="https://hutool.cn/docs/#/extra/FTP/FTP客户端封装-Ftp">Ftp</a>  FTP 封装
 * <p>需要引入 commons-net:commons-net
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/extra/ftp/Ftp.html">Ftp api</a>
 *
 * @author ljh
 * @since 2020/11/9 16:11
 */
public class FtpDemo {

    @Test
    public void testFtp() throws IOException {
        Ftp ftp = new Ftp("172.0.0.1");
        ftp.close();
    }
}
