package jar.apache.commons.text;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.jupiter.api.Test;

/**
 * StringEscapeUtils
 * 转义和反转义 Java, JavaScript, HTML, XML 字符串
 * https://commons.apache.org/proper/commons-text/javadocs/api-release/org/apache/commons/text/StringEscapeUtils.html
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class StringEscapeUtilsDemo {

    @Test
    public void json() {
        String str = "{\"name\":\"Jack\",\"age\":18}";
        String json = StringEscapeUtils.escapeJson(str);
        System.out.println("json = " + json);
        // json = {\"name\":\"Jack\",\"age\":18}
        System.out.println("str = " + StringEscapeUtils.unescapeJson(json));
        // str = {"name":"Jack","age":18}
    }

    @Test
    public void html() {
        String str = "<div class=\"table-td\">红豆生南国<br/>春来发几枝<br/>愿君多采撷<br/>此物最相思</div>";
        String html = StringEscapeUtils.unescapeHtml4(str);
        System.out.println("html = " + html);
        // html = <div class="table-td">红豆生南国<br/>春来发几枝<br/>愿君多采撷<br/>此物最相思</div>
        System.out.println("str = " + StringEscapeUtils.escapeHtml4(html));
        // str = &lt;div class=&quot;table-td&quot;&gt;红豆生南国&lt;br/&gt;春来发几枝&lt;br/&gt;愿君多采撷&lt;br/&gt;此物最相思&lt;/div&gt;
    }

}
