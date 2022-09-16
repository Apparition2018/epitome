package spring.bean;

import lombok.Data;
import lombok.experimental.Accessors;
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

import static l.demo.Demo.pe;

/**
 * Bean
 *
 * @author ljh
 * created on 2020/11/25 15:28
 */
@Data
@Accessors(chain = true)
@Component("BEAN")
public class Bean implements InitializingBean, DisposableBean, ApplicationContextAware {

    public Integer score = 100;

    public Properties properties;

    public Bean() {
        pe("Bean's construct()");
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        pe("XxxAware's setXxx()");
    }

    @PostConstruct
    public void postConstruct() {
        pe("@postConstruct()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        pe("InitializingBean()'s afterPropertiesSet()");
    }

    public void initMethod() {
        pe("init-method");
    }

    public void service() {
        pe("Bean's service()");
    }

    @PreDestroy
    public void preDestroy() {
        pe("@preDestroy()");
    }

    @Override
    public void destroy() throws Exception {
        pe("DisposableBean()'s destroy()");
    }

    public void destroyMethod() {
        pe("destroy-method");
    }
}
