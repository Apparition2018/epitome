package jar.hutool;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.StreamProgress;
import cn.hutool.http.HtmlUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import l.demo.Demo;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Http
 * https://hutool.cn/docs/#/http/%E6%A6%82%E8%BF%B0
 * <p>
 * Hutool-http 优点：
 * 1.根据 URL 自动判断是请求 HTTP 还是 HTTPS，不需要单独写多余的代码。
 * 2.表单数据中有 File 对象时自动转为 multipart/form-data 表单，不必单做做操作。
 * 3.默认情况下 Cookie 自动记录，比如可以实现模拟登录，即第一次访问登录 URL 后后续请求就是登录状态。
 * 4.自动识别 304 跳转并二次请求
 * 5.自动识别页面编码，即根据 header 信息或者页面中的相关标签信息自动识别编码，最大可能避免乱码。
 * 6.自动识别并解压 Gzip 格式返回内容
 *
 * @author ljh
 * created on 2020/11/2 15:58
 */
public class HttpDemo extends Demo {

    /**
     * HttpRequest
     * HttpUtil 中的 get 和 post 工具方法都是 HttpRequest 对象的封装，
     * 因此如果想更加灵活操作 Http 请求，可以使用 HttpRequest
     * https://hutool.cn/docs/#/http/Http%E8%AF%B7%E6%B1%82-HttpRequest
     * <p>
     * HttpResponse
     * https://hutool.cn/docs/#/http/Http%E5%93%8D%E5%BA%94-HttpResponse
     */
    @Test
    public void testHttp() {

        // Restful
        HttpResponse response = HttpRequest.post(BAIDU_URL)
                .body("baidu")
                .execute();

        p(response.getStatus()); // 302
    }

    /**
     * HttpUtil
     * 此工具封装了 HttpRequest 对象常用操作。
     * 此模块基于 JDK 的 HttpUrlConnection 封装完成，完整支持 https、代理和文件上传。
     * https://hutool.cn/docs/#/http/Http%E5%AE%A2%E6%88%B7%E7%AB%AF%E5%B7%A5%E5%85%B7%E7%B1%BB-HttpUtil
     */
    public static class HttpUtilDemo {
        /**
         * 上传
         */
        @Test
        public void testUpload() {
            Map<String, Object> paramMap = new HashMap<>();
            // 文件上传只需将参数中的键指定为 file
            paramMap.put("file", new File(DEMO_FILE_PATH));
            p(HttpUtil.post(BAIDU_URL, paramMap));
        }

        /**
         * 下载
         */
        @Test
        public void testDownload() {
            // 如果想更加灵活的将 HTTP 内容转换写出，
            // 可以使用 HttpUtil.download(String url, OutputStream out, boolean isCloseOut[, StreamProgress streamProgress])
            HttpUtil.downloadFile(ARSENAL_LOGO, FileUtil.file("E:/"), new StreamProgress() {
                @Override
                public void start() {
                    p("开始下载...");
                }

                @Override
                public void progress(long progressSize) {
                    p("已下载：" + FileUtil.readableFileSize(progressSize));
                }

                @Override
                public void finish() {
                    p("下载完成...");
                }
            });
        }
    }

    /**
     * HtmlUtil
     * https://hutool.cn/docs/#/http/HTML%E5%B7%A5%E5%85%B7%E7%B1%BB-HtmlUtil
     */
    private static class HtmlUtilDemo {

        public static void main(String[] args) {
            String html = HttpUtil.get(BAIDU_URL);

            // filter(html)                     过滤 html 文本，防止 XSS 攻击
            html = HtmlUtil.filter(html);

            // removeHtml(html, tagName...)     去除指定标签及其内容
            p(HtmlUtil.removeHtmlTag("a<img src='xxx/xxx/test.jpg'>", "img"));      // a
            // unwrapHtmlTag(html, tagName...)  去除指定标签保留内容
            p(HtmlUtil.unwrapHtmlTag("a<div class='test_div'>b</div>", "div"));     // ab
            // cleanHtmlTag(html)               去除所有标签保留内容
            p(HtmlUtil.cleanHtmlTag("a<div class='test_div'>b</div><div>c</div>")); // abc

            // removeHtmlAttr(html, attr...)    去除指定属性
            p(HtmlUtil.removeHtmlAttr("<div class='test_div'></div><span class='test_span'></span>", "class")); // <div></div><span></span>
            // removeAllHtmlAttr(html);         去除指定标签所有属性
            p(HtmlUtil.removeAllHtmlAttr("<div class='test_div' width='120'></div>", "div"));                   // <div></div>

            // escape(html)                     转义
            // ' 替换为 &#039;
            // " 替换为 &quot;
            // & 替换为 &amp;
            // < 替换为 &lt;
            // > 替换为 &gt;
            String escape = HtmlUtil.escape("<div class='test_div'>&</div>"); // <div></div>
            p(escape);                          // &lt;div class=&#039;test_div&#039;&gt;&amp;&lt;/div&gt;
            // unescape(escape)                 还原转义
            p(HtmlUtil.unescape(escape));       // <div class='test_div'>&</div>

        }
    }
}
