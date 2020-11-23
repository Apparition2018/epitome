package spring.demo.bean;

import l.demo.Demo;
import l.demo.Person.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
    
    @Test
    public void test() {
        Student student = (Student) ac.getBean("student");
        p(student.getBirth());

        Student student2 = (Student) ac.getBean("student2");
        p(student2);
    }

    @After
    public void destroy() {
        ac.close();
    }
}
