package jar.apache.commons.text;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.jupiter.api.Test;

import static l.demo.Demo.p;

/**
 * <a href="https://commons.apache.org/proper/commons-text/javadocs/api-release/org/apache/commons/text/StringEscapeUtils.html">StringEscapeUtils</a>
 * <p>转义和反转义 Java, JavaScript, HTML, XML 字符串
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class StringEscapeUtilsDemo {

    @Test
    public void json() {
        String str = "{\"name\":\"Jack\",\"age\":18}";
        String json = StringEscapeUtils.escapeJson(str);
        p("json = " + json);
        // json = {\"name\":\"Jack\",\"age\":18}
        p("str = " + StringEscapeUtils.unescapeJson(json));
        // str = {"name":"Jack","age":18}
    }

    @Test
    public void html() {
        String str = "<div class=\"table-td\">红豆生南国<br/>春来发几枝<br/>愿君多采撷<br/>此物最相思</div>";
        String html = StringEscapeUtils.unescapeHtml4(str);
        p("html = " + html);
        // html = <div class="table-td">红豆生南国<br/>春来发几枝<br/>愿君多采撷<br/>此物最相思</div>
        p("str = " + StringEscapeUtils.escapeHtml4(html));
        // str = &lt;div class=&quot;table-td&quot;&gt;红豆生南国&lt;br/&gt;春来发几枝&lt;br/&gt;愿君多采撷&lt;br/&gt;此物最相思&lt;/div&gt;
    }
}
