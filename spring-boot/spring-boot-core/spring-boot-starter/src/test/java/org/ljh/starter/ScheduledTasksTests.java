package org.ljh.starter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ljh.starter.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

/**
 * 定时任务演示：应用启动后 {@code @Scheduled} 任务会自动开始调度，
 * 这里等待数秒后断言各任务已被触发。
 */
@SpringBootTest
class ScheduledTasksTests {

    @Autowired
    private TaskService taskService;

    @Test
    @DisplayName("fixedRateTask 每秒执行一次，2 秒内应被触发至少 1 次")
    void fixedRateTaskRuns() {
        await().atMost(Duration.ofSeconds(3))
            .untilAsserted(() -> assertThat(taskService.getFixedRateCount()).isGreaterThanOrEqualTo(1));
    }

    @Test
    @DisplayName("fixedDelayTask 每 2 秒执行一次，3 秒内应被触发至少 1 次")
    void fixedDelayTaskRuns() {
        await().atMost(Duration.ofSeconds(4))
            .untilAsserted(() -> assertThat(taskService.getFixedDelayCount()).isGreaterThanOrEqualTo(1));
    }

    @Test
    @DisplayName("cronTask 每 5 秒执行一次，6 秒内应被触发至少 1 次")
    void cronTaskRuns() {
        await().atMost(Duration.ofSeconds(7))
            .untilAsserted(() -> assertThat(taskService.getCronCount()).isGreaterThanOrEqualTo(1));
    }

    @Test
    @DisplayName("三种定时任务都注册并执行")
    void allTasksRun() {
        await().atMost(Duration.ofSeconds(7)).untilAsserted(() -> {
            assertThat(taskService.getCronCount()).isGreaterThanOrEqualTo(1);
            assertThat(taskService.getFixedDelayCount()).isGreaterThanOrEqualTo(1);
            assertThat(taskService.getFixedRateCount()).isGreaterThanOrEqualTo(1);
        });
    }
}
