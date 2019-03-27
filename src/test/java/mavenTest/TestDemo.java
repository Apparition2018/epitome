package mavenTest;

public class TestDemo {

    private int j;

    public static void main(String[] args) {
        TestDemo t = new TestDemo();
        for (int i = 0; i < 2; i++) {
            Thread t1 = new Inc(t);
            t1.start();
            Thread t2 = new Dec(t);
            t2.start();
        }
    }

    public synchronized void inc() {
        j++;
        System.out.println(Thread.currentThread().getName() + ":inc" + j);
    }

    public synchronized void dec() {
        j--;
        System.out.println(Thread.currentThread().getName() + ":dec" + j);
    }

}

class Inc extends Thread {
    private TestDemo a;

    Inc(TestDemo a) {
        this.a = a;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            a.inc();
        }
    }
}

class Dec extends Thread {
    private TestDemo a;

    Dec(TestDemo a) {
        this.a = a;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            a.dec();
        }
    }
}