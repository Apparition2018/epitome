package knowledge.network.url;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * URL
 * 统一资源定位符，它是指向互联网“资源”的指针。
 * 资源可以是简单的文件或目录，也可以是对更为复杂的对象的引用，例如对数据库或搜索引擎的查询。
 * https://tool.oschina.net/uploads/apidocs/jdk-zh/java/net/URL.html
 *
 * @author ljh
 * @since 2020/11/17 19:09
 */
public class URLDemo extends Demo {

    @Test
    public void testURL() throws IOException {
        // URL(String spec)         根据 String 表示形式创建 URL 对象
        URL url = new URL(MOZILLA_DEMO_URL);

        p(url);                     // https://developer.mozilla.org/en-US/search?q=URL#search-results-close-container
        p(url.getProtocol());       // 协议名称：https
        p(url.getAuthority());      // 授权部分：developer.mozilla.org
        p(url.getHost());           // 主机名：developer.mozilla.org
        p(url.getDefaultPort());    // 默认端口号：443
        p(url.getPort());           // 端口号：-1
        p(url.getFile());           // 文件名：/en-US/search?q=URL
        p(url.getPath());           // 路径部分：/en-US/search
        p(url.getQuery());          // 查询部分：q=URL
        p(url.getRef());            // 锚点：search-results-close-container
        p(url.getContent());        // 内容：sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@704921a5

        url = new URL("http://192.168.0.1:8080");
        p(url.getAuthority());      // 192.168.0.1:8080
        p(url.getHost());           // 192.168.0.1
    }

    /**
     * InputStream	    openStream()        打开到此 URL 的连接并返回一个用于从该连接读入的 InputStream
     */
    @Test
    public void openStream() throws IOException {
        URL url = new URL(BAIDU_URL);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
        String line;
        StringBuilder sb = new StringBuilder();
        while (null != (line = br.readLine())) {
            sb.append(line).append("\n");
        }
        p(sb);
        br.close(); // 关闭最外层流后，内部的流也会关闭
    }

    /**
     * URLConnection	openConnection()    返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接
     * <p>
     * 1.连接 Http 协议的 URL，返回 HttpURLConnection 对象
     * 2.连接 Https 协议的 URL，返回 HttpsURLConnection 对象
     * 3.连接的 URL 为一个 JAR 文件，返回 JarURLConnection 对象
     * ...
     */
    @Test
    public void openConnection() throws IOException {
        URL url = new URL(BAIDU_URL);
        URLConnection urlConn = url.openConnection();
        HttpsURLConnection conn;
        if (urlConn instanceof HttpsURLConnection) {
            conn = (HttpsURLConnection) urlConn;
            p(conn);
        }
    }
}
