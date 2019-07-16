package jar.fasterxml.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * https://www.cnblogs.com/yuanmo396022993/p/9118308.html
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
        objectMapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS);
        // 允许出现单引号
        objectMapper.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
    }

    @Test
    public void test() {
        Student student = new Student();
        student.setPassword("123");
        student.setAge(0);
        student.setBirth(new Date());
        String json = obj2String(student);
        log.info(obj2String(student));
        log.info(string2Obj(json, new TypeReference<Student>() {
        }).toString());
    }

    public static <T> String obj2String(T src) {
        if (src == null) {
            return null;
        }
        try {
            return src instanceof String ? (String) src : objectMapper.writeValueAsString(src);
        } catch (Exception e) {
            log.warn("parse Object to String exception, error:{}", e);
            return null; // 暂时返回空，也可抛出异常
        }
    }

    public static <T> T string2Obj(String src, TypeReference<T> typeReference) {
        if (src == null || typeReference == null) {
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

@Getter
@Setter
@NoArgsConstructor
@ToString
class Student {
    private String name;
    @JsonIgnore
    private String password;
    private Integer age;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a", locale = "zh", timezone = "GMT+8")
    private Date birth;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String desc;
}
