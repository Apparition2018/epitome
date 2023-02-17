package jar.lombok;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static l.demo.Demo.p;

/**
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class LombokDemo2 {

    /**
     * &#064;Accessors
     * <p>主要用于控制生成的 getter / setter
     * <p>fluent boolean 值，默认 false。如果为 true，生成的 getter / setter 方法前面不带 get / set
     * <p>chain boolean 值，默认 false。如果为 true，setter 返回的是此对象，方便链式调用方法
     * <p>prefix 去除前缀
     */
    @Test
    public void testAccessorsFluentAndChain() {
        User5 u = new User5();
        u.id(1).name("Henry").password("123");
        p("id = " + u.id());
        p("name = " + u.name());
        p("password = " + u.name());
    }

    @Test
    public void testAccessorsPrefix() {
        User5 u = new User5();
        u.number("123456");
        p(u.number());
    }

    @Getter
    @Setter
    @Accessors(fluent = true, chain = true)
    private static class User5 {
        private Integer id;
        private String name;
        private String password;
        @Accessors(fluent = true, prefix = "phone")
        private String phoneNumber;
    }

    /**
     * &#064;FieldDefaults
     * <p>主要用于给字段添加修饰符
     */
    @Test
    public void testFieldDefaults() {
        User6 u = new User6(1, "Henry", "123");
        p(u);
    }

    @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @ToString
    private static class User6 {
        private Integer id;
        private String name;
        private String password;
    }

    /**
     * &#064;Value
     * <p>不可变对象
     * <p>相当于 @Getter, @FieldDefaults(makeFinal=true, level=AccessLevel.PRIVATE), @AllArgsConstructor, @ToString, @EqualsAndHashCode
     */
    @Test
    public void testValue() {
        User7 u = new User7(1, "Henry", "123");
        // u.setId(2); // cannot resolve method 'setName()'
    }

    @Value
    private static class User7 {
        Integer id;
        String name;
        String password;
    }

    /**
     * &#064;Builder & &#064;Singular
     * <p>与 Builder 一起用于在集合的生成器中创建单个元素 add()
     */
    @Test
    public void testBuilder() {
        User8 u = User8.builder()
                .id(1)
                .name("Henry")
                .password("123")
                .interest("football")
                .interest("running")
                .build();

        p(u); // User8(id=1, name=Henry, password=123, interests=[football, running])

    }

    @Builder(toBuilder = true)
    private record User8(Integer id, String name, String password, @Singular Set<String> interests) {
    }
}
