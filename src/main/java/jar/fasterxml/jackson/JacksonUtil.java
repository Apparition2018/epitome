package jar.fasterxml.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import l.demo.Person.Student;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * https://www.cnblogs.com/yuanmo396022993/p/9118308.html
 * <p>
 * spring-boot 中 spring.jackson.date-format 失效及解决办法：
 * https://my.oschina.net/u/3681868/blog/3075150
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
@Slf4j
public class JacksonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 日期格式化
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 格式化输出
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        // 忽略无法转换的对象
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 设置空如何序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // 允许对象忽略json中不存在的属性
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 允许出现特殊字符和转义符
        objectMapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
        // 允许出现单引号
        objectMapper.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
    }

    public static void main(String[] args) {
        Student student = new Student();
        student.setPassword("123");
        student.setAge(0);
        student.setBirth(new Date());
        String json = obj2String(student);
        log.info("Obj -> String:\n" + json + "\n");
        log.info("String -> Obj:\n" + string2Obj(json, new TypeReference<Student>() {
        }).toString());
    }

    public static <T> String obj2String(T src) {
        if (null == src) {
            return null;
        }
        try {
            return src instanceof String ? (String) src : objectMapper.writeValueAsString(src);
        } catch (Exception e) {
            log.warn("parse Object to String exception, error:{}", e.getMessage());
            return null; // 暂时返回空，也可抛出异常
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T string2Obj(String src, TypeReference<T> typeReference) {
        if (null == src || null == typeReference) {
            return null;
        }
        try {
            return typeReference.getType().equals(String.class) ? (T) src : objectMapper.readValue(src, typeReference);
        } catch (Exception e) {
            log.warn("parse String to Object exception, String:{}, TypeReference<T>:{}, error:{}", src, typeReference.getType(), e);
            return null;
        }
    }
}
