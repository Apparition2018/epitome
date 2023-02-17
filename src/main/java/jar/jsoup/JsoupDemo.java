package jar.jsoup;

import l.demo.Demo;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * <a href="https://jsoup.org/apidocs/">Jsoup</a>
 * <pre>
 * jsoup 是一个用于处理真实世界 HTML 的 Java 库。
 * jsoup 提供了一个非常方便的 API 来提取和操作数据，并充分利用了 DOM、CSS 和类 jquery 的方法。
 * jsoup 实现了 WHATWG HTML5 规范，并将 HTML 解析为与现代浏览器相同的 DOM。
 * </pre>
 *
 * @author ljh
 * @since 2020/11/12 10:43
 */
public class JsoupDemo extends Demo {

    public static void main(String[] args) throws IOException {
        // static Connection	connect(String url)
        // 创建到 URL 的连接
        Connection connect = Jsoup.connect(BAIDU_URL);

        // Document	            get()
        // 执行 Get 请求，并解析结果
        Document document = connect.get();

        // Element	            getElementById(String id)
        // 根据 ID 查找元素及其子元素
        Element su = document.getElementById("su");

        // 获取一个表单元素的值 (input, textarea 等)
        p(su.val()); // 百度一下
    }
}
