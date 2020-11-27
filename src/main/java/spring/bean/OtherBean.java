package spring.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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
@Scope("prototype")
public class OtherBean {

    @Autowired
    @Qualifier("BEAN")
    private Bean bean;

    @Value("x,y,z.split(',')")
    private List<String> str;

    @Value("#{BEAN.score}")     // 'BEAN' 为注册 Bean 的 id
    private String score;

    @Value("${jdbc.password}")  // 要配置 <context:property-placeholder/>
    private String password;
    
    @NumberFormat(pattern = "#,###")
    private double salary;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date payDate;

    public OtherBean() {
        p("Lazy.construct()");
    }

    @PostConstruct
    public void init() {
        p("Lazy.init()");
    }

    public void service() {
        p("LLazy.service()");
    }

    @PreDestroy
    public void destroy() {
        p("Lazy.destroy()");
    }
}
