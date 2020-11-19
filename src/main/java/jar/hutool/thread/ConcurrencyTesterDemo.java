package jar.hutool.thread;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import org.junit.Test;

/**
 * ConcurrencyTester    高并发测试
 * https://hutool.cn/docs/#/core/%E7%BA%BF%E7%A8%8B%E5%92%8C%E5%B9%B6%E5%8F%91/%E9%AB%98%E5%B9%B6%E5%8F%91%E6%B5%8B%E8%AF%95-ConcurrencyTester
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/thread/ConcurrencyTester.html
 *
 * @author ljh
 * created on 2020/11/19 15:30
 */
public class ConcurrencyTesterDemo {

    @Test
    public void testConcurrencyTester() {
        ConcurrencyTester tester = ThreadUtil.concurrencyTest(100, () -> {
            // 测试的逻辑内容
            long delay = RandomUtil.randomLong(100, 1000);
            ThreadUtil.sleep(delay);
            Console.log("{} test finished, delay: {}", Thread.currentThread().getName(), delay);
        });

        // 获取总的执行时间，单位毫秒
        Console.log(tester.getInterval());
    }
}
