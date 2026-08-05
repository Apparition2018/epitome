package org.ljh.starter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示 spring-boot-starter 内置定时任务 {@code @Scheduled} 的三种用法。
 *
 * <p>三者区别：
 * <ul>
 *     <li>{@code cron}：通过 cron 表达式指定执行时刻（秒 分 时 日 月 周）</li>
 *     <li>{@code fixedDelay}：上一次执行<b>结束</b>到下一次开始的时间间隔（ms）</li>
 *     <li>{@code fixedRate}：上一次执行<b>开始</b>到下一次开始的时间间隔（ms），
 *         不会等待上次执行结束，可能并发触发</li>
 * </ul>
 */
@Service
public class TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    /** 记录三种任务各自被触发的次数，供测试断言验证 */
    private final AtomicInteger cronCount = new AtomicInteger();
    private final AtomicInteger fixedDelayCount = new AtomicInteger();
    private final AtomicInteger fixedRateCount = new AtomicInteger();

    /**
     * cron 表达式：每 5 秒执行一次（默认 spring 使用 6 段，首位为秒）。
     * <pre>
     *  ┌──────────── 秒 (0-59)
     *  │ ┌────────── 分 (0-59)
     *  │ │ ┌──────── 时 (0-23)
     *  │ │ │ ┌────── 日 (1-31)
     *  │ │ │ │ ┌──── 月 (1-12)
     *  │ │ │ │ │ ┌── 周 (0-7, 0/7 均为周日)
     *  │ │ │ │ │ │
     *  * * * * * *
     * </pre>
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void cronTask() {
        int n = cronCount.incrementAndGet();
        log.info("cronTask #{} 执行 @ {}", n, LocalDateTime.now().format(FORMAT));
    }

    /**
     * fixedDelay：上次执行结束后延时 2 秒再执行下一次。
     */
    @Scheduled(fixedDelay = 2000)
    public void fixedDelayTask() {
        int n = fixedDelayCount.incrementAndGet();
        log.info("fixedDelayTask #{} 执行 @ {}", n, LocalDateTime.now().format(FORMAT));
    }

    /**
     * fixedRate：固定间隔 1 秒执行一次，无论上次是否结束。
     */
    @Scheduled(fixedRate = 1000)
    public void fixedRateTask() {
        int n = fixedRateCount.incrementAndGet();
        log.info("fixedRateTask #{} 执行 @ {}", n, LocalDateTime.now().format(FORMAT));
    }

    public int getCronCount() {
        return cronCount.get();
    }

    public int getFixedDelayCount() {
        return fixedDelayCount.get();
    }

    public int getFixedRateCount() {
        return fixedRateCount.get();
    }
}
