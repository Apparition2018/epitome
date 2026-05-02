package springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import springboot.config.AsyncConfig;
import springboot.config.WebMvcConfig;

import java.util.concurrent.*;

/**
 * 异步
 * <pre>
 * 1 异步方法：{@link AsyncConfig#getAsyncExecutor() @Async}，客户端不等待结果
 * 2 异步请求：{@link WebMvcConfig#configureAsyncSupport Callable/WebAsyncTask/DeferredResult}，客户端等待结果，释放 Tomcat 线程。
 *      解决的问题不是“服务器变快了”，是慢接口不抢 Tomcat 线程，快接口不被慢接口堵死
 *  1 {@link #asyncRequestCallable() Callable}：结果不需要外部系统
 *  2 {@link #asyncRequestWebAsyncTask() WebAsyncTask}：同 Callable，但需要自定义超时时间和超时回调
 *  3 DeferredResult：结果来自外部系统（长轮询、支付回调、MQ 消费者回调、事件驱动）
 * </pre>
 *
 * @author ljh
 * @since 2026/5/1 1:29
 */
@Slf4j
@RestController
@RequestMapping("/async")
@Tag(name = "GlobalExecutors")
public class AsyncController {

    private final ApplicationContext applicationContext;

    public AsyncController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /** <a href="http://localhost:3333/async/method/void">asyncMethodVoid</a> */
    @RequestMapping("/method/void")
    @Operation(summary = "@Async - 发送邮件（客户端不等待结果）")
    public ResponseEntity<String> asyncMethodVoid() {
        self().sendEmail();
        return ResponseEntity.ok("ok");
    }

    /** <a href="http://localhost:3333/async/method/future">asyncMethodFuture</a> */
    @RequestMapping("/method/future")
    @Operation(summary = "@Async - 并行查询汇总结果（客户端等待结果）")
    public ResponseEntity<String> asyncMethodFuture() {
        CompletableFuture<Void> userFuture = self().queryUser();
        CompletableFuture<Void> orderFuture = self().queryOrder();
        CompletableFuture.allOf(userFuture, orderFuture).join();
        return ResponseEntity.ok("ok");
    }

    /** <a href="http://localhost:3333/async/request/callable">asyncRequestCallable</a> */
    @RequestMapping("/request/callable")
    @Operation(summary = "Callable - 生成用户仪表盘")
    public Callable<ResponseEntity<String>> asyncRequestCallable() {
        return this::buildDashboard;
    }

    /** <a href="http://localhost:3333/async/request/webAsyncTask">asyncRequestWebAsyncTask</a> */
    @RequestMapping("/request/webAsyncTask")
    @Operation(summary = "WebAsyncTask - 生成用户仪表盘（超时3秒返回兜底）")
    public WebAsyncTask<ResponseEntity<String>> asyncRequestWebAsyncTask() {
        // 本质：WebAsyncTask = Callable + 超时控制 + 超时回调
        WebAsyncTask<ResponseEntity<String>> task = new WebAsyncTask<>(3_000, this::buildDashboard);
        task.onTimeout(() -> ResponseEntity.ok("timeout"));
        return task;
    }

    private ResponseEntity<String> buildDashboard() {
        CompletableFuture<Void> userFuture = self().queryUser();
        CompletableFuture<Void> orderFuture = self().queryOrder();
        return userFuture
            // 并行
            .thenCombine(orderFuture, (user, order) -> 1)
            // 串行
            .thenCompose(uid -> self().genDashboard())
            .thenApply(o -> ResponseEntity.ok("ok"))
            .join();
    }

    private final ConcurrentHashMap<String, DeferredResult<String>> pendingPayments = new ConcurrentHashMap<>();

    /** <a href="http://localhost:3333/async/request/deferredResult?orderId=123">asyncRequestDeferredResult</a> */
    @RequestMapping("/request/deferredResult")
    @Operation(summary = "DeferredResult - 等待支付结果（30秒超时）")
    public DeferredResult<String> asyncRequestDeferredResult(@RequestParam String orderId) {
        DeferredResult<String> result = new DeferredResult<>(30_000L, "pay timeout");
        result.onCompletion(() -> pendingPayments.remove(orderId));
        pendingPayments.put(orderId, result);
        // 立即释放 Tomcat 线程，等支付回调线程调用 result.setResult()
        return result;
    }

    /** <a href="http://localhost:3333/async/request/deferredResul/payCallback?orderId=123">模拟支付回调</a> */
    @RequestMapping("/request/deferredResult/payCallback")
    @Operation(summary = "DeferredResult - 模拟支付回调")
    public ResponseEntity<String> payCallback(@RequestParam String orderId) {
        DeferredResult<String> result = pendingPayments.remove(orderId);
        if (result == null) return ResponseEntity.ok("done or timeout");
        result.setResult("order " + orderId + " pay success");
        return ResponseEntity.ok("ok");
    }

    @Async
    public void sendEmail() {
        self().retrySendEmail();
    }

    /**
     * {@code @Async} + {@code @Retryable}：无法合到一个方法上使用，以下为解决方法
     * <pre>
     * 1 @Retryable + 注入 {@link AsyncConfig#ioBoundTaskExecutor()}
     * 2 @Async + 编程式 RetryTemplate
     * </pre>
     */
    @Retryable(maxRetries = 3, delay = 300)
    public void retrySendEmail() {
        Integer.parseInt("a");
        simulateWork("发送邮件");
    }

    @Async
    public CompletableFuture<Void> queryUser() {
        simulateWork("查询用户");
        return CompletableFuture.completedFuture(null);
    }

    @Async
    public CompletableFuture<Void> queryOrder() {
        simulateWork("查询订单");
        return CompletableFuture.completedFuture(null);
    }

    @Async
    public CompletableFuture<Void> genDashboard() {
        simulateWork("生成仪表");
        return CompletableFuture.completedFuture(null);
    }

    @SneakyThrows
    private void simulateWork(String taskName) {
        int elapsedTime = ThreadLocalRandom.current().nextInt(1, 3);
        TimeUnit.SECONDS.sleep(elapsedTime);
        System.out.printf("%s 线程: %s，耗时%s秒%n", taskName, Thread.currentThread().getName(), elapsedTime);
    }

    private AsyncController self() {
        return applicationContext.getBean(AsyncController.class);
    }
}
