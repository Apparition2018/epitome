package jar.fasterxml.jackson.annotataion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jar.fasterxml.jackson.custom.CustomFilter;
import jar.fasterxml.jackson.entity.Person;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * Annotation
 * <pre>
 * <a href="https://github.com/FasterXML/jackson-docs/wiki/JacksonAnnotations">Jackson Annotations</a>
 * <a href="https://www.jianshu.com/p/83b0a3f4d0bf">Jackson 全面解析--注解全讲解一</a>
 * <a href="https://www.jianshu.com/p/32c21a390e1d">Jackson 全面解析--注解全讲解二</a>
 * <a href="https://www.jianshu.com/p/e85c3dfba052">Jackson 全面解析--注解全讲解三</a>
 * <a href="https://www.jianshu.com/p/38e6f1f642b3">Jackson 全面解析--注解全讲解四</a>
 * <a href="https://www.jianshu.com/p/2982116ee0ff">Jackson 全面解析--注解全讲解五</a>
 * <a href="https://www.jianshu.com/p/a4e24b253c4d">Jackson 全面解析--注解全讲解六</a>
 * <a href="https://www.jianshu.com/p/54cf66ff6e3f">Jackson 全面解析--注解全讲解七</a>
 * <a href="https://www.jianshu.com/p/2b9ee52bd6ab">Jackson 全面解析--注解全讲解八</a>
 * <a href="https://www.jianshu.com/p/3d98fa64473b">Jackson 全面解析--注解全讲解九</a>
 * <a href="https://www.jianshu.com/p/4003b9756952">Jackson 全面解析--注解全讲解十</a>
 * <a href="https://www.jianshu.com/p/38202f12dd31">Jackson 全面解析--注解全讲解十一</a>
 * <a href="https://www.jianshu.com/p/e7483a292605">Jackson 全面解析--注解全讲解十二</a>
 * </pre>
 *
 * @author ljh
 * @since 2021/7/19 23:44
 */
public class AnnotationDemo extends Demo {

    /**
     * {@code @JsonInclude}
     * {@code @JsonIgnoreProperties}
     * {@code @JsonIgnore}
     * {@code @JsonIgnoreType}
     * {@code @JsonPropertyOrder}
     * {@code @JsonProperty}
     * {@code @JsonAppend}
     * {@code @JsonFilter}
     * {@code @JsonRootName}
     * {@code @JsonNaming}
     * {@code @JsonFormat}
     * {@code @JsonRawValue}
     * {@code @JsonAnyGetter}
     *
     * @see jar.fasterxml.jackson.entity.Person
     */
    @Test
    public void testGenerator() throws JsonProcessingException {
        jsonMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("customFilter", new CustomFilter());
        jsonMapper.setFilterProvider(filterProvider);
        Person person = new Person()
                .setName(MY_NAME)
                .setAge(31)
                .setBirth(new Date())
                .setSex(1)
                .setHairStyle("pony-tail")
                .setDog(new Person.Dog().setName("旺财").setColor(Person.Color.BLACK))
                .setJson("{\"language\":\"chinese\"}");
        person.getOther().put("country", "cn");
        // {"P":{"name":"ljh","gender":1,"birth":"2021-07-24 18:07:36 PM","hairstyle":"pony-tail",
        //      "dog":{"name":"旺财","color":"BLACK"},"json":{"language":"chinese"},"height":"163","version":"1.2","country":"cn"}}
        p(jsonMapper.writerFor(Person.class).withAttribute("height", "163").writeValueAsString(person));
    }

    /**
     * {@code @JsonNaming}
     * {@code @JsonProperty}
     * {@code @JsonIgnoreProperties}
     * {@code @JsonIgnore}
     * {@code @JsonIgnoreType}
     * {@code @JsonAlias}
     * {@code @JacksonInject}
     * {@code @JsonAnySetter}
     * {@code @JsonEnumDefaultValue}
     *
     * @see jar.fasterxml.jackson.entity.Person
     */
    @Test
    public void testParser() throws JsonProcessingException {
        InjectableValues.Std std = new InjectableValues.Std();
        std.addValue("age", 0);
        jsonMapper.setInjectableValues(std);
        jsonMapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);
        String json = "{\"name\":\"ljh\",\"birthday\":1626920254637,\"gender\":1,\"dog\":{\"name\":\"旺财\",\"color\":\"BLUE\"},\"password\":\"123\",\"country\":\"cn\"}";
        p(jsonMapper.readValue(json, Person.class));
        // Person(birth=Thu Jul 22 10:17:34 CST 2021, age=0, sex=1, password=null, hairStyle=null,
        //      dog=Person.Dog(name=旺财, color=WHITE), hobbies=null, name=ljh, json=null, other={country=cn, password=123})
    }
}
