package knowledge.network.url;

import l.demo.Demo;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/net/URLConnection.html">URLConnection</a>
 *
 * @author ljh
 * @since 2020/11/17 19:09
 */
public class URLConnectionDemo extends Demo {

    @Test
    public void testURLConnection() throws IOException {
        URL url = new URL(BAIDU_URL);
        URLConnection conn = url.openConnection();
        // 设置连接超时时间
        conn.setConnectTimeout((int) TimeUnit.SECONDS.toMillis(10));
        // 设置读超时时间
        conn.setReadTimeout((int) TimeUnit.SECONDS.toMillis(10));

        // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）
        conn.connect();

        // 响应状态信息
        String statusInfo = conn.getHeaderField(0);
        if (null == statusInfo || statusInfo.contains(String.valueOf(HttpStatus.SC_OK))) {
            p("请求失败！");
        }

        p("======== map ========");
        // 头字段的不可修改的 Map
        Map<String, List<String>> headerFields = conn.getHeaderFields();
        Set<String> keySet = headerFields.keySet();
        for (Object key : keySet) {
            // 指定的头字段的值
            String val = conn.getHeaderField(String.valueOf(key));
            p(key + " = " + val);
        }

        p("======== get ========");
        // 字节长度
        p("ContentLength = " + conn.getContentLength());    // ContentLength = 97996
        // 编码类型
        p("ContentEncoding = " + conn.getContentEncoding());// ContentEncoding = null
        // MIME类型
        p("ContentType = " + conn.getContentType());        // ContentType = image/png
        // 最后修改时间
        p("LastModified = " + conn.getLastModified());      // LastModified = 0
        // Date
        p("Date = " + conn.getDate());                      // Date = 1600221472000
        // 过期时间
        p("Expiration = " + conn.getExpiration());          // Expiration = 1602848715000
    }

    /**
     * InputStream	                getInputStream()                返回从此打开的连接读取的输入流
     */
    @Test
    public void getInputStream() throws IOException {
        URL url = new URL(BAIDU_URL);
        URLConnection conn = url.openConnection();
        HttpURLConnection httpConn;
        if (conn instanceof HttpURLConnection) {
            httpConn = (HttpURLConnection) conn;
            p(httpConn);
        } else {
            p("请输入 url 地址");
            return;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), StandardCharsets.UTF_8));
        String line;
        StringBuilder sb = new StringBuilder();
        while (null != (line = br.readLine())) {
            sb.append(line).append("\n");
        }
        p(sb);
    }
}
