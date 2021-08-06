package l.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

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
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Comparable<Person>, Serializable {
    private static final long serialVersionUID = -8205619618185839521L;
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private List<String> otherInfo;
    private Home home;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
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

    @Bean("person")
    public Person getInstance(Integer id, String name) {
        return new Person(id, name);
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
     * <p>
     * POJO
     * 1.有一些属性是私有的 private，每一个属性提供 getter 和 setter 方法
     * 2.没有继承任何类，没有实现任何接口
     * 3.没有被其它框架侵入
     * 阿里编程规约：
     * 定义 DO/DTO/VO 等 POJO 类时，不要设定任何属性默认值
     * POJO 类必须写 toString 方法。如果继承了另一个 POJO 类，注意在前面加一下 super.toString
     */
    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    public static class Student extends Person implements Serializable {
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
         * 如果完全不兼容升级，避免反序列化混乱，那么请修改 serialVersionUID 值（阿里编程规约）
         * <p>
         * serialVersionUID的作用：https://www.zhihu.com/question/24852886/answer/117314768
         */
        private static final long serialVersionUID = 527285523879940432L;
        private String no;
        // 不参与序列化和反序列化
        @JsonIgnore
        // 被 transient 修饰的属性，在进行对象序列化时该值会被忽略，已达到对象瘦身的目的
        private transient String password;
        // 格式化
        @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a", locale = "zh", timezone = "GMT+8")
        private Date birth;
        // 当不为 null 时才返回该属性
        @JsonInclude(JsonInclude.Include.NON_NULL)
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
                    (null != birth ? ", birth=" + DateFormatUtils.format(birth, "yyyy-MM-dd") : "") + (null != score ? ", score=" + score : "");
            return "Student{" + (result.startsWith(",") ? result.substring(2) : result) + "}";
        }
    }
}
