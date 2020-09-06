package knowledge.线程;

/**
 * https://blog.csdn.net/never_cxb/article/details/50379047
 * https://blog.csdn.net/ankeyuan/article/details/39671979
 * 设计4个线程，其中两个每次对j增加1，另外两个对j每次减少1。循环100次。
 */
public class ThreadExercise {

    private int j;

    class Inc implements Runnable {

        @Override
        public void run() {
            int i = 0;
            while (i++ < 100) {
                inc();
            }
        }
    }

    class Dec extends Thread {

        @Override
        public void run() {
            int i = 0;
            while (i++ < 100) {
                dec();
            }
        }
    }

    private synchronized void inc() {
        j++;
        System.out.println(Thread.currentThread().getName() + "--inc--" + j);
    }

    private synchronized void dec() {
        j--;
        System.out.println(Thread.currentThread().getName() + "--dec--" + j);
    }

    public static void main(String[] args) {
        ThreadExercise rd = new ThreadExercise();

        Thread thread;

        for (int i = 0; i < 2; i++) {
            thread = new Thread(rd.new Inc());
            thread.start();
        }

        for (int i = 0; i < 2; i++) {
            thread = rd.new Dec();
            thread.start();
        }
    }

}
