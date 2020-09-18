package knowledge.线程;

import l.demo.Demo;

/**
 * https://blog.csdn.net/never_cxb/article/details/50379047
 * 设计4个线程，其中两个每次对j增加1，另外两个对j每次减少1。循环100次。
 */
public class ThreadExercise extends Demo {

    private static int j;

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Inc().start();
        }

        for (int i = 0; i < 2; i++) {
            new Dec().start();
        }
    }

    private static class Inc extends Thread {

        @Override
        public void run() {
            int i = 0;
            while (i++ < 100) {
                inc();
            }
        }
    }

    private static class Dec extends Thread {

        @Override
        public void run() {
            int i = 0;
            while (i++ < 100) {
                dec();
            }
        }
    }

    private static synchronized void inc() {
        j++;
        p(Thread.currentThread().getName() + "--inc--" + j);
    }

    private static synchronized void dec() {
        j--;
        p(Thread.currentThread().getName() + "--dec--" + j);
    }

}
