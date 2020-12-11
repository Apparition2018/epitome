package spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.service.GameService;

/**
 * GameServiceConfig
 *
 * @author ljh
 * created on 2020/12/11 9:53
 */
@Configuration
public class GameServiceConfig {

    @Bean
    public GameService a() {
        return () -> System.out.println("= 0");
    }

    @Bean
    public GameService b() {
        return () -> System.out.println("< 0");
    }

    @Bean
    public GameService c() {
        return () -> System.out.println("> 0");
    }
}
