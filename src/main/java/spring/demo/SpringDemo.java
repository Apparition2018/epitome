package spring.demo;

import l.demo.Person;
import l.demo.Person.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.bean.Bean;
import spring.bean.OtherBean;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static l.demo.Demo.p;

/**
 * Spring
 *
 * @author ljh
 * created on 2020/11/23 17:17
 */
public class SpringDemo {

    ClassPathXmlApplicationContext ac;
    ClassPathXmlApplicationContext annAc;

    @Before
    public void init() {
        ac = new ClassPathXmlApplicationContext("demo/spring/spring-bean.xml");
    }

    /**
     * 生命周期
     */
    @Test
    public void testLifecycle() {
        Bean bean = ac.getBean("bean", Bean.class);
        bean.service();
    }

    /**
     * 延迟加载
     */
    @Test
    public void testLazy() {
        OtherBean otherBean = ac.getBean("otherBean", OtherBean.class);
        otherBean.service();
        // Bean.construct()
        // Bean.init()
        // Lazy.construct()
        // LLazy.service()
        // Bean.destroy()
    }

    @Test
    public void getBean() {
        // 无参构造创建
        GregorianCalendar gregorianCalendar = ac.getBean("gregorianCalendar", GregorianCalendar.class);

        // 工厂静态方法
        Calendar calendar = ac.getBean("calendar", Calendar.class);

        // 工厂实例方法
        Date date = ac.getBean("time", Date.class);
    }

    /**
     * 依赖注入
     */
    @Test
    public void testDependencyInjection() {
        // set 注入
        Student student = ac.getBean("student", Student.class);
        p(student.getBirth()); // 2020-11-24 01:53:36

        // 构造器注入
        Student student2 = ac.getBean("student2", Student.class);
        p(student2);    // Student{id=1, name='张三'}

        // p 命名空间注入
        Student student3 = ac.getBean("student3", Student.class);
        p(student3);    // Student{id=1, name='张三'}
    }

    /**
     * 注入值
     */
    @Test
    public void testInjectValue() {
        Person person = ac.getBean("person", Person.class);
        p(person);  // Person{otherInfo=[父亲, 医生]}

        Bean bean = ac.getBean("bean", Bean.class);
        p(bean.properties.getProperty("jdbc.driver")); // com.mysql.cj.jdbc.Driver

        // spring el expression
        OtherBean otherBean = ac.getBean("otherBean", OtherBean.class);
        p(otherBean.getScore()); // 100
    }

    /**
     * 作用域
     */
    @Test
    public void testScope() {
        p(ac.getBean("student") == ac.getBean("student"));  // false
        p(ac.getBean("person") == ac.getBean("person"));    // true
    }

    /**
     * 注解配置
     */
    @Test
    public void testAnn() {
        annAc = new ClassPathXmlApplicationContext("demo/spring/spring-ann.xml");

        // @PostConstruct @PreDestroy @Lazy
        OtherBean otherBean = annAc.getBean("otherBean", OtherBean.class);
        OtherBean otherBean2 = annAc.getBean("otherBean", OtherBean.class);

        // @scope
        p(otherBean == otherBean2); // false

        // @Autowired @Qualifier @Value
        p(otherBean); // OtherBean(bean=Bean(score=100, properties=null), str=[x,y,z.split(',')], score=100, password=root)

        annAc.close();
    }

    @After
    public void destroy() {
        ac.close();
    }
}
