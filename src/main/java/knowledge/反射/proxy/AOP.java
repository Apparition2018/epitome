package knowledge.反射.proxy;

import l.demo.Demo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * AOP
 *
 * @author ljh
 * created on 2020/11/10 15:43
 */
public class AOP extends Demo {

    ClassPathXmlApplicationContext ac;

    @org.junit.Before
    public void init() {
        ac = new ClassPathXmlApplicationContext("spring/spring-service.xml", "spring/spring-aop.xml");
    }

    @Test
    public void hello() {
        People people = (People) ac.getBean("man");
        people.hello();
    }

    @Test
    public void goodbye() {
        People people = (People) ac.getBean("man");
        people.goodbye();
    }

    @org.junit.After
    public void destroy() {
        ac.close();
    }


    @Order(1)
    @Aspect
    @Component
    public static class StopWatchProxyAspect {

        long start;
        long end;
        String methodName;

        @Before("bean(man)")
        public void before(JoinPoint point) {
            methodName = point.getSignature().getName();
            start = System.currentTimeMillis();
            p(methodName + " start.time = " + start);
        }

        @AfterReturning("bean(man)")
        public void afterReturning() {
        }

        @AfterThrowing("bean(man)")
        public void afterThrowing() {
        }

        @After("bean(man)")
        public void after(JoinPoint point) {
            end = System.currentTimeMillis();
            p(methodName + " end.time = " + end);
            p(methodName + " execute spend [" + (end - start) + "]ms.");
        }

        // @Around
    }

    @Order(2)
    @Aspect
    @Component
    public static class TransactionProxyAspect {

        /**
         * 切入点
         */
        @Pointcut("execution(* *man.*o*(*))")
        public void pointcut() {
        }

        /**
         * 所有的通知可以理解是要扩展的业务
         */
        @Before("pointcut()")
        public void before(JoinPoint point) {
            String methodName = point.getSignature().getName();
            p(methodName + " 打开连接，开启事务");
        }

        @AfterReturning("pointcut()")
        public void afterReturning() {
            p("提交事务");
        }

        @AfterThrowing("pointcut()")
        public void afterThrowing() {
            p("回滚事务");
        }

        @After("pointcut()")
        public void after() {
            p("关闭连接");
        }
    }
}
