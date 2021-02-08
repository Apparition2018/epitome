package spring.api.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import spring.config.GameServiceConfig;
import spring.service.GameService;

import java.util.List;

/**
 * SpringTest
 *
 * @author ljh
 * created on 2020/12/11 9:21
 */

// junit4
//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = {GameServiceConfig.class})

// junit5，相当于上面注解作用
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {GameServiceConfig.class})

// junit5，相当于上面注解作用
@SpringJUnitConfig(classes = {GameServiceConfig.class})
public class SpringTestDemo {

    @Autowired
    private List<GameService> gameServices;

    @Test
    public void testServiceList() {
        for (GameService gameService : gameServices) {
            gameService.play();
        }
    }
}
