package knowledge.concurrent.util;

import com.google.common.collect.Lists;
import l.demo.Demo;
import l.demo.Person;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.*;
import java.util.stream.IntStream;

/**
 * Atomic
 * <p>Atomic 类是通过自旋 CAS 操作 volatile 变量实现的
 * <p>CAS：Compare And Swap，解决多线程并行情况下使用锁造成性能损耗的一种机制，这是硬件实现的原子操作
 * <pre>
 * 1 三个基本操作数：内存地址 V，旧的预期值 A，修改后的新值 B
 * 2 定义：更新一个变量时，只有当变量的预期值 A 和内存地址 V 当中的实际值相同时，才会将内存地址 V 对应的值修改为 B
 * 3 Java CAS 的底层实现：lock cmpxchg
 * 4 在高并发场景下，CAS 的冲突概率大，会导致经常自旋，影响整体效率
 *
 * <a href="https://www.sohu.com/a/314272265_120104204">什么是 CAS 机制</a>
 * </pre>
 * 原子性：不会被线程调度机制打断的操作，要么全都操作成功，要么全都失败，不能只操作成功其中的一部分
 * <p>原子类分类：
 * <pre>
 * 1 基本类型：AtomicInteger, AtomicLong, AtomicBoolean
 * 2 数组类型：AtomicIntegerArray, AtomicLongArray, AtomicReferenceArray
 * 3 引用类型：AtomicReference, AtomicStampedReference, AtomicMarkableReference
 * 4 更新器：AtomicIntegerFieldUpdater, AtomicLongFieldUpdater, AtomicReferenceFieldUpdater
 * 5 累加器：LongAdder, DoubleAdder
 * 6 积累器：LongAccumulator, DoubleAccumulator
 * </pre>
 * 优势：
 * <pre>
 * 1 粒度细：原子变量可以把竞争范围缩小到变量级别
 * 2 效率高：原子类底层利用了 CAS 操作，不会阻塞线程
 * </pre>
 *
 * @author ljh
 * @see <a href="https://www.jianshu.com/p/66758b960698">原子类（一）如何保证线程安全？</a>
 * @see <a href="https://www.jianshu.com/p/18aa29f72252">原子类（二）高并发下的 AtomicInteger 和 LongAdder</a>
 * @see <a href="https://www.jianshu.com/p/61fc48f84056">原子类（三）原子类和 volatile</a>
 * @see <a href="https://www.jianshu.com/p/85af7c8bc8a1">原子类（四）AtomicInteger 和 synchronized</a>
 * @see <a href="https://www.jianshu.com/p/492f1f5f7763">原子类（五）Adder 和 Accumulator</a>
 * @since 2020/11/17 0:52
 */
public class Atomic extends Demo {

    /**
     * AtomicReference      原子引用
     *
     * @see <a href="https://segmentfault.com/a/1190000015831791">JUC 之 Atomic 框架：AtomicReference</a>
     */
    @Test
    public void testAtomicReference() {
        // 初始引用
        Person initRef = personList.get(0);
        // 期望引用（旧的引用）
        Person expectedRef;
        // 新的引用
        Person newRef = personList.get(1);

        /* 1 AtomicReference */
        AtomicReference<Person> atomicRef = new AtomicReference<>(initRef);
        expectedRef = atomicRef.get();
        atomicRef.compareAndSet(expectedRef, newRef);
        p(atomicRef.get());

        /* 2 AtomicStampedReference */
        // AtomicStampedReference 加入 stamp（可理解成版本号，可记录引用被更改了多少次），解决 CAS 操作可能存在的 ABA 问题
        // 初始戳
        int initialStamp = 0;
        AtomicStampedReference<Person> atomicStampedRef = new AtomicStampedReference<>(initRef, initialStamp);
        int[] stampHolder = new int[1];
        // 获取旧的引用，并把 initialStamp 放入 stampHolder[0]
        expectedRef = atomicStampedRef.get(stampHolder);
        // 期望戳
        int expectedStamp = stampHolder[0];
        // 新的戳
        int newStamp = expectedStamp++;
        atomicStampedRef.compareAndSet(expectedRef, newRef, expectedStamp, newStamp);
        p(atomicStampedRef.getReference());

        /* 3 AtomicMarkableReference */
        // AtomicMarkableReference 使用 boolean 来检查引用是否被更改过，而不考虑引用被更改了多少次
        // 初始标记
        boolean initMark = false;
        AtomicMarkableReference<Person> atomicMarkableRef = new AtomicMarkableReference<>(initRef, initMark);
        boolean[] markHolder = new boolean[1];
        // 获取旧的引用，并把 initMark 放入 markHolder[0]
        expectedRef = atomicMarkableRef.get(markHolder);
        // 期望标记
        boolean expectedMark = markHolder[0];
        // 新的标记
        boolean newMark = !expectedMark;
        atomicMarkableRef.compareAndSet(expectedRef, newRef, expectedMark, newMark);
        p(atomicMarkableRef.getReference());
    }

    /**
     * Adder     累加器
     * <p>AtomicLong 多线程下，存在 flush() 和 refresh()，让其它线程感知变化，耗费很多资源，而且高并发场景下 CAS 冲突概率大
     * <p>LongAdder 引入了分段累加的概念，内部两个参数参与计数：变量 base 和 数组 Cell[]
     * <pre>
     * 1 base：竞争不激烈的情况下，直接把累加结果改到 base 变量上
     * 2 Cell[]：竞争激烈的情况下，各个线程会分散累加到自己所对应的 Cell[] 数组的某一个对象中，而不会大家共用同一个，在最后才 sum() 求和，而不需要 flush() 和 refresh()
     * </pre>
     *
     * @see <a href="https://www.jianshu.com/p/dddb531e403c">比 AtomicLong 更优秀的 LongAdder</a>
     */
    @Test
    public void testAtomicLongAdder() throws InterruptedException {
        this.testEff(1, TEN_MILLION);
        this.testEff(10, TEN_MILLION);
        this.testEff(100, TEN_MILLION);
    }

    private void testEff(int threadCount, int times) throws InterruptedException {
        p("threadCount: " + threadCount + ", times: " + times);
        testLongAdder(threadCount, times);
        testAtomicLong(threadCount, times);
        p("--------------------------------------------------");
    }

    private void testLongAdder(int threadCount, int times) throws InterruptedException {
        LongAdder longAdder = new LongAdder();
        List<Thread> threadList = Lists.newArrayList();
        StopWatch stopWatch = StopWatch.createStarted();
        for (int i = 0; i < threadCount; i++) {
            threadList.add(new Thread(() -> IntStream.rangeClosed(1, times).forEach(j -> longAdder.increment())));
        }
        for (Thread thread : threadList) {
            thread.start();
        }
        for (Thread thread : threadList) {
            thread.join();
        }
        p("LongAdder value is: " + longAdder.longValue());
        p("LongAdder spend: " + stopWatch.getTime() + "ms");
    }

    private void testAtomicLong(int threadCount, int times) throws InterruptedException {
        AtomicLong atomicLong = new AtomicLong();
        List<Thread> threadList = Lists.newArrayList();
        StopWatch stopWatch = StopWatch.createStarted();
        for (int i = 0; i < threadCount; i++) {
            threadList.add(new Thread(() -> {
                for (int j = 0; j < times; j++) {
                    atomicLong.incrementAndGet();
                }
            }));
        }
        for (Thread thread : threadList) {
            thread.start();
        }
        for (Thread thread : threadList) {
            thread.join();
        }
        p("AtomicLong value is: " + atomicLong.get());
        p("AtomicLong spend: " + stopWatch.getTime() + "ms");
    }

    /**
     * Accumulator  积累器
     * Adder 的功能增强版，提供了自定义的函数操作
     */
    @Test
    public void testAccumulator() throws InterruptedException {
        LongAccumulator longAccumulator = new LongAccumulator(Long::sum, 0);
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        setCountDownLatch(MILLION);
        IntStream.rangeClosed(1, MILLION).forEach(i -> threadPool.submit(() -> {
            longAccumulator.accumulate(i);
            countDownLatch.countDown();
        }));
        countDownLatch.await();
        p(longAccumulator.getThenReset()); // 500000500000
    }
}
