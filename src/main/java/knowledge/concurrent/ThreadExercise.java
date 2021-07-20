package knowledge.concurrent;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * 设计4个线程，其中两个每次对j增加1，另外两个对j每次减少1。循环5次。
 * https://blog.csdn.net/never_cxb/article/details/50379047
 *
 * @author ljh
 * created on 2020/11/17 19:09
 */
public class ThreadExercise {

    private static int j;

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Inc().start();
        }

        for (int i = 0; i < 2; i++) {
            new Dec().start();
        }
    }

    static class Inc extends Thread {

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

    static class Dec extends Thread {

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
