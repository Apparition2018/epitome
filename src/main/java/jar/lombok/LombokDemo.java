package jar.lombok;

import lombok.*;
import org.junit.jupiter.api.Test;

import static l.demo.Demo.p;

/**
 * Lombok
 * Lombok features：https://projectlombok.org/features/all
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class LombokDemo {

    /**
     * `@NonNull
     * null 检测
     */
    @Test
    public void testNonNull() {
        User1 u = new User1(1, "Henry", null); // NullPointerException: password is marked non-null but is null
        p(u);
    }

    @Data
    static
    class User1 {

        private Integer id;
        private String name;
        private String password;

        User1(Integer id, String name, @NonNull String password) {
            this.id = id;
            this.name = name;
            this.password = password;
        }

    }

    /**
     * `@NoArgsConstructor
     * 无参构造函数
     * 当类中有 final 字段没有被初始化时，编译会报错，此时使用 force = true，就会为没有初始化的 final 字段设置默认值为 0 / false / null
     * 对于具有约束的字段（如 @NonNull 字段），不会生成检查或分配，因此请注意，正确初始化这些字段之前，这些约束无效
     * <p>
     * `@RequiredArgsConstructor
     * 如果带有参数，则参数必须是 final 修饰的未经初始化的字段，或 @NonNull 注解的未经初始化的字段
     * <p>
     * `@AllArgsConstructor
     * 全参构造函数
     */
    @Test
    public void testRequiredArgsConstructor() {
        User2 u = new User2("Henry", "123");
        p(u);
    }

    @NoArgsConstructor(force = true)
    @RequiredArgsConstructor
    @AllArgsConstructor
    @ToString
    private static class User2 {

        private Integer id;
        private final String name;
        @NonNull
        private final String password;

    }

    /**
     * `@RequiredArgsConstructor
     * staticName = "of"，生成一个 of() 静态方法，并把构造方法设置为 private
     */
    @Test
    public void testRequiredArgsConstructorStaticName() {
        User3 u = User3.of("Henry", "123");
        p(u);
    }

    @Data
    @RequiredArgsConstructor(staticName = "of")
    private static class User3 {

        private Integer id;
        private final String name;
        @NonNull
        private String password;

    }

    /**
     * `@ToString
     * 生成 toString()
     * exclude:     忽略打印字段
     * callSuper:   是否打印超类
     */
    @Test
    public void testToStringExcludeAndCallSuper() {
        User4 u = new User4();
        u.setId(1);
        u.setName("Henry");
        u.setPassword("123");
        u.setPhone("12456");
        p(u); // User3(super=User(phone=12456), name=Henry, password=123)
    }

    /**
     * `@EqualsAndHashCode
     * 生成 hashCode() 和 equals()，同行仓情况下，所有 non-static, non-transient 字段都用于标识
     * exclude:     排除
     * callSuper:   是否使用超类字段用于标识
     */
    @Test
    public void testEqualsAndHashCodeExclude() {
        User4 u1 = new User4(1, "Henry", "123");
        User4 u2 = new User4(2, "Henry", "123");
        p(u1.equals(u2)); // true
    }

    /**
     * `@Getter / @Setter
     * 生成 getter / setter
     */
    @Test
    public void testGetterAccessLevel() {
        User4 u = new User4();
        u.setName("Henry");
        // String name = u.getName(); // 编译错误，getName() has private access ...
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString(exclude = "id", callSuper = true)
    @EqualsAndHashCode(exclude = "id", callSuper = true)
    private static class User4 extends User {

        private Integer id;
        // 将 getter 默认访问修饰符 public 改为 protected
        @Getter(AccessLevel.PRIVATE)
        private String name;
        private String password;

    }

    /**
     * `@Data
     * 相当于 @Getter, 非 final @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
     */
    @Data
    private static class User {
        private String phone;
    }
}


