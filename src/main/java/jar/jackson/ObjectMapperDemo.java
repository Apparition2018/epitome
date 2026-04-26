package jar.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import jar.jackson.custom.CustomDeserializer;
import jar.jackson.custom.CustomSerializer;
import jar.jackson.entity.Person;
import l.demo.Demo;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.InjectableValues;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.cfg.DateTimeFeature;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.SimpleTimeZone;

/**
 * ObjectMapper
 * ObjectMapper 是 jackson-databind 模块最为重要的一个类，它完成了 coder 对数据绑定的几乎所有功能。
 * ObjectMapper 是面向用户的高层 API，底层依赖于 Streaming API 来实现读/写。
 * 1 读取和写入JSON的功能
 * -  1.1 普通 POJO 的序列化/反序列化
 * -  1.2 JSON 树模型的读/写
 * 2 可以高度定制，以使用不同风格的 JSON
 * -  2.1 Feature
 * -  2.2 tools.jackson.databind.module
 * 3 支持更高级的对象概念：多态泛型、对象标识
 * 4 充当其它更高级的 API：ObjectReader 和 ObjectWriter 的工厂
 *
 * @author ljh
 * @since 2021/7/18 12:59
 */
public class ObjectMapperDemo extends Demo {

    /** JsonMapper 为 ObjectMapper 的子类 */
    @Test
    public void testJsonMapper() {
        JsonMapper jsonMapper = JsonMapper.builder().injectableValues(new InjectableValues.Std().addValue("age", 0)).build();

        // String   writeValueAsString(Object value)
        // 写成字符串形式（最常用）
        String json = jsonMapper.writeValueAsString(new Person(MY_NAME, 31, new Date()));
        p("========== Obj → String ==========\n" + json);
        // T        readValue(String content, Class<T> valueType)
        // 读为指定 Class 类型的对象
        Person person = jsonMapper.readValue(json, Person.class);
        System.out.println("========== String → Obj ==========\n" + person + StringUtils.CR);

        json = jsonMapper.writeValueAsString(list);
        System.out.println("========== List → String ==========\n" + json);
        // T        readValue(String content, TypeReference<T> valueTypeRef)
        // 读为指定 TypeReference 类型的对象，一般用于泛型集合/Map的反序列化
        List<Long> list = jsonMapper.readValue(json, new TypeReference<>() {
        });
        System.out.println("========== String → List ==========\n" + list);

        json = jsonMapper.writeValueAsString(map);
        System.out.println("========== Map → String ==========\n" + json);
        // T        readValue(String content, JavaType valueType)
        // 读为指定 JavaType 类型的对象，一般用于泛型集合/Map的反序列化
        map = jsonMapper.readValue(json, jsonMapper.getTypeFactory().constructMapType(HashMap.class, Integer.class, String.class));
        System.out.println("========== String → Map ==========\n" + map);
    }

    @Test
    public void testFeature() {
        // 自定义模块
        SimpleModule simpleModule = new SimpleModule();
        // 自定义序列
        simpleModule.addSerializer(new CustomSerializer());
        // 自定义反序列化
        simpleModule.addDeserializer(Person.class, new CustomDeserializer());

        ObjectMapper objectMapper = JsonMapper.builder()
            // 格式化输出，默认 false
            .enable(SerializationFeature.INDENT_OUTPUT)
            // 没有找到属性的访问器或注解则报错，默认 true
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            // 遇到未知属性报错，默认 true
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            // 日期转为时间戳，默认 true
            .disable(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS)
            // 设置空如何序列化
            .changeDefaultPropertyInclusion(incl -> incl.withValueInclusion(JsonInclude.Include.NON_EMPTY))
            // 设置 DateFormat
            .defaultDateFormat(DATE_TIME_FORMAT.get())
            // 设置 TimeZone
            .defaultTimeZone(SimpleTimeZone.getTimeZone("GMT+8"))
            // 设置可视化
            .changeDefaultVisibility(vc -> vc.withVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY))
            // 注册自定义模块
            .addModule(simpleModule)
            // 自动注册所有模块
            .findAndAddModules()
            .build();
    }
}
