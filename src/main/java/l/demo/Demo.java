package l.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * Demo
 *
 * @author ljh
 * created on 2020/9/3 10:15
 */
public class Demo {

    public String projectName = "epitome";
    public String numString = "123456789";
    public List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    public List<String> sList = new ArrayList<>(Arrays.asList("1 2 3 4 5 6 7 8 9".split(" ")));
    public List<Integer> descList = Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1);
    public List<Integer> repeatList = new ArrayList<>(Arrays.asList(1, 1, 2, 2, 3, 3, 4, 5));
    public List<Integer> subList = new ArrayList<>(list.subList(1, 6));
    public List<Integer> subList2 = list.subList(3, 8);
    public CountDownLatch countDownLatch;
    public Map<Integer, String> map = new HashMap<Integer, String>(3) {
        {
            put(1, "A");
            put(2, "B");
            put(3, "C");
        }
    };
    public Map<Integer, String> map2 = new HashMap<Integer, String>(5) {
        {
            put(1, "A");
            put(2, "B");
            put(3, "C");
            put(4, "E");
            put(5, "D");
        }
    };
    public SimpleDateFormat SDF = DATE_SDF;
    public static final SimpleDateFormat DATE_SDF = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_TIME_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public static final String UTF_8 = String.valueOf(StandardCharsets.UTF_8);
    public static final String DEMO_URL = "https://developer.mozilla.org/en-US/search?q=URL#search-results-close-container";
    public static final String BAIDU_URL = "https://www.baidu.com/";
    public static final String BAIDU_HOST = "www.baidu.com";
    public static final String DEMO_PATH = "src/main/resources/demo/";
    public static final String ARSENAL_LOGO = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1113375911,3381445023&fm=26&gp=0.jpg";
    public static final String USER_DIR = System.getProperty("user.dir");

    public void setCountDownLatch(int n) {
        countDownLatch = new CountDownLatch(n);
    }

    /**
     * 简写 System.out.println()
     */
    public static void p() {
        System.out.println();
    }

    public static <T> void p(T obj) {
        p(obj, false);
    }

    public static <T> void p(T obj, boolean original) {
        if (obj == null) return;
        // 数组
        if (obj.getClass().isArray()) {
            if (obj instanceof long[]) {
                System.out.println(Arrays.toString((long[]) obj));
                return;
            }
            if (obj instanceof int[]) {
                System.out.println(Arrays.toString((int[]) obj));
                return;
            }
            if (obj instanceof short[]) {
                System.out.println(Arrays.toString((short[]) obj));
                return;
            }
            if (obj instanceof char[]) {
                System.out.println(Arrays.toString((char[]) obj));
                return;
            }
            if (obj instanceof byte[]) {
                System.out.println(Arrays.toString((byte[]) obj));
                return;
            }
            if (obj instanceof boolean[]) {
                System.out.println(Arrays.toString((boolean[]) obj));
                return;
            }
            if (obj instanceof float[]) {
                System.out.println(Arrays.toString((float[]) obj));
                return;
            }
            if (obj instanceof double[]) {
                System.out.println(Arrays.toString((double[]) obj));
                return;
            }
            System.out.println(Arrays.toString((Object[]) obj));
            return;
        }
        // 日期时间
        if (obj instanceof Date) {
            if (original) {
                System.out.println(obj);
                return;
            }
            System.out.println(DATE_TIME_SDF.format(obj));
            return;
        }
        System.out.println(obj);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Person implements Comparable<Person>, Serializable {
        private static final long serialVersionUID = 1L;
        private Integer id;
        private String name;
        private Integer age;
        private String gender;
        private List<String> otherInfo;
        private Home home;

        @Data
        @AllArgsConstructor
        public static class Home implements Serializable {
            private static final long serialVersionUID = 1L;
            private String address;
            private String tel;
        }

        public Person(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Person(String name) {
            this.name = name;
        }

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public Person(String name, Integer age, String gender, List<String> otherInfo) {
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.otherInfo = otherInfo;
        }

        @Override
        public int compareTo(Person o) {
            return new CompareToBuilder().append(age, o.age).toComparison();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
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
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class Student extends Person implements Serializable {
        /**
         * 当一个类实现了可序列化接口，就要定义一个常量：版本号 (serialVersionUID)
         * 版本号决定着对象反序列化是否成功：
         * 1：反序列化的对象的版本号若与当前类版本号一致，反序列化成功。若反序列化对象的结构与当前类接口有变化，那么可以还原的属性就还原，没有的属性就忽略
         * 2：版本号若不一致，则反序列化直接失败
         */
        private static final long serialVersionUID = 1L;
        private String no;
        // 不返回该属性
        @JsonIgnore
        // 被 transient 修饰的属性，在进行对象序列化时该值会被忽略，已达到对象瘦身的目的
        private transient String password;
        // 会覆盖配置文件的 jason 配置
        @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a", locale = "zh", timezone = "GMT+8")
        private Date birth;
        // 当不为 null 时才返回该属性
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Float score;

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
                    (null != birth ? ", birth=" + DATE_SDF.format(birth) : "") + (null != score ? ", score=" + score : "");
            return "Student{" + (result.startsWith(",") ? result.substring(2) : result) + "}";
        }
    }

    @Data
    public abstract static class Animal {
        public String name;

        static {
            System.out.println("The is a Animal.");
        }

        public abstract void eat();
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Cat extends Animal {

        public int age;
        public int foot;

        static {
            System.out.println("The is a Cat.");
        }

        public void eat() {
            p("吃鱼");
        }

        public void work() {
            p("抓老鼠");
        }

    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Chicken extends Animal {

        public int age;
        private int wing;

        static {
            System.out.println("The is a Chicken.");
        }

        public void eat() {
            p("吃米");
        }

        public void work() {
            p("生鸡蛋");
        }

    }
}
