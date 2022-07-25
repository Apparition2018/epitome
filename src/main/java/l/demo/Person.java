package l.demo;

import cn.hutool.core.date.DatePattern;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.time.DateFormatUtils;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Person
 *
 * @author ljh
 * created on 2020/9/18 15:45
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@Schema(description = "人员")
public class Person implements Comparable<Person>, Cloneable, Serializable {
    private static final long serialVersionUID = -8205619618185839521L;
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private List<String> otherInfo;
    private Home home;

    @Data
    public static class Home implements Serializable {
        private static final long serialVersionUID = 5624494519989168136L;
        private String address;
        private String tel;
    }

    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, Integer age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public int compareTo(Person o) {
        return new CompareToBuilder().append(age, o.age).toComparison();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (null == o || getClass() != o.getClass()) return false;
        Person p = (Person) o;
        return Objects.equals(id, p.id);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public Person clone() {
        try {
            return (Person) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        String result = (null != id ? "id=" + id : "") + (null != name ? ", name='" + name + '\'' : "") +
                (null != age ? ", age=" + age : "") + (null != gender ? ", gender='" + gender + '\'' : "") +
                (null != otherInfo ? ", otherInfo=[" + StringUtils.join(otherInfo, ", ") + "]" : "") +
                (null != getHome() ? ", home=Home(" + (null != getHome().getAddress() ? "address='" + getHome().getAddress() + '\'' : "") + (null != getHome().getTel() ? ", tel='" + getHome().getTel() + '\'' : "") + ")" : "");
        return "Person{" + (result.startsWith(",") ? result.substring(2) : result) + "}";
    }

    /**
     * JavaBean
     * 1.类是公有的 public
     * 2.属性是私有的 private，提供 getter 和 setter 方法
     * 3.提供无参构造器
     * 4.实现序列化接口 Serializable (分布式应用)
     * ps：JavaBean 第二个字母不能大写：https://www.cnblogs.com/threecc/archive/2011/09/05/2167667.html
     * -    产生问题：无法通过 getter/setter 获取和设置属性
     * -    解决方法：使用 @JsonProperty
     * <p>
     * POJO
     * 1.有一些属性是私有的 private，每一个属性提供 getter 和 setter 方法
     * 2.没有继承任何类，没有实现任何接口
     * 3.没有被其它框架侵入
     * 阿里编程规约：
     * 1.所有的 POJO 类属性必须使用包装数据类型
     * 2.定义 DO / PO / DTO / VO 等 POJO 类时，不要设定任何属性默认值
     * 3.POJO 类必须写 toString 方法。如果继承了另一个 POJO 类，注意在前面加一下 super.toString()
     * 4.禁止在 POJO 类中，同时存在对应属性 xxx 的 isXxx() 和 getXxx() 方法
     * 5.velocity 调用 POJO 类的属性时，直接使用属性名取值即可，模板引擎会自动按规范调用 POJO 的 getXxx()，
     * 如果是 boolean 基本数据类型变量（boolean 命名不需要加 is 前缀），会自动调 isXxx() 方法
     */
    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    @XmlRootElement(name = "student")
    @Schema(description = "学生")
    public static class Student extends Person {
        /**
         * 当一个类实现了可序列化接口，就要定义一个常量：版本号 (serialVersionUID)
         * <p>
         * 生成方式：
         * 1.1L
         * 2.根据类名、接口名、成员方法及属性等来生成
         * <p>
         * 版本号决定着对象反序列化是否成功：
         * 1.反序列化的对象的版本号若与当前类版本号一致，反序列化成功。若反序列化对象的结构与当前类接口有变化，那么可以还原的属性就还原，没有的属性就忽略
         * 2.版本号若不一致，则反序列化直接失败
         * <p>
         * 序列化类新增属性时，请不要修改 serialVersionUID 字段，避免反序列失败；如果完全不兼容升级，避免反序列化混乱，那么请修改 serialVersionUID 值（阿里编程规约）
         * <p>
         * serialVersionUID的作用：https://www.zhihu.com/question/24852886/answer/117314768
         */
        private static final long serialVersionUID = 527285523879940432L;
        private String no;
        // 被 transient 修饰的属性，在进行对象序列化时该值会被忽略，已达到对象瘦身的目的
        private transient String password;
        private Date birth;
        private Float score;

        public Student(Integer id, String name) {
            super(id, name);
        }

        public Student(Integer id, String no, String name, Integer age, Float score) {
            super.id = id;
            this.no = no;
            super.name = name;
            super.age = age;
            this.score = score;
        }

        @Override
        public String toString() {
            String result = (null != super.id ? "id=" + super.id : "") + (null != super.name ? ", name='" + super.name + '\'' : "") +
                    (null != super.age ? ", age=" + super.age : "") + (null != super.gender ? ", gender='" + super.gender + '\'' : "") +
                    (null != super.otherInfo ? ", otherInfo=[" + StringUtils.join(super.otherInfo, ", ") + "]" : "") +
                    (null != super.getHome() ? ", home=Home(" + (null != super.getHome().getAddress() ? "address='" + super.getHome().getAddress() + '\'' : "") + (null != super.getHome().getTel() ? ", tel='" + super.getHome().getTel() + '\'' : "") + ")" : "") +
                    (null != no ? ", no='" + no + '\'' : "") +
                    (null != birth ? ", birth=" + DateFormatUtils.format(birth, DatePattern.NORM_DATE_PATTERN) : "") + (null != score ? ", score=" + score : "");
            return "Student{" + (result.startsWith(",") ? result.substring(2) : result) + "}";
        }
    }
}
