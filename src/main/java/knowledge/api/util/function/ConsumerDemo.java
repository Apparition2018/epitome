package knowledge.api.util.function;

import l.demo.Demo;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Consumer<T>
 * 接收一个参数，不返回结果，用来对参数进行任意(消费)处理
 * https://www.jianshu.com/p/63771441ba31
 *
 * @author ljh
 * created on 2020/9/9 14:02
 */
public class ConsumerDemo extends Demo {

    @Test
    public void testConsumer() {
        Consumer<String> consumer1 = o -> {
            p("length: " + o.length());
        };

        Consumer<String> consumer2 = o -> {
            p("isStartsWithA: " + o.startsWith(o));
        };

        consumer1.andThen(consumer2).accept("ABC");
        // length: 3
        // isStartsWithA: true
    }
}
