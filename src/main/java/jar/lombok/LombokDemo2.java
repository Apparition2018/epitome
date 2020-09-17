package jar.lombok;

import l.demo.Demo;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.junit.Test;

import java.util.Set;

public class LombokDemo2 extends Demo {

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

    /**
     * Accessors
     * 主要用于控制生成的 getter / setter
     * <p>
     * fluent boolean 值，默认 false。控制生成的 getter / setter 方法前面是否带 get / set
     * chain boolean 值，默认 false。如果为 true，setter 返回的是此对象，方便链式调用方法
     * prefix 去除前缀
     */
    @Getter
    @Setter
    @Accessors(fluent = true, chain = true)
    static class User5 {
        private Integer id;
        private String name;
        private String password;
        @Accessors(fluent = true, prefix = "phone")
        private String phoneNumber;
    }

    @Test
    public void testFieldDefaults() {
        User6 u = new User6(1, "Henry", "123");
        p(u);
    }

    /**
     * FieldDefaults
     * 主要用于给字段添加修饰符
     */
    @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @ToString
    static class User6 {
        private Integer id;
        private String name;
        private String password;
    }

    @Test
    public void testValue() {
        User7 u = new User7(1, "Henry", "123");
        // u.setId(2); // cannot resolve method 'setName()'
    }


    /**
     * Value
     * 不可变对象
     * 相当于 Getter, FieldDefaults(makeFinal=true, level=AccessLevel.PRIVATE), AllArgsConstructor, ToString, EqualsAndHashCode
     */
    @Value
    static class User7 {
        Integer id;
        String name;
        String password;
    }

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

    /**
     * Builder
     * <p>
     * Singular
     * 与 Builder 一起用于在集合的生成器中创建单个元素 add()
     */
    @ToString
    @Builder(toBuilder = true)
    static class User8 {
        private Integer id;
        private String name;
        private String password;
        @Singular
        private Set<String> interests;
    }

}

