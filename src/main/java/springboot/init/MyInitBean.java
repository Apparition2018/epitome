package springboot.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 顺序：
 * 1. PostConstruct
 * 2. InitializingBean
 * 3. ApplicationRunner
 * 4. CommandLineRunner
 * <p>
 * SpringBoot 启动初始化数据：https://www.jianshu.com/p/01e08aef73c9
 *
 * @author ljh
 * @since 2021/12/10 15:33
 */
@Slf4j
@Component
@Order(1)
public class MyInitBean implements InitializingBean, ApplicationRunner, CommandLineRunner {

    @PostConstruct
    public void postConstruct() {
        log.warn("PostConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.warn("InitializingBean");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.warn("ApplicationRunner");
    }

    @Override
    public void run(String... args) throws Exception {
        log.warn("CommandLineRunner");
    }
}
