package jar.hutool.http;

import cn.hutool.http.HtmlUtil;
import cn.hutool.http.HttpUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

/**
 * HtmlUtil
 * 爬虫爬取 HTML 之后，需要对返回页面的 HTML 内容做一定处理，比如去掉指定标签（例如广告栏等）、去除 JS、去掉样式等等
 * https://hutool.cn/docs/#/http/HTML%E5%B7%A5%E5%85%B7%E7%B1%BB-HtmlUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/http/HtmlUtil.html
 *
 * @author Arsenal
 * created on 2020/11/2 23:35
 */
public class HtmlUtilDemo extends Demo {

    @Test
    public void testHtmlUtil() {
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
        String escape = HtmlUtil.escape("<div class='test_div'>&</div>");
        p(escape);                          // &lt;div class=&#039;test_div&#039;&gt;&amp;&lt;/div&gt;
        // unescape(escape)                 还原转义
        p(HtmlUtil.unescape(escape));       // <div class='test_div'>&</div>
    }
}
