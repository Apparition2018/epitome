package jar.hutool.thread;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;

import java.io.IOException;

/**
 * <a href="https://hutool.cn/docs/#/core/线程和并发/高并发测试-ConcurrencyTester">ConcurrencyTester</a>  高并发测试
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/thread/ConcurrencyTester.html">ConcurrencyTester api</a>
 *
 * @author ljh
 * @since 2020/11/19 15:30
 */
public class ConcurrencyTesterDemo {

    public static void main(String[] args) {
        try (ConcurrencyTester tester = ThreadUtil.concurrencyTest(100, () -> {
            // 测试的逻辑内容
            long delay = RandomUtil.randomLong(100, 1000);
            ThreadUtil.sleep(delay);
            Console.log("{} test finished, delay: {}", Thread.currentThread().getName(), delay);
        })) {
            // 获取总的执行时间，单位毫秒
            Console.log(tester.getInterval());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
