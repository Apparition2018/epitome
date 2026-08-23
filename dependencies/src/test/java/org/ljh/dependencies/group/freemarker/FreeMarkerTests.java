package org.ljh.dependencies.group.freemarker;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

/**
 * FreeMarker
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class FreeMarkerTests {

    private final FreeMarkerUtil util = FreeMarkerUtil.getInstance("src/test/java/org/ljh/dependencies/group/freemarker");

    @Test
    public void testFtl() {
        this.test("01.ftl", "test01.ftl");
    }

    @Test
    public void testHtm() {
        this.test("01.htm", "test02.htm");
    }

    @Test
    public void testJsp() {
        this.test("01.jsp", "test03.jsp");
    }

    private void test(String fName, String outPath) {
        Employer emp = new Employer(1, "张三", 18);
        List<Employer> empList = List.of(
                new Employer(1, "张三", 16),
                new Employer(2, "李四", 32),
                new Employer(3, "王五", 64)
        );
        Map<String, Object> params = Map.of(
                "username", "小张",
                "emp", emp,
                "empList", empList
        );
        util.printOut(params, fName);
        util.printFile(params, fName, "target/" + outPath);
    }
}
