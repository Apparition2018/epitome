package jar.jackson;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import jar.jackson.entity.Person;
import jar.jackson.entity.Person;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import static l.demo.Demo.MY_NAME;

/**
 * JsonGenerator
 *
 * @author ljh
 * @since 2021/7/15 17:58
 */
public class JsonGeneratorDemo {
    public static final String PERSON_JSON_FILE = System.getProperty("user.dir") + "/src/main/java/jar/fasterxml/jackson/person.json";

    @Test
    public void testJsonGenerator() throws IOException {
        JsonFactory jsonFactory = new JsonFactory();

        try (JsonGenerator jsonGenerator = jsonFactory.createGenerator(new File(PERSON_JSON_FILE), JsonEncoding.UTF8)) {
            // 格式化输出
            // jsonGenerator.useDefaultPrettyPrinter();
            // 紧凑型输出
            jsonGenerator.setPrettyPrinter(new MinimalPrettyPrinter());
            jsonGenerator.writeStartObject(); // {

            jsonGenerator.writeStringField("name", MY_NAME);
            jsonGenerator.writeNumberField("age", 31);

            // 写入 Dog 对象
            jsonGenerator.writeFieldName("dog");
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", "旺财");
            jsonGenerator.writeStringField("color", "WHITE");
            jsonGenerator.writeEndObject();

            // 写入一个数组
            jsonGenerator.writeFieldName("hobbies");
            jsonGenerator.writeStartArray();
            jsonGenerator.writeString("basketball");
            jsonGenerator.writeString("football");
            jsonGenerator.writeEndArray();

            jsonGenerator.writeEndObject(); // }
        }
    }

    /**
     * <pre>
     * JsonGenerator enable(Feature f)                      开启
     * JsonGenerator disable(Feature f)                     关闭
     * JsonGenerator configure(Feature f, boolean state)    开启/关闭
     * </pre>
     */
    @Test
    public void testFeature() throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        try (JsonGenerator jsonGenerator = jsonFactory.createGenerator(System.out, JsonEncoding.UTF8)) {
            // 自动关闭流，默认 true
            // jsonGenerator.close();
            jsonGenerator.enable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
            // 自动补齐，默认 true
            jsonGenerator.enable(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT);
            // 自动 flush，默认 true
            // jsonGenerator.flush();
            jsonGenerator.enable(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM);
            // 使用 BigDecimal#toPlainString() 输出，默认 false
            // 1E+11 → 100000000000
            jsonGenerator.enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
            // 严格重复属性检测，默认 false
            jsonGenerator.enable(JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION);
            // 忽略没有定义的属性，作用于预先定义了格式的数据类型，如 Avro、protobuf 等，默认 false
            jsonGenerator.enable(JsonGenerator.Feature.IGNORE_UNKNOWN);

        }
    }

    /** 自定义 ObjectCodec */
    @Test
    public void testMyObjectCodec() throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        try (JsonGenerator jsonGenerator = jsonFactory.createGenerator(System.out, JsonEncoding.UTF8)) {
            jsonGenerator.setCodec(new MyObjectCodec());
            jsonGenerator.writeObject(new Person(MY_NAME, 31));
        }
    }

    private static class MyObjectCodec extends ObjectCodec {

        @Override
        public Version version() {
            return null;
        }

        @Override
        public <T> T readValue(JsonParser jsonParser, Class<T> aClass) throws IOException {
            return null;
        }

        @Override
        public <T> T readValue(JsonParser jsonParser, TypeReference<T> typeReference) throws IOException {
            return null;
        }

        @Override
        public <T> T readValue(JsonParser jsonParser, ResolvedType resolvedType) throws IOException {
            return null;
        }

        @Override
        public <T> Iterator<T> readValues(JsonParser jsonParser, Class<T> aClass) throws IOException {
            return null;
        }

        @Override
        public <T> Iterator<T> readValues(JsonParser jsonParser, TypeReference<T> typeReference) throws IOException {
            return null;
        }

        @Override
        public <T> Iterator<T> readValues(JsonParser jsonParser, ResolvedType resolvedType) throws IOException {
            return null;
        }

        @Override
        public void writeValue(JsonGenerator jsonGenerator, Object o) throws IOException {
            Person person = (Person) o;
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", person.getName());
            jsonGenerator.writeNumberField("age", person.getAge());
            jsonGenerator.writeEndObject();
        }

        @Override
        public <T extends TreeNode> T readTree(JsonParser jsonParser) throws IOException {
            return null;
        }

        @Override
        public void writeTree(JsonGenerator jsonGenerator, TreeNode treeNode) throws IOException {

        }

        @Override
        public TreeNode createObjectNode() {
            return null;
        }

        @Override
        public TreeNode createArrayNode() {
            return null;
        }

        @Override
        public JsonParser treeAsTokens(TreeNode treeNode) {
            return null;
        }

        @Override
        public <T> T treeToValue(TreeNode treeNode, Class<T> aClass) throws JsonProcessingException {
            return null;
        }
    }
}
