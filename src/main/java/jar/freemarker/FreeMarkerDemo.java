package jar.freemarker;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

/**
 * FreeMarker
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class FreeMarkerDemo {
    private final FreeMarkerUtil util = FreeMarkerUtil.getInstance("/WEB-INF/ftl");

    @Test
    public void test01() {
        this.test("01.ftl", "test01.ftl");
    }

    @Test
    public void test02() {
        this.test("01.htm", "test02.htm");
    }

    @Test
    public void test03() {
        this.test("01.jsp", "test03.jsp");
    }

    private void test(String fName, String outPath) {
        Employer emp = new Employer(1, "张三", 18);
        List<Employer> empList = List.of(
            new Employer(1, "张三", 18),
            new Employer(2, "李四", 20),
            new Employer(3, "王五", 22)
        );
        Map<String, Object> params = Map.of(
            "username", "小张",
            "emp", emp,
            "empList", empList
        );
        util.sPrint(params, fName);
        util.fPrint(params, fName, "T:/" + outPath);
    }
}
