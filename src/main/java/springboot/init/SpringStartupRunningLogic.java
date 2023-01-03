package springboot.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import javax.annotation.PostConstruct;

/**
 * 启动顺序：
 * <pre>
 * 1 @Bean's Constructor
 * 2 @PostConstruct
 * 3 InitializingBean's afterPropertiesSet()
 * 4 @Bean's InitMethod
 * 5 ContextRefreshedEvent
 * 6 ApplicationRunner's run()
 * 7 CommandLineRunner's run()
 * </pre>
 * 参考：
 * <pre>
 * <a href="https://www.jianshu.com/p/01e08aef73c9">Guide To Running Logic on Startup in Spring</a>
 * <a href="https://www.jianshu.com/p/01e08aef73c9">SpringBoot 启动初始化数据</a>
 * </pre>
 *
 * @author ljh
 * @since 2021/12/10 15:33
 */
@Slf4j
@Configuration
public class SpringStartupRunningLogic {

    @Bean(initMethod = "init")
    public ExampleBean initMethodExampleBean() {
        return new ExampleBean();
    }

    private static class ExampleBean implements InitializingBean, ApplicationRunner, CommandLineRunner {
        public ExampleBean() {
            log.warn("@Bean's Constructor");
        }

        @PostConstruct
        public void postConstruct() {
            log.warn("@PostConstruct");
        }

        @Override
        public void afterPropertiesSet() {
            log.warn("InitializingBean's afterPropertiesSet()");
        }

        public void init() {
            log.warn("@Bean's InitMethod");
        }

        @EventListener
        public void contextStarted(ContextRefreshedEvent event) {
            log.warn("ContextRefreshedEvent");
        }

        @Override
        public void run(ApplicationArguments args) throws Exception {
            log.warn("ApplicationRunner's run()");
        }

        @Override
        public void run(String... args) throws Exception {
            log.warn("CommandLineRunner's run()");
        }
    }
}
