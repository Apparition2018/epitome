package jar.hutool.extra;

import cn.hutool.extra.ftp.Ftp;
import org.junit.Test;

import java.io.IOException;

/**
 * Ftp
 * FTP 客户端封装，此客户端基于 apache commons-net
 * https://hutool.cn/docs/#/extra/CommonsNet%E5%B0%81%E8%A3%85/FTP%E5%B0%81%E8%A3%85-Ftp
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
