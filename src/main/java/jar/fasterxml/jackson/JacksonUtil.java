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
import org.assertj.core.util.Lists;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * https://www.cnblogs.com/yuanmo396022993/p/9118308.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
@Slf4j
public class JacksonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        // ObjectMapper enable(SerializationFeature f)                      开启
        // ObjectMapper disable(SerializationFeature f)                     关闭
        // ObjectMapper configure(SerializationFeature f, boolean state)    开启/关闭

        // 日期格式化
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 格式化输出
        OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        // 忽略无法转换的对象
        OBJECT_MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 设置空如何序列化
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // 允许对象忽略json中不存在的属性
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 允许出现特殊字符和转义符
        OBJECT_MAPPER.enable(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature());
        // 同上
        // OBJECT_MAPPER.enable(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS);
        // 允许出现单引号
        OBJECT_MAPPER.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
    }

    public static void main(String[] args) {
        Student student = new Student();
        student.setPassword("123");
        student.setAge(1);
        student.setBirth(new Date());
        Student student2 = new Student();
        student2.setPassword("234");
        student2.setAge(2);
        student2.setBirth(new Date());
        List<Student> list = Lists.list(student, student2);

        String json = obj2String(list);
        log.info("\n========== Obj → String ==========\n" + json + "\n");

        list = string2Obj(json, new TypeReference<List<Student>>() {
        });
        log.info("\n========== String → Obj ==========\n" + list);
    }

    public static <T> String obj2String(T src) {
        if (null == src) {
            return null;
        }
        try {
            // String   writeValueAsString(Object value)
            // 写成字符串形式（最常用）
            return src instanceof String ? (String) src : OBJECT_MAPPER.writeValueAsString(src);
        } catch (Exception e) {
            log.warn("parse Object to String exception, error:{}", e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T string2Obj(String src, TypeReference<T> typeReference) {
        if (null == src || null == typeReference) {
            return null;
        }
        try {
            // T        readValue(String content, TypeReference<T> valueTypeRef)
            // 读为指定 TypeReference 类型的对象，一般用于泛型集合/Map的反序列化
            return typeReference.getType().equals(String.class) ? (T) src : OBJECT_MAPPER.readValue(src, typeReference);
        } catch (Exception e) {
            log.warn("parse String to Object exception, String:{}, TypeReference<T>:{}, error:{}", src, typeReference.getType(), e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T string2Obj(String src, Class<T> clazz) {
        if (null == src || null == clazz) {
            return null;
        }
        try {
            // T        readValue(String content, Class<T> valueType)
            // 读为指定 Class 类型的对象
            return clazz.equals(String.class) ? (T) src : OBJECT_MAPPER.readValue(src, clazz);
        } catch (Exception e) {
            log.warn("parse String to Object exception, String:{}, Class<T>:{}, error:{}", src, clazz, e);
            return null;
        }
    }
}
