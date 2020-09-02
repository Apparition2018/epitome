package jar.lombok;


import lombok.*;
import org.junit.Test;

/**
 * Lombok
 * <p>
 * https://www.cnblogs.com/maoshiling/p/8534980.html
 * http://www.cnblogs.com/exmyth/p/8710902.html
 * http://himichaelchu.iteye.com/blog/2124584
 * https://www.cnblogs.com/zhangyinhua/p/8623018.html
 * https://www.cnblogs.com/yrml/p/9015968.html
 * https://www.lwhweb.com/2017/11/08/lombok-use/
 * https://projectlombok.org/features/all
 */
public class LombokDemo {

    @Test
    public void nonNull() {
        User1 u = new User1(1, "Henry", null); // NullPointerException: password
        p(u);
    }

    @Test
    public void requiredArgsConstructor() {
        User2 u = new User2("Henry", "123");
        p(u);
    }

    @Test
    public void of() {
        User3 u = User3.of("Henry", "123");
        p(u);
    }

    @Test
    public void toStringExcludeAndCallSuper() {
        User4 u = new User4();
        u.setId(1);
        u.setName("Henry");
        u.setPassword("123");
        u.setPhone("12456");
        p(u); // User3(super=User(phone=12456), name=Henry, password=123)
    }

    @Test
    public void equalsExclude() {
        User4 u1 = new User4(1, "Henry", "123");
        User4 u2 = new User4(2, "Henry", "123");
        p(u1.equals(u2)); // true
    }

    @Test
    public void accessLevel() {
        User4 u = new User4();
        u.setName("Henry");
        // String name = u.getName(); // 编译错误，getName() has private access ...
    }


    private static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }

}

/**
 * NonNull
 * null 检测
 */
@Data
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
 * NoArgsConstructor
 * 无参构造函数
 * 当类中有 final 字段没有被初始化时，编译会报错，此时使用 force = true，就会为没有初始化的 final 字段设置默认值为 0 / false / null
 * 对于具有约束的字段（如 @NonNull 字段），不会生成检查或分配，因此请注意，正确初始化这些字段之前，这些约束无效
 * <p>
 * RequiredArgsConstructor
 * 如果带有参数，则参数必须是 final 修饰的未经初始化的字段，或 @NonNull 注解的未经初始化的字段
 * <p>
 * AllArgsConstructor
 * 全参构造函数
 */
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
class User2 {

    private Integer id;
    private final String name;
    @NonNull
    private String password;

}

/**
 * RequiredArgsConstructor
 * staticName = "of"，生成一个 of() 静态方法，并把构造方法设置为 private
 */
@Data
@RequiredArgsConstructor(staticName = "of")
class User3 {

    private Integer id;
    private final String name;
    @NonNull
    private String password;

}

/**
 * Getter / Setter
 * 生成 getter / setter
 * <p>
 * ToString
 * 生成 toString()
 * exclude:     忽略打印字段
 * callSuper:   是否打印超类
 * <p>
 * EqualsAndHashCode
 * 生成 hashCode() 和 equals()，同行仓情况下，所有 non-static, non-transient 字段都用于标识
 * exclude:     排除
 * callSuper:   是否使用超类字段用于标识
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "id", callSuper = true)
@EqualsAndHashCode(exclude = "id", callSuper = true)
class User4 extends User {

    private Integer id;
    // 修改默认访问修饰符 public 为 protected
    private @Getter(AccessLevel.PRIVATE)
    String name;
    private String password;

}

/**
 * Data
 * 相当于 Getter, 非 final Setter, ToString, EqualsAndHashCode, RequiredArgsConstructor
 */
@Data
class User {
    private String phone;
}


