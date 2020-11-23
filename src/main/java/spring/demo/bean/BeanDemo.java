package spring.demo.bean;

import l.demo.Demo;
import l.demo.Person;
import l.demo.Person.Student;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

/**
 * BeanFactory
 *
 * @author ljh
 * created on 2020/11/23 17:17
 */
public class BeanDemo extends Demo {

    ClassPathXmlApplicationContext ac;

    @Before
    public void init() {
        ac = new ClassPathXmlApplicationContext("demo/spring-bean.xml");
    }
    
    @Test
    public void getBean() {
        // 无参构造创建
        GregorianCalendar gregorianCalendar = (GregorianCalendar) ac.getBean("gregorianCalendar");

        // 工厂静态方法
        Calendar calendar = (Calendar) ac.getBean("calendar");

        // 工厂实例方法
        Date date = (Date) ac.getBean("time");
    }

    /**
     * 依赖注入
     */
    @Test
    public void testDependencyInjection() {
        // set 注入
        Student student = (Student) ac.getBean("student");
        p(student.getBirth()); // 2020-11-24 01:53:36

        // 构造器注入
        Student student2 = (Student) ac.getBean("student2");
        p(student2);    // Student{id=1, name='张三'}

        // p 命名空间注入
        Student student3 = (Student) ac.getBean("student3");
        p(student3);    // Student{id=1, name='张三'}
    }

    /**
     * 注入集合
     */
    @Test
    public void testCollection() {
        Person person = (Person) ac.getBean("person");
        p(person); // Person{otherInfo=[父亲, 医生]}
        
        Bean bean = (Bean) ac.getBean("properties");
        p(bean); // BeanDemo.Bean(properties={...})
    }

    @After
    public void destroy() {
        ac.close();
    }
    
    @Data
    private static class Bean {
        private Properties properties;
        private Map<String, String> map;
    }
}
