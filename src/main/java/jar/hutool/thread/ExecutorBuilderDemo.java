package jar.hutool.thread;

import cn.hutool.core.thread.ExecutorBuilder;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * ExecutorBuilder      自定义线程池
 * https://hutool.cn/docs/#/core/%E7%BA%BF%E7%A8%8B%E5%92%8C%E5%B9%B6%E5%8F%91/%E8%87%AA%E5%AE%9A%E4%B9%89%E7%BA%BF%E7%A8%8B%E6%B1%A0-ExecutorBuilder
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/thread/ExecutorBuilder.html
 *
 * @author ljh
 * created on 2020/11/19 15:29
 */
public class ExecutorBuilderDemo {

    @Test
    public void testExecutorBuilder() {
        ExecutorService pool;
        // 1.默认线程池
        // 初始线程数为corePoolSize指定的大小
        // 没有最大线程数限制
        // 默认使用LinkedBlockingQueue，默认队列大小为1024（最大等待数1024）
        // 当运行线程大于corePoolSize放入队列，队列满后抛出异常
        pool = ExecutorBuilder.create().build();

        // 2.单线程线程池
        // 初始线程数为 1
        // 最大线程数为 1
        // 默认使用LinkedBlockingQueue，默认队列大小为1024
        // 同时只允许一个线程工作，剩余放入队列等待，等待数超过1024报错
        pool = ExecutorBuilder.create().setCorePoolSize(1).setMaxPoolSize(1).setKeepAliveTime(0).build();

        // 3.更多选项的线程池
        // 初始5个线程
        // 最大10个线程
        // 有界等待队列，最大等待数是100
        pool = ExecutorBuilder.create().setCorePoolSize(5).setMaxPoolSize(10)
                .setWorkQueue(new LinkedBlockingQueue<>(100)).build();

        // 4.特殊策略的线程池
        // 初始5个线程
        // 最大10个线程
        // 它将任务直接提交给线程而不保持它们。当运行线程小于maxPoolSize时会创建新线程，否则触发异常策略
        pool = ExecutorBuilder.create().setCorePoolSize(5).setMaxPoolSize(10)
                .useSynchronousQueue().build();
    }
}
