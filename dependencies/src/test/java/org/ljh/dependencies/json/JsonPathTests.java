package org.ljh.dependencies.json;

import com.jayway.jsonpath.*;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JsonPath
 *
 * @author ljh
 * @see <a href="https://github.com/json-path/JsonPath">JsonPath</a>
 * @since 2021/8/31 15:42
 */
public class JsonPathTests implements JsonDemo {

    @Test
    public void test() {
        ReadContext context = JsonPath.parse(COMPLEX_JSON);
        // 所有学生的名字；.. 深度扫描
        assertEquals(context.read("$.students[*].studentName"), (Object) context.read("$..studentName"));
        // 最后一个学生的名字
        assertEquals("lucy", context.read("$.students[-1].studentName"));
        // 第一个和第二个学生
        assertEquals(context.read("$.students[0:2]"), (Object) context.read("$.students[0, 1]"));
        // 年龄大于13的学生的名字
        assertEquals(List.of("lucy"), context.read("$.students[?(@.studentAge > 13)].studentName"));
        // 匹配名字（忽略大小写）的学生
        assertEquals(List.of("lucy"), context.read("$.students[?(@.studentName =~ /.*CY/i)].studentName"));
        // 学生数量
        assertEquals(2, (Object) context.read("$.students.length()"));
        // 学生平均年龄
        assertEquals(13.5, context.read("$..studentAge.avg()"));
    }

    @Test
    public void testReadingDocument() {
        // 1.
        assertEquals("lily", JsonPath.read(JSON_PLAIN, "$.name"));
        // 2.
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(JSON_PLAIN);
        assertEquals("lily", JsonPath.read(document, "$.name"));
        // 3.
        ReadContext context = JsonPath.parse(JSON_PLAIN);
        assertEquals("lily", context.read("$.name"));
        // 4.
        assertEquals("lily", JsonPath.parse(JSON_PLAIN).read("$.name"));
    }

    @Test
    public void testReturn() {
        // 1.
        System.out.println(JsonPath.parse("{\"date_as_long\" : 1411455611975}").read("$.date_as_long", Date.class));
        System.out.println(JsonPath.parse(COMPLEX_JSON).read("$", Teacher.class));
        // 2.
        Configuration config = Configuration.builder()
                .jsonProvider(new JacksonJsonProvider())
                .mappingProvider(new JacksonMappingProvider())
                .build();
        List<Student> studentList = JsonPath.using(config).parse(COMPLEX_JSON).read("$.students", new TypeRef<>() {
        });
        System.out.println(studentList);
    }

    @Test
    public void testPredicates() {
        // 1. Inline Predicates
        List<Map<String, Object>> students = JsonPath.read(COMPLEX_JSON, "$.students[?(@.studentName == 'lily' || !(@.studentAge < 14))]");
        // 2. Filter Predicates
        Filter filter = Filter.filter(Criteria.where("studentName").is("lily")).or(Criteria.where("studentAge").gte(14));
        assertEquals(students, JsonPath.parse(COMPLEX_JSON).read("$.students[?]", filter));
        // 3. Roll Your Own
        Predicate predicate = ctx -> {
            @SuppressWarnings("unchecked")
            Map<String, Object> student = ctx.item(Map.class);
            String name = (String) student.get("studentName");
            Integer age = (Integer) student.get("studentAge");
            return "lily".equals(name) || (age != null && age >= 14);
        };
        assertEquals(students, JsonPath.parse(COMPLEX_JSON).read("$.students[?]", predicate));
    }

    /**
     * JsonPath 可以返回 Path 或 Value（默认），本例是如何设置返回完整的 Path
     */
    @Test
    public void testReturnPath() {
        Configuration conf = Configuration.builder().options(Option.AS_PATH_LIST).build();
        List<String> pathList = JsonPath.using(conf).parse(COMPLEX_JSON).read("$..studentName");
        assertEquals(List.of("$['students'][0]['studentName']", "$['students'][1]['studentName']"), pathList);
    }

    @Test
    public void testSetValue() {
        assertEquals("{\"name\":\"lily\",\"age\":13}", JsonPath.parse(JSON_PLAIN).set("$.age", 13).jsonString());
    }
}
