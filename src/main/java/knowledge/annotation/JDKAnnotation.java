package knowledge.annotation;

// import jdk.internal.vm.annotation.Contended;

import l.demo.Animal.Chicken;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static l.demo.Demo.p;

/**
 * JDKAnnotation
 * <p>JDK 自带注解
 * <pre>
 * 1 @Override
 * 2 @Deprecation
 * 3 <a href="https://www.cnblogs.com/fsjohnhuang/p/4040785.html">@SuppressWarnings</a> 抑制编译器产生警告信息
 * 4 @SafeVarargs
 * 5 @Contended
 * </pre>
 *
 * @author ljh
 * @since 2020/9/18 10:13
 */
public class JDKAnnotation {

    /**
     * deprecation              过期
     * <p>外部正在调用的接口或者二方库依赖的接口，不允许修改方法签名，避免对接口调用方产生影响。接口过时必须加 @Deprecated 注解，并清晰地说明采用的新接口或者新服务是什么（阿里编程规约）
     * <p><a href="https://openjdk.org/jeps/277">JDK9 JEP 277: Enhanced Deprecation</a>
     * <pre>
     * 1 since      被弃用的版本
     * 2 forRemoval 在将来的版本中会否被删除
     * </pre>
     */
    @Deprecated(since = "2.x", forRemoval = true)
    public void deprecation() {
        Chicken chicken = new Chicken();
        chicken.fly();
    }

    /** unused                  未使用 */
    public void unused() {
        @SuppressWarnings("unused")
        int max = Math.max(2, 1);
    }

    /**
     * rawtypes                 原生类型未使用泛型<br/>
     * unchecked                未检测转换
     */
    @SuppressWarnings(value = {"unchecked", "rawtypes"})
    public void rawTypesAndUnchecked(String item) {
        List<Object> items = new ArrayList();
        items.add(item);
        p(items.size());
    }

    /** InfiniteLoopStatement   无线循环语句 */
    @SuppressWarnings("InfiniteLoopStatement")
    public void infiniteLoopStatement() {
        while (true) {
            p(StringUtils.SPACE);
        }
    }

    /** @see <a href="https://www.cnblogs.com/cxuanBlog/p/10927483.html">@SafeVarargs</a> */
    @SafeVarargs
    public static <T> List<T> safeVarargs(T... t) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, t);
        return list;
    }

    // /**
    //  * Contended
    //  * <p>JDK8 新增的注解，减少伪共享(False Sharing)的发生
    //  *
    //  * @see <a href="https://www.zhihu.com/zvideo/1312762510748577792">伪共享问题及如何解决方案</a>
    //  */
    // static class ContendedDemo {
    //     private volatile long x;
    //     private volatile long y;
    //     @Contended
    //     private volatile long x2;
    //     @Contended
    //     private volatile long y2;
    //
    //     @Test
    //     public void contented() throws InterruptedException {
    //         Thread t1 = new Thread(() -> IntStream.rangeClosed(1, HUNDRED_MILLION).forEach(i -> x = i));
    //         Thread t2 = new Thread(() -> IntStream.rangeClosed(1, HUNDRED_MILLION).forEach(i -> y = i));
    //         stopWatch.start("contented not used");
    //         t1.start();
    //         t2.start();
    //         t1.join();
    //         t2.join();
    //         stopWatch.stop();
    //
    //         Thread t3 = new Thread(() -> IntStream.rangeClosed(1, HUNDRED_MILLION).forEach(i -> x2 = i));
    //         Thread t4 = new Thread(() -> IntStream.rangeClosed(1, HUNDRED_MILLION).forEach(i -> y2 = i));
    //         stopWatch.start("contented used");
    //         t3.start();
    //         t4.start();
    //         t3.join();
    //         t4.join();
    //         stopWatch.stop();
    //
    //         p(stopWatch.prettyPrint());
    //         // ---------------------------------------------
    //         // ns         %     Task name
    //         // ---------------------------------------------
    //         // 197255399  066%  contented not used
    //         // 102313399  034%  contented used
    //     }
    // }
}
