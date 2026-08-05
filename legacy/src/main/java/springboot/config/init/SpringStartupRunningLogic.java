package springboot.config.init;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

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
 *
 * @author ljh
 * @see <a href="https://www.baeldung.com/running-setup-logic-on-startup-in-spring">Guide To Running Logic on Startup in Spring</a>
 * @since 2021/12/10 15:33
 */
@Slf4j
@Configuration
public class SpringStartupRunningLogic {

    @Bean(initMethod = "init")
    public ExampleBean initMethodExampleBean() {
        return new ExampleBean();
    }

    public static class ExampleBean implements InitializingBean, ApplicationRunner, CommandLineRunner {
        public ExampleBean() {
            log.info("@Bean's Constructor");
        }

        @PostConstruct
        public void postConstruct() {
            log.info("@PostConstruct");
        }

        @Override
        public void afterPropertiesSet() {
            log.info("InitializingBean's afterPropertiesSet()");
        }

        public void init() {
            log.info("@Bean's InitMethod");
        }

        @EventListener
        public void contextStarted(ContextRefreshedEvent event) {
            log.info("ContextRefreshedEvent");
        }

        // 假设启动命令：java -jar app.jar --name=John debug
        // args.getOptionValues("name") → ["John"]
        // args.getNonOptionArgs() → ["debug"]
        // 推荐使用 ApplicationRunner，省去手动解析命令行参数的麻烦
        @Override
        public void run(@NonNull ApplicationArguments args) throws Exception {
            log.info("ApplicationRunner's run()，args: {}", args);
        }

        // 假设启动命令：java -jar app.jar --name=John debug
        // args → ["--name=John", "debug"]
        @Override
        public void run(String @NonNull ... args) throws Exception {
            log.info("CommandLineRunner's run(), args: {}", (Object[]) args);
        }
    }
}
