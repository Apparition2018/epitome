package jar.apache.commons.beanutils;

import java.util.Date;

/**
 * JavaBean
 * 1.类是公有的 public
 * 2.属性是私有的 private，提供 getter 和 setter 方法
 * 3.提供无参构造器
 * 4.实现序列化接口 Serializable （分布式应用）
 * <p>
 * POJO
 * 1.有一些属性是私有的 private，每一个属性提供 getter 和 setter 方法
 * 2.没有继承任何类，没有实现任何接口
 * 3.没有被其它框架侵入
 */
public class Student {

    private int id;
    private String name;
    private int age;
    private Date birth;

    Student() {
    }

    public Student(String name) {
        this.name = name;
    }


    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
