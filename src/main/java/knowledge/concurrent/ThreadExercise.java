package knowledge.concurrent;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * <a href="https://blog.csdn.net/never_cxb/article/details/50379047">设计4个线程，其中两个每次对j增加1，另外两个对j每次减少1。循环5次</a>
 *
 * @author ljh
 * @since 2020/11/17 19:09
 */
public class ThreadExercise {

    private static int j;

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 2).forEach(i -> new Inc().start());
        IntStream.rangeClosed(1, 2).forEach(i -> new Dec().start());
    }

    private static class Inc extends Thread {

        @SneakyThrows
        @Override
        public void run() {
            int i = 0;
            while (i++ < 5) {
                TimeUnit.MILLISECONDS.sleep(100);
                inc();
            }
        }
    }

    private static class Dec extends Thread {

        @SneakyThrows
        @Override
        public void run() {
            int i = 0;
            while (i++ < 5) {
                TimeUnit.MILLISECONDS.sleep(100);
                dec();
            }
        }
    }

    private static synchronized void inc() {
        j++;
        System.out.println(Thread.currentThread().getName() + "--inc--" + j);
    }

    private static synchronized void dec() {
        j--;
        System.out.println(Thread.currentThread().getName() + "--dec--" + j);
    }
}
