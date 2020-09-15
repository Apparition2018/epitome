package knowledge.线程.synchronized_;

import l.demo.Demo;

import java.util.concurrent.TimeUnit;

/**
 * 互斥锁
 * 使用 synchronized 修饰的多段代码，并且这些同步块的同步监视器对象是同一个的时候，
 * 这些代码间就具有了互斥效果，同一时间多个线程不能同时在这些方法内部执行
 */
public class SynchronizedDemo3 extends Demo {

    public static void main(String[] args) {
        Student s = new Student();
        new Thread(s::learning).start();
        new Thread(s::dining).start();
    }

    static class Student {
        public synchronized void learning() {
            try {
                p("learning start");
                TimeUnit.SECONDS.sleep(1);
                p("learning end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public synchronized void dining() {
            try {
                p("dining start");
                TimeUnit.SECONDS.sleep(1);
                p("dining end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

