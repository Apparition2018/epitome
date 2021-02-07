package jar.hutool.extra;

import cn.hutool.extra.ftp.Ftp;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Ftp      FTP 封装
 * 需要引入 commons-net:commons-net
 * https://hutool.cn/docs/#/extra/CommonsNet%E5%B0%81%E8%A3%85/FTP%E5%B0%81%E8%A3%85-Ftp
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/extra/ftp/Ftp.html
 *
 * @author ljh
 * created on 2020/11/9 16:11
 */
public class FtpDemo {
    
    @Test
    public void testFtp() throws IOException {
        Ftp ftp = new Ftp("");
        ftp.close();
    }
}
