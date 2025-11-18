package jar.jsonpath;

import com.jayway.jsonpath.*;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import l.demo.Demo;
import l.demo.JsonDemo;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * JsonPath
 *
 * @author ljh
 * @see <a href="https://github.com/json-path/JsonPath">JsonPath</a>
 * @since 2021/8/31 15:42
 */
public class JsonPathDemo extends Demo implements JsonDemo {

    @Test
    public void test() {
        ReadContext context = JsonPath.parse(COMPLEX_JSON);
        // 所有学生的名字；.. 深度扫描
        ae(context.read("$..studentName"), context.read("$.students[*].studentName"));
        // 最后一个学生的名字
        ae(context.read("$.students[-1].studentName"), "lucy");
        // 第一个和第二个学生
        ae(context.read("$.students[0, 1]"), context.read("$.students[0:2]"));
        // 年龄大于13的学生的名字
        ae(context.read("$.students[?(@.studentAge > 13)].studentName"), List.of("lucy"));
        // 匹配名字（忽略大小写）的学生
        ae(context.read("$.students[?(@.studentName =~ /.*CY/i)].studentName"), List.of("lucy"));
        // 学生数量
        ae(context.read("$.students.length()"), 2);
        // 学生平均年龄
        ae(context.read("$..studentAge.avg()"), 13.5);
    }

    @Test
    public void testReadingDocument() {
        // 1.
        ae(JsonPath.read(JSON_PLAIN, "$.name"), "lily");
        // 2.
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(JSON_PLAIN);
        ae(JsonPath.read(document, "$.name"), "lily");
        // 3.
        ReadContext context = JsonPath.parse(JSON_PLAIN);
        ae(context.read("$.name"), "lily");
        // 4.
        ae(JsonPath.parse(JSON_PLAIN).read("$.name"), "lily");
    }

    @Test
    public void testReturn() {
        // 1.
        p(JsonPath.parse("{\"date_as_long\" : 1411455611975}").read("$.date_as_long", Date.class));
        p(JsonPath.parse(COMPLEX_JSON).read("$", Teacher.class));
        // 2.
        Configuration config = Configuration.builder()
            .jsonProvider(new JacksonJsonProvider())
            .mappingProvider(new JacksonMappingProvider())
            .build();
        List<Student> studentList = JsonPath.using(config).parse(COMPLEX_JSON).read("$.students", new TypeRef<>() {
        });
        p(studentList);
    }

    @Test
    public void testPredicates() {
        // 1. Inline Predicates
        List<Map<String, Object>> students = JsonPath.read(COMPLEX_JSON, "$.students[?(@.studentName == 'lily' || !(@.studentAge < 14))]");
        // 2. Filter Predicates
        Filter filter = Filter.filter(Criteria.where("studentName").is("lily")).or(Criteria.where("studentAge").gte(14));
        ae(JsonPath.parse(COMPLEX_JSON).read("$.students[?]", filter), students);
        // 3. Roll Your Own
        Predicate predicate = ctx -> {
            @SuppressWarnings("unchecked")
            Map<String, Object> student = ctx.item(Map.class);
            String name = (String) student.get("studentName");
            Integer age = (Integer) student.get("studentAge");
            return "lily".equals(name) || (age != null && age >= 14);
        };
        ae(JsonPath.parse(COMPLEX_JSON).read("$.students[?]", predicate), students);
    }

    /**
     * JsonPath 可以返回 Path 或 Value（默认），本例是如何设置返回完整的 Path
     */
    @Test
    public void testReturnPath() {
        Configuration conf = Configuration.builder().options(Option.AS_PATH_LIST).build();
        List<String> pathList = JsonPath.using(conf).parse(COMPLEX_JSON).read("$..studentName");
        ae(pathList, List.of("$['students'][0]['studentName']", "$['students'][1]['studentName']"));
    }

    @Test
    public void testSetValue() {
        ae(JsonPath.parse(JSON_PLAIN).set("$.age", 13).jsonString(), "{\"name\":\"lily\",\"age\":13}");
    }
}
