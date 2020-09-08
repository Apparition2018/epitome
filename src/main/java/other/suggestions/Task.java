package other.suggestions;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 建议127：Lock 和 synchronized
 */
class Task {
    void doSomething() {
        try {
            // 每个线程等待2秒钟，注意此时线程的状态变为 Warning 状态
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        StringBuffer sb = new StringBuffer();
        // 线程名称
        sb.append("线程名称：").append(Thread.currentThread().getName());
        // 运行时间戳
        sb.append("，执行事件：").append(Calendar.getInstance().get(Calendar.SECOND)).append("s");
        System.out.println(sb);
    }
}

class TaskWithSync extends Task implements Runnable {

    @Override
    public void run() {
        // 第一次运行到这里的时候，在字符串池生成 "A"，之后的每一次都锁定了这个 "A"
        // 所以这些同步块的同步监视器对象是同一个对象，产生了互斥效果
        synchronized ("A") {
            doSomething();
        }
    }
}

class TaskWithLock extends Task implements Runnable {

    private final Lock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            lock.lock();
            doSomething();
        } finally {
            lock.unlock();
        }
    }
}