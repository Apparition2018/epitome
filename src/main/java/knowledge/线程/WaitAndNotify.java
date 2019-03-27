package knowledge.线程;

/**
 * wait(), notify()
 * 对象锁，锁池，等待池
 */
public class WaitAndNotify {
    public static void main(String[] args) {
        Target t = new Target();

        Thread t1 = new Increase(t);
        t1.setName("Increase");
        Thread t2 = new Decrease(t);
        t2.setName("Decrease");

        t1.start();
        t2.start();
    }
}

class Target {
    private int count;

    public synchronized void increase() {
        if (count == 2) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;
        System.out.println(Thread.currentThread().getName() + ":" + count);
        notify();
    }

    public synchronized void decrease() {
        if (count == 0) {
            try {
                // 等待，由于 Decrease 线程调用的该方法,
                // 所以 Decrease 线程进入对象 t (main函数中实例化的)的等待池，并且释放对象 t 的锁
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        System.out.println(Thread.currentThread().getName() + ":" + count);

        // 唤醒线程 Increase, Increase 线程从等待池到锁池
        notify();
    }
}

class Increase extends Thread {

    private Target t;

    Increase(Target t) {
        this.t = t;
    }

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            try {
                // 随机睡眠0~500毫秒
                // sleep() 不会释放对象 t 的锁
                Thread.sleep((long) (Math.random() * 500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            t.increase();
        }
    }

}

class Decrease extends Thread {

    private Target t;

    Decrease(Target t) {
        this.t = t;
    }

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            try {
                Thread.sleep((long) (Math.random() * 500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            t.decrease();
        }
    }

}
