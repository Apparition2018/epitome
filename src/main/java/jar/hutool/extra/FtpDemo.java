package jar.hutool.extra;

import cn.hutool.extra.ftp.Ftp;

import java.io.IOException;

/**
 * <a href="https://doc.hutool.cn/pages/Ftp/">FTP客户端封装-Ftp</a>
 * <p>需要引入 commons-net:commons-net
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/extra/ftp/Ftp.html">Ftp api</a>
 *
 * @author ljh
 * @since 2020/11/9 16:11
 */
public class FtpDemo {

    public static void main(String[] args) throws IOException {
        Ftp ftp = new Ftp("172.0.0.1");
        ftp.close();
    }
}
