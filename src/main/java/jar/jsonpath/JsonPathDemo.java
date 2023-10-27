package jar.jsonpath;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import l.demo.Demo;
import l.demo.JsonDemo;

/**
 * JsonPath
 * <pre>
 * <a href="https://lux-sun.blog.csdn.net/article/details/106420629">Json-Path 使用（一）</a>
 * <a href="https://lux-sun.blog.csdn.net/article/details/106421388">Json-Path 使用（二）</a>
 * </pre>
 *
 * @author ljh
 * @since 2021/8/31 15:42
 */
public class JsonPathDemo extends Demo implements JsonDemo {

    public static void main(String[] args) {
        ReadContext context = JsonPath.parse(COMPLEX_JSON);

        p("studentName: " + context.read("$.students[0].studentName"));
        p("studentName: " + JsonPath.read(COMPLEX_JSON, "$.students[0].studentName"));
    }
}
