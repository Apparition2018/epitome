package knowledge.线程.synchronized_;

/**
 * Synchronized     内置锁
 */
public class SynchronizedDemo {


    public static void main(String[] args) {

        Table table = new Table();

        new Thread() {
            public void run() {
                while (true) {
                    Thread.yield();
                    System.out.println(getName() + ":" + table.getBean2());
                }
            }
        }.start();

        new Thread() {
            public void run() {
                while (true) {
                    Thread.yield();
                    System.out.println(getName() + ":" + table.getBean2());
                }
            }
        }.start();

    }

}

class Table {

    private int beans = 20;

    /**
     * 同步方法
     * <p>
     * 同步监视器对象是当前方法所属对象，即 this
     */
    public synchronized int getBean() {

        if (beans == 0) {
            throw new RuntimeException("没有豆子了！");
        }
        return --beans;
    }

    /**
     * 同步块
     * <p>
     * 有效的缩小同步范围可以在保证安全的前提下提高代码并发执行的效率
     * <p>
     * synchronized(同步监视器) { doSomething }
     * <p>
     * 同步监视器即上锁的对象，可以是 java 中的任意对象，
     * 但是必须保证需要同步执行代码的多个线程看到的该对象必须是"同一个"才可以。
     * 通常可以使用 this
     */
    public int getBean2() {

        if (beans == 0) {
            throw new RuntimeException("没有豆子了！");
        }

        synchronized (this) {
            return --beans;
        }
    }

}