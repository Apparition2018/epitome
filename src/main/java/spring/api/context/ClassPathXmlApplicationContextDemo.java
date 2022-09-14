package spring.api.context;

import l.demo.Person;
import l.demo.Person.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.bean.Bean;
import spring.bean.OtherBean;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static l.demo.Demo.pe;

/**
 * spring
 *
 * @author ljh
 * created on 2020/11/23 17:17
 */
public class ClassPathXmlApplicationContextDemo {

    ClassPathXmlApplicationContext applicationContext;
    ClassPathXmlApplicationContext annApplicationContext;

    @BeforeEach
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("demo/spring/spring-bean.xml");
    }

    /**
     * 生命周期
     * -   BeanFactoryPostProcessor's construct()
     * 1.  BeanFactoryPostProcessor's postProcessBeanFactory()
     * -   BeanPostProcessor's construct()
     * 2.  Bean's construct()
     * 3.  XxxAware's setXxx()
     * 4.  BeanPostProcessor's postProcessBeforeInitialization()
     * 5.  @postConstruct()
     * 6.  InitializingBean()'s afterPropertiesSet()
     * 7.  init-method
     * 8.  BeanPostProcessor's postProcessAfterInitialization()
     * 9.  Bean's service()
     * 10. @preDestroy()
     * 11. DisposableBean()'s destroy()d
     * 12. destroy-method
     */
    @Test
    public void testLifecycle() {
        Bean bean = applicationContext.getBean("bean", Bean.class);
        bean.service();
    }

    /**
     * 延迟加载
     */
    @Test
    public void testLazy() {
        OtherBean otherBean = applicationContext.getBean("otherBean", OtherBean.class);
        otherBean.service();
        // Bean.construct()
        // Bean.init()
        // Lazy.construct()
        // Lazy.service()
        // Bean.destroy()
    }

    /**
     * 实例化 Bean
     */
    @Test
    public void initBean() {
        // 1. 无参构造方法
        GregorianCalendar gregorianCalendar = applicationContext.getBean("gregorianCalendar", GregorianCalendar.class);

        // 2 工厂方法
        // 2.1 静态工厂方法
        Calendar calendar = applicationContext.getBean("calendar", Calendar.class);
        // 2.2 实例工厂方法
        Date date = applicationContext.getBean("time", Date.class);
    }

    /**
     * 依赖
     */
    @Test
    public void testDependencies() {
        // 构造器注入
        Student student = applicationContext.getBean("student", Student.class);
        pe(student);    // Student{id=1, name='<张三>', birth=2022-09-13}

        // c 命名空间注入
        Student student2 = applicationContext.getBean("student2", Student.class);
        pe(student2);   // Student{id=1, name='张三', birth=2022-09-13}

        // setter 注入
        Student student3 = applicationContext.getBean("student3", Student.class);
        pe(student3);   // Student{id=1, name='张三', birth=2022-09-13}

        // p 命名空间注入
        Student student4 = applicationContext.getBean("student4", Student.class);
        pe(student4);   // Student{id=1, name='张三', birth=2022-09-13}

        // 自动装配
        Student student5 = applicationContext.getBean("student5", Student.class);
        pe(student5);   // Student{otherInfo=[父亲, 医生]}

        // 继承 Bean 属性
        Student student6 = applicationContext.getBean("student6", Student.class);
        pe(student6);   // Student{id=2, name='李四', birth=2022-09-13}
    }

    /**
     * 注入值
     */
    @Test
    public void testInjectValue() {
        // 注入集合
        Person person = applicationContext.getBean("person", Person.class);
        pe(person.getOtherInfo());  // [父亲, 医生]

        // 注入 Properties
        Bean bean = applicationContext.getBean("bean", Bean.class);
        pe(bean.properties.getProperty("jdbc.driver")); // com.mysql.cj.jdbc.Driver

        // SpEL
        OtherBean otherBean = applicationContext.getBean("otherBean", OtherBean.class);
        pe(otherBean.getScore());   // 100
    }

    /**
     * 作用域
     */
    @Test
    public void testScope() {
        pe(applicationContext.getBean("student") == applicationContext.getBean("student")); // false
        pe(applicationContext.getBean("person") == applicationContext.getBean("person"));   // true
    }

    /**
     * 注解配置
     */
    @Test
    public void testAnn() {
        pe("=== testAnn ===");
        annApplicationContext = new ClassPathXmlApplicationContext("demo/spring/spring-ann.xml");

        pe("=== @PostConstruct @PreDestroy @Lazy ===");
        OtherBean otherBean = annApplicationContext.getBean("otherBean", OtherBean.class);
        OtherBean otherBean2 = annApplicationContext.getBean("otherBean", OtherBean.class);

        pe("=== @scope ===");
        pe(otherBean == otherBean2); // false

        pe("=== @Autowired @Qualifier @Value ===");
        pe(otherBean); // OtherBean(bean=Bean(score=100, properties=null), str=[x,y,z.split(',')], score=100, password=root)

        annApplicationContext.close();
    }

    @AfterEach
    public void destroy() {
        applicationContext.close();
    }
}
