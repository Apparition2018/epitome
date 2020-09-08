package knowledge.线程.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 测试 newSingleThreadExecutor
 * 该方法返回一个只有一个线程的线程池，即每次只能执行一个线程任务，多余的任务会保存到一个任务队列中，
 * 等待这一个线程空闲，当这个线程空闲了再按FIFO方式顺序执行任务队列中的任务
 */
public class ExecutorServiceDemo2 {
    public static void main(String[] args) {
        //创建一个可重用固定线程数的线程池
        ExecutorService pool = Executors.newSingleThreadExecutor();

        //创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        Thread t3 = new MyThread();
        Thread t4 = new MyThread();
        Thread t5 = new MyThread();

        //将线程放入池中进行执行
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);

        //关闭线程池
        pool.shutdown();
    }
}

class MyThread extends Thread {
    private static int[] arr = {1, 2, 3, 4, 5};
    private static int index = 0;

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + "正在执行。。。" + arr[index++]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}