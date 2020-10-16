package jar.apache.commons.text;

import l.demo.Demo;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;

/**
 * StringEscapeUtils
 * 转义和反转义 Java, JavaScript, HTML, XML 字符串
 * <p>
 * https://commons.apache.org/proper/commons-text/javadocs/api-release/org/apache/commons/text/StringEscapeUtils.html
 */
public class StringEscapeUtilsDemo extends Demo {

    @Test
    public void json() {
        String str = "{\"name\":\"Jack\",\"age\":18}";
        String json = StringEscapeUtils.escapeJson(str);
        p("json = " + json);
        p("str = " + StringEscapeUtils.unescapeJson(json));
    }

    @Test
    public void html() {
        String str = "<div class=\"table-td\">红豆生南国<br/>春来发几枝<br/>愿君多采撷<br/>此物最相思</div>";
        String html = StringEscapeUtils.unescapeHtml4(str);
        p("html = " + html);
        p("str = " + StringEscapeUtils.escapeHtml4(html));
    }

}
