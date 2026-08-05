package knowledge.suggestions;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 建议122：使用线程异常处理器提升系统可靠性
 *
 * @author ljh
 * @since 2020/10/10 19:23
 */
@Slf4j
class TcpServer implements Runnable {

    TcpServer() {
        Thread t = new Thread(this);
        t.setUncaughtExceptionHandler(new TcpServerExceptionHandler());
        t.start();
    }

    @Override
    @SneakyThrows
    public void run() {
        for (int i = 0; i < 3; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("系统正常运： " + i);
        }
        // 模拟异常
        throw new RuntimeException();
    }

    private static class TcpServerExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            // 记录线程异常信息
            log.warn("线程 {} 出现异常，自行重启，请分析原因。", t.getName(), e);
            // 重启线程，保证业务不中断
            new TcpServer();
        }
    }
}
