package org.ljh.flyway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

/**
 * FlywayApplicationTests
 *
 * @author ljh
 * @since 2026/7/18
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class FlywayApplicationTests {

    @Test
    void contextLoads() throws InterruptedException {
        new CountDownLatch(1).await();
    }
}
