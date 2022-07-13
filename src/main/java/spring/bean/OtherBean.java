package spring.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.List;

import static l.demo.Demo.p;

/**
 * LazyBean
 *
 * @author ljh
 * created on 2020/11/25 15:29
 */
@Data
@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OtherBean {

    @Autowired
    @Qualifier("BEAN")
    private Bean bean;

    @Value("x,y,z.split(',')")
    private List<String> str;

    // 'BEAN' 为注册 Bean 的 id
    @Value("#{BEAN.score}")
    private String score;

    // 要配置 <context:property-placeholder/>
    @Value("${jdbc.password}")
    private String password;

    @NumberFormat(pattern = "#,###")
    private double salary;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date payDate;

    public OtherBean() {
        p("Lazy.construct()");
    }

    @PostConstruct
    public void postConstruct() {
        p("Lazy.postConstruct()");
    }

    public void service() {
        p("Lazy.service()");
    }

    @PreDestroy
    public void preDestroy() {
        p("Lazy.preDestroy()");
    }
}
