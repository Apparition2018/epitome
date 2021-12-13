package spring.bean;

import lombok.Data;
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
public class Bean {
    
    public Integer score = 100;
    
    public Properties properties;

    public Bean() {
        p("Bean.construct()");
    }

    @PostConstruct
    public void postConstruct() {
        p("Bean.postConstruct()");
    }

    public void init() {
        p("Bean.init()");
    }

    public void service() {
        p("Bean.service()");
    }

    @PreDestroy
    public void preDestroy() {
        p("Bean.preDestroy()");
    }

    public void destroy() {
        p("Bean.destroy()");
    }
}
