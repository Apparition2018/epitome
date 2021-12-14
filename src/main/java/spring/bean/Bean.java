package spring.bean;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;

import static l.demo.Demo.p;

/**
 * Bean
 *
 * @author ljh
 * created on 2020/11/25 15:28
 */
@Data
@Component("BEAN")
public class Bean implements InitializingBean, DisposableBean, ApplicationContextAware {

    public Integer score = 100;

    public Properties properties;

    public Bean() {
        p("Bean's construct()");
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        p("XxxAware's setXxx()");
    }

    @PostConstruct
    public void postConstruct() {
        p("@postConstruct()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        p("InitializingBean()'s afterPropertiesSet()");
    }

    public void initMethod() {
        p("init-method");
    }

    public void service() {
        p("Bean's service()");
    }

    @PreDestroy
    public void preDestroy() {
        p("@preDestroy()");
    }

    @Override
    public void destroy() throws Exception {
        p("DisposableBean()'s destroy()");
    }

    public void destroyMethod() {
        p("destroy-method");
    }
}
