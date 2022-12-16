package knowledge.types.basic;

import l.demo.Demo;
import l.demo.Person;
import org.junit.jupiter.api.Test;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Reference    引用
 * 强引用：抛出 OutOfMemory 错误也不会回收
 * 软引用：内存不足的时候 JVM 才会回收；缓存
 * 弱引用：无论内存是否充足，都会回收
 * 虚引用：跟没有引用与之关联一样，任何时候都可能被回收
 * <p>
 * Java 如何有效地避免 OOM：善于利用软引用和弱引用：https://www.cnblogs.com/dolphin0520/p/3784171.html
 *
 * @author ljh
 * @since 2020/11/7 23:00
 */
public class ReferenceDemo extends Demo {

    /**
     * 软引用
     * 你不可不知的 Java 引用类型之——软引用：https://www.cnblogs.com/mfrank/p/9781216.html
     */
    static class SoftReferenceDemo {

        /**
         * VM options: -verbose:gc -Xms4m -Xmx4m -Xmn2m
         */
        public static void main(String[] args) {
            SoftCache<OOMClass> softCache = new SoftCache<>();

            IntStream.rangeClosed(1, 40).forEach(i -> softCache.add(new OOMClass("OOM Obj-" + i)));
            p(softCache.size());

            for (int i = 0; i < softCache.size(); i++) {
                OOMClass obj = softCache.get(i);
                p(obj == null ? "null" : obj.name);
            }
            p(softCache.size());
        }

        static class OOMClass {
            private final String name;
            private final int[] oom = new int[1024 * 100];

            public OOMClass(String name) {
                this.name = name;
            }
        }

        static class SoftCache<T> {
            // 引用队列
            private final ReferenceQueue<T> referenceQueue = new ReferenceQueue<>();
            // 保存软引用集合，在引用对象被回收后销毁
            private final List<Reference<T>> list = new ArrayList<>();

            // 添加缓存对象
            public synchronized void add(T obj) {
                // 构建软引用
                Reference<T> reference = new SoftReference<>(obj, referenceQueue);
                // 加入列表中
                list.add(reference);
            }

            // 获取缓存对象
            public synchronized T get(int index) {
                // 先对无效引用进行清理
                clear();
                if (index < 0 || list.size() < index) {
                    return null;
                }
                Reference<T> reference = list.get(index);
                return reference == null ? null : reference.get();
            }

            public int size() {
                return list.size();
            }

            @SuppressWarnings("unchecked")
            private void clear() {
                Reference<T> reference;
                while (null != (reference = (Reference<T>) referenceQueue.poll())) {
                    list.remove(reference);
                }
            }
        }

    }

    /**
     * 弱引用
     * 你不可不知的 Java 引用类型之——弱引用：https://www.cnblogs.com/mfrank/p/9829993.html
     */
    @Test
    public void testWeakReference() {
        String str = new String("hello");
        WeakReference<String> weakReference = new WeakReference<>(str);
        str = null;
        // 重新关联上强引用
        str = weakReference.get();
    }

    /**
     * 虚引用
     * 你不可不知的 Java 引用类型之——虚引用：https://www.cnblogs.com/mfrank/p/9837070.html
     */
    static class PhantomReferenceDemo {

        private static final List<Object> TEST_DATA = new LinkedList<>();
        private static final ReferenceQueue<Person> QUEUE = new ReferenceQueue<>();

        /**
         * VM options: -verbose:gc -Xms4m -Xmx4m -Xmn2m
         */
        public static void main(String[] args) {
            Person person = new Person(1, "张三");
            PhantomReference<Person> personPhantomReference = new PhantomReference<>(person, QUEUE);

            // 不断往 List 插入数据促使系统进行 GC，同时不断读取虚引用
            new Thread(() -> {
                while (true) {
                    TEST_DATA.add(new byte[1024 * 100]);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                    p(personPhantomReference.get());
                }
            }).start();

            // 不断读取虚引用，当虚引用只想的对象比回收时，该引用就会被加入引用队列中
            new Thread(() -> {
                while (true) {
                    Reference<? extends Person> reference = QUEUE.poll();
                    if (null != reference) {
                        p("虚引用被 JVM 回收了..." + reference);
                        p("回收对象：" + reference.get());
                    }
                }
            }).start();

            person = null;
            try {
                Thread.currentThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
