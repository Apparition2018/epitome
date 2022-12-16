package jar.jsonpath;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import l.demo.Demo;
import l.demo.JsonDemo;
import org.junit.jupiter.api.Test;

/**
 * JsonPath
 * <p>
 * Json-Path 使用（一）：https://lux-sun.blog.csdn.net/article/details/106420629
 * Json-Path 使用（二）：https://lux-sun.blog.csdn.net/article/details/106421388
 *
 * @author ljh
 * @since 2021/8/31 15:42
 */
public class JsonPathDemo extends Demo implements JsonDemo {

    @Test
    public void testJsonPath() {
        ReadContext context = JsonPath.parse(JSON_COMPLEX);

        p("studentName: " + context.read("$.students[0].studentName"));
        p("studentName: " + JsonPath.read(JSON_COMPLEX, "$.students[0].studentName"));
    }
}
