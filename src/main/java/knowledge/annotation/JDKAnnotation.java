package knowledge.annotation;

import l.demo.Animal.Chicken;
import l.demo.Demo;
import org.apache.commons.lang3.time.StopWatch;
import sun.misc.Contended;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * JDKAnnotation
 * JDK 自带注解
 * 1. @Override
 * 2. @Deprecation
 * 3. @SuppressWarnings 抑制编译器产生警告信息：https://www.cnblogs.com/fsjohnhuang/p/4040785.html
 * 4. @SafeVarargs
 * 5. @Contended
 *
 * @author ljh
 * created on 2020/9/18 10:13
 */
public class JDKAnnotation extends Demo {

    /**
     * deprecation              过期
     * 外部正在调用的接口或者二方库依赖的接口，不允许修改方法签名，避免对接口调用方产生影响。接口过时必须加 @Deprecated 注解，并清晰地说明采用的新接口或者新服务是什么（阿里编程规约）
     */
    // @SuppressWarnings("deprecation")
    @Deprecated
    public void testDeprecation() {
        Chicken chicken = new Chicken();
        chicken.fly();
    }

    /**
     * unused                   未使用
     */
    public void testUnused() {
        @SuppressWarnings("unused")
        int max = Math.max(2, 1);
    }

    /**
     * rawtypes                 原生类型未使用泛型
     * unchecked                未检测转换
     */
    @SuppressWarnings(value = {"unchecked", "rawtypes"})
    public void testRawTypesAndUnchecked(String item) {
        List<Object> items = new ArrayList();
        items.add(item);
        p(items.size());
    }

    /**
     * InfiniteLoopStatement    无线循环语句
     */
    @SuppressWarnings("InfiniteLoopStatement")
    public void testInfiniteLoopStatement() {
        while (true) {
            p(" ");
        }
    }

    /**
     * SafeVarargs
     * https://www.cnblogs.com/cxuanBlog/p/10927483.html
     */
    @SafeVarargs
    public static <T> List<T> testSafeVarargs(T... t) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, t);
        return list;
    }

    /**
     * Contended
     * JDK8 新增的注解，减少伪共享(False Sharing)的发生
     * <p>
     * Java 经典面试题：伪共享问题及如何解决方案：https://www.zhihu.com/zvideo/1312762510748577792
     */
    static class ContendedDemo {
        @Contended
        private volatile static long x;
        @Contended
        private volatile static long y;
        private static final long TIMES = 100000000L;

        public static void main(String[] args) throws InterruptedException {
            StopWatch stopWatch = StopWatch.createStarted();
            Thread t1 = new Thread(() -> {
                for (long i = 0; i < TIMES; i++) {
                    x = i;
                }
            });
            Thread t2 = new Thread(() -> {
                for (long i = 0; i < TIMES; i++) {
                    y = i;
                }
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            p(stopWatch.getTime());
        }
    }
}
