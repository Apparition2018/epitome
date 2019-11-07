package knowledge.网络编程.url;

import org.junit.Test;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * URL
 * 类 URL 代表一个统一资源定位符，它是指向互联网“资源”的指针。
 * 资源可以是简单的文件或目录，也可以是对更为复杂的对象的引用，例如对数据库或搜索引擎的查询。
 */
public class URLDemo {

    /**
     * URL(String spec)
     * 根据 String 表示形式创建 URL 对象
     */
    @Test
    public void url() throws MalformedURLException {
        URL url = new URL("https://developer.mozilla.org/en-US/search?q=URL#search-results-close-container");

        System.out.println(url.getProtocol());      // 协议名称：https
        System.out.println(url.getPort());          // 端口号：-1
        System.out.println(url.getDefaultPort());   // 默认端口号：443
        System.out.println(url.getAuthority());     // 授权部分：developer.mozilla.org
        System.out.println(url.getHost());          // 主机名：developer.mozilla.org
        System.out.println(url.getFile());          // 文件名：/en-US/search?q=URL
        System.out.println(url.getPath());          // 路径部分：/en-US/search
        System.out.println(url.getQuery());         // 查询部分：q=URL
        System.out.println(url.getRef());           // 锚点：search-results-close-container
    }

    /**
     * InputStream	openStream()
     * 打开到此 URL 的连接并返回一个用于从该连接读入的 InputStream
     */
    @Test
    public void openStream() throws IOException {
        URL url = new URL("http://www.baidu.com");
        InputStream is = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        System.out.println(sb);
        br.close(); // 关闭最外层流后，内部的流也会关闭
    }

    /**
     * URLConnection	openConnection()
     * 返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接
     * <p>
     * 1.连接 Http 协议的 URL，返回 HttpURLConnection 对象
     * 2.连接 Https 协议的 URL，返回 HttpsURLConnection 对象
     * 3.连接的 URL 为一个 JAR 文件，返回 JarURLConnection 对象
     * ...
     */
    @Test
    public void openConnection() throws IOException {
        URL url = new URL("https://developer.mozilla.org/en-US/search?q=URL#search-results-close-container");
        URLConnection urlConn = url.openConnection();
        HttpsURLConnection conn;
        if (urlConn instanceof HttpsURLConnection) {
            conn = (HttpsURLConnection) urlConn;
            System.out.println(conn);
        }
    }
}
