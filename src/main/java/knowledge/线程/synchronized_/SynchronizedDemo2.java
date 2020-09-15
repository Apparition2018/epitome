package knowledge.线程.synchronized_;

import l.demo.Demo;

import java.util.concurrent.TimeUnit;

/**
 * 静态方法上使用 synchronized 修饰后，该方法一定具有同步效果，
 * 即使监视器对象不是同一个对象
 */
public class SynchronizedDemo2 extends Demo {

    public static void main(String[] args) {
        Fly f1 = new Fly();
        Fly f2 = new Fly();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                f1.fly();
                Thread.yield();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                f2.fly();
                Thread.yield();
            }
        }).start();
    }

    static class Fly {
        public synchronized static void fly() {
            try {
                p("fly start");
                TimeUnit.SECONDS.sleep(1);
                p("fly end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}