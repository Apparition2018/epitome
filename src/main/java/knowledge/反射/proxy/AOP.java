package knowledge.反射.proxy;

import knowledge.反射.proxy.domain.People;
import l.demo.Demo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * AOP
 * 详解请查看 spring-aop 项目
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
    public void aop() {
        People people = (People) ac.getBean("man");
        people.work();
        people.sleep();
    }

    @org.junit.After
    public void destroy() {
        ac.close();
    }


    @Order(1)
    @Aspect
    @Component
    public static class StopWatchAspect {

        long start;
        long end;
        String methodName;

        @Pointcut("execution(public * knowledge..Man.*(..))")
        public void pointcut() {
        }

        /**
         * 前置通知
         */
        @Before("pointcut()")
        public void before(JoinPoint point) {
            methodName = point.getSignature().getName();
            start = System.currentTimeMillis();
            p(methodName + " start.time = " + start);
        }

        /**
         * 后置通知
         */
        @AfterReturning("pointcut()")
        public void afterReturning() {
        }

        /**
         * 异常通知
         */
        @AfterThrowing("pointcut()")
        public void afterThrowing() {
        }

        /**
         * 最终通知
         */
        @After("pointcut()")
        public void after() {
            end = System.currentTimeMillis();
            p(methodName + " end.time = " + end);
            p(methodName + " execute spend [" + (end - start) + "]ms.\n");
        }
    }

    @Order(2)
    @Aspect
    @Component
    public static class ConnectionAspect {

        /**
         * 环绕通知
         */
        @Around("bean(man)")
        public Object databaseConnection(ProceedingJoinPoint joinPoint) {
            try {
                p("get connection");
                Object returnObj;
                returnObj = joinPoint.proceed(joinPoint.getArgs());
                return returnObj;
            } catch (Throwable throwable) {
                return null;
            } finally {
                p("close connection");
            }
        }
    }

    @Order(3)
    @Aspect
    @Component
    public static class TransactionAspect {
        
        @Pointcut("@annotation(knowledge.反射.proxy.domain.Man.AOP)")
        public void pointcut1() {
        }

        @Pointcut("within(knowledge.反射.proxy..*) && args()")
        public void pointcut2() {
        }

        @Before("pointcut1()")
        public void before() {
            p("set auto commit false");
        }

        @AfterReturning("pointcut2()")
        public void afterReturning() {
            p("commit tx");
        }

        @AfterThrowing("pointcut1()")
        public void afterThrowing() {
            p("rollback tx");
        }

        @After("pointcut2()")
        public void after() {
        }
    }
}
