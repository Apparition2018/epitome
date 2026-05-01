package springboot.config;

import knowledge.concurrent.pool.ThreadPoolExecutorDemo;
import org.jspecify.annotations.Nullable;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * AsyncConfig
 *
 * @author ljh
 * @since 2026/4/30 23:41
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    /**
     * 处理异步方法(@Async)调用
     * <p>@Async 查找线程池流程：
     * <pre>
     * 1 指定线程池：找不到 抛出 NoSuchBeanDefinitionException
     * 2 没有指定线程池，是否实现了 {@link AsyncConfigurer#getAsyncExecutor()}
     *  2.1 是
     *      2.1.1 not null → 使用配置的线程池
     *      2.1.2 null → 使用 {@link SimpleAsyncTaskExecutor}：不是线程池，每个任务新创建一个线程的简易异步执行器
     *  2.2 否
     *      2.2.1 查找唯一的 TaskExecutor Bean
     *          注：容器中没有其他 Executor Bean 时，Spring Boot 会创建 线程名前缀 "task-" 的 ThreadPoolTaskExecutor
     *              queueCapacity 默认 Integer.MAX_VALUE → OOM
     *      2.2.2 查找 beanName = "taskExecutor" 的 Executor
     *      2.2.3 使用 SimpleAsyncTaskExecutor
     * </pre
     */
    @Override
    public @Nullable Executor getAsyncExecutor() {
        return ioBoundTaskExecutor();
    }

    /**
     * 为异步方法(@Async)提供异常处理
     * <p>仅对返回 void 的方法，其它方法异常可通过 future.get() 传递给调用方
     * <p>@Async 方法支持且建议的返回类型：①void ②Future ③CompletableFuture
     */
    @Override
    public @Nullable AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return AsyncConfigurer.super.getAsyncUncaughtExceptionHandler();
    }

    /**
     * IO 密集型任务线程池（非常适合异步）
     * <pre>
     * 1. MQ 消息发送
     * 2. 发送通知（短信/邮件/推送）
     * 3. 数据同步到 ES / Redis（商品信息/缓存预热/全文检索/实时榜单）
     * 4. 写操作日志
     * 5. 导出大批量数据文件（①查DB-IO ②生成文件-CPU ③上传-IO）
     * 6. 导入大批量数据文件（①解析文件-CPU ②写DB-IO）
     * </pre>
     */
    @Bean
    public ThreadPoolTaskExecutor ioBoundTaskExecutor() {
        int cpuCores = Runtime.getRuntime().availableProcessors();
        // @Bean 管理的 ThreadPoolTaskExecutor，不需要手动 initialize()
        // Spring 会在 afterPropertiesSet() 时自动调用
        // executor.initialize();
        return this.createBaseExecutor(cpuCores * 2, cpuCores * 4, 1000, "IoBoundTask-", new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * CPU 密集型任务线程池（有限适合异步）
     * <pre>
     * 1. 批量图片处理（压缩/缩略图/多尺寸/水印/OCR 识别）
     * 2. 加密/签名
     * </pre>
     */
    @Bean
    public ThreadPoolTaskExecutor cpuBoundTaskExecutor() {
        int cpuCores = Runtime.getRuntime().availableProcessors();
        return this.createBaseExecutor(cpuCores + 1, cpuCores + 1, 200, "CpuBoundTask-", new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * @see ThreadPoolTaskExecutor
     * @see ThreadPoolExecutorDemo.MyRejectHandler
     */
    private ThreadPoolTaskExecutor createBaseExecutor(int coreSize, int maxSize, int queueCap, String prefix, RejectedExecutionHandler handler) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(coreSize);
        executor.setMaxPoolSize(maxSize);
        executor.setKeepAliveSeconds(60);
        // 默认使用 LinkedBlockingQueue
        executor.setQueueCapacity(queueCap);
        executor.setThreadNamePrefix(prefix);
        // 自定义线程工厂
        // executor.setThreadFactory(new MyThreadFactory());
        executor.setRejectedExecutionHandler(handler);
        // 设置是否在关闭时等待计划任务完成，不中断正在运行的任务并执行队列中的所有任务
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 设置在关机时等待任务完成的最大秒数
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }
}
