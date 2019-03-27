package knowledge.网络编程.url;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;

/**
 * URLConnection
 */
public class URLConnectionDemo {

    /**
     * InputStream	getInputStream()
     * 返回从此打开的连接读取的输入流
     */
    @Test
    public void getInputStream() throws IOException {
        URL url = new URL("http://www.baidu.com");
        URLConnection urlConn = url.openConnection();
        HttpURLConnection conn;
        if (urlConn instanceof HttpURLConnection) {
            conn = (HttpURLConnection) urlConn;
            System.out.println(conn);
        } else {
            System.out.println("请输入 url 地址");
            return;
        }
        InputStream is = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    @Test
    public void test() throws IOException {
        URL url = new URL("http://www.runoob.com/wp-content/themes/runoob/assets/img/newlogo.png");
        URLConnection conn = url.openConnection();

        // Map<String,List<String>>	getHeaderFields()
        // 返回头字段的不可修改的 Map
        Map headers = conn.getHeaderFields();
        Set keys = headers.keySet();
        for (Object key : keys) {
            // String	getHeaderField(String name)
            // 返回指定的头字段的值
            String val = conn.getHeaderField(String.valueOf(key));
            System.out.println(key + "   " + val);
        }

        System.out.println("====================");

        // int	getContentLength()
        // 返回 content-length 头字段的值
        System.out.println("文件大小为：" + conn.getContentLength() + " bytes");

        // long	getLastModified()
        // 返回 last-modified 头字段的值
        System.out.println("文件最后修改时间：" + conn.getLastModified());

        // long	getDate()
        // 返回 date 头字段的值
        System.out.println("Date: " + conn.getDate());
    }

}
