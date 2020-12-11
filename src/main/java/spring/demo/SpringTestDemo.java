package spring.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import spring.config.GameServiceConfig;
import spring.service.GameService;

import java.util.List;

/**
 * SpringTest
 *
 * @author ljh
 * created on 2020/12/11 9:21
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {GameServiceConfig.class})
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
