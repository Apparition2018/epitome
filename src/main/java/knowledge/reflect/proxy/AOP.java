package knowledge.reflect.proxy;

import knowledge.reflect.proxy.domain.People;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static l.demo.Demo.p;

/**
 * AOP
 * 详解请查看 spring-aop 项目
 * RuoYi 系统日志 (LogAspect)：http://doc.ruoyi.vip/ruoyi/document/htsc.html#%E7%B3%BB%E7%BB%9F%E6%97%A5%E5%BF%97
 * RuoYi 数据权限 (DataScopeAspect)：http://doc.ruoyi.vip/ruoyi/document/htsc.html#%E6%95%B0%E6%8D%AE%E6%9D%83%E9%99%90
 *
 * @author ljh
 * @since 2020/11/10 15:43
 */
public class AOP {

    ClassPathXmlApplicationContext applicationContext;

    @BeforeEach
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
    }

    @Test
    public void aop() {
        People people = applicationContext.getBean("man", People.class);
        people.work();
        people.sleep();
    }

    @AfterEach
    public void destroy() {
        applicationContext.close();
    }


    @Order(1)
    @Aspect
    @Component
    static class StopWatchAspect {

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
    static class ConnectionAspect {

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
    static class TransactionAspect {

        @Pointcut("@annotation(knowledge.reflect.proxy.domain.Man.AOP)")
        public void pointcut1() {
        }

        @Pointcut("within(knowledge.reflect.proxy..*) && args()")
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
