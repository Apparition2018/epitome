package knowledge.suggestions;

import java.util.concurrent.TimeUnit;

/**
 * 建议122：使用线程异常处理器提升系统可靠性
 *
 * @author ljh
 * @since 2020/10/10 19:23
 */
class TcpServer implements Runnable {

    TcpServer() {
        Thread t = new Thread(this);
        t.setUncaughtExceptionHandler(new TcpServerExceptionHandler());
        t.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("系统正常运： " + i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // 模拟异常
        throw new RuntimeException();
    }

    private static class TcpServerExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            // 记录线程异常信息
            System.out.println("线程" + t.getName() + " 出现异常，自行重启，请分析原因。");
            e.printStackTrace();
            // 重启线程，保证业务不中断
            new TcpServer();
        }
    }
}
