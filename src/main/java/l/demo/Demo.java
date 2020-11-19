package l.demo;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demo
 *
 * @author ljh
 * created on 2020/9/3 10:15
 */
public class Demo {

    public static Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    public static List<String> sList = new ArrayList<>(Arrays.asList("1 2 3 4 5 6 7 8 9".split(" ")));
    public static List<Integer> descList = Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1);
    public static List<Integer> repeatList = new ArrayList<>(Arrays.asList(1, 1, 2, 2, 3, 3, 4, 5));
    public static List<Integer> subList = new ArrayList<>(list.subList(1, 6));
    public static List<Integer> subList2 = list.subList(3, 8);
    public static List<Person> personList = new ArrayList<Person>(3) {
        {
            add(new Person(1, "张三"));
            add(new Person(2, "李四"));
            add(new Person(3, "王五"));
        }
    };

    public static Map<Integer, String> map = new HashMap<Integer, String>(3) {
        {
            put(1, "A");
            put(2, "B");
            put(3, "C");
        }
    };
    public static Map<Integer, String> map2 = new HashMap<Integer, String>(5) {
        {
            put(1, "A");
            put(2, "B");
            put(3, "C");
            put(4, "D");
            put(5, "E");
            put(6, "F");
            put(7, "G");
            put(8, "H");
            put(9, "I");
        }
    };
    public static CountDownLatch countDownLatch;
    public static final String HELLO_WORLD = "Hello World!";
    public static final String MY_NAME = "梁杰辉";
    public static final String MY_EMAIL = "88850180@163.com";
    public static final String MY_PHONE = "13425589294";
    public static final String MY_ID_CARD = "442000199010160716";
    public static final String UTF_8 = StandardCharsets.UTF_8.name();
    public static final String BAIDU_HOST = "www.baidu.com";
    public static final String BAIDU_URL = "https://" + BAIDU_HOST;
    public static final String DEMO_URL = "https://developer.mozilla.org/en-US/search?q=URL#search-results-close-container";
    public static final String USER_DIR = System.getProperty("user.dir");
    public static final String JAVA_PATH = "src/main/java/";
    public static final String RESOURCES_PATH = "src/main/resources/";
    public static final String RESOURCES_ABSOLUTE_PATH = USER_DIR + File.separator + RESOURCES_PATH;
    public static final String DEMO_PATH = RESOURCES_PATH + "demo/";
    public static final String DEMO_ABSOLUTE_PATH = USER_DIR + File.separator + DEMO_PATH;
    public static final String DEMO_FILE_PATH = DEMO_PATH + "demo";
    public static final String DEMO_FILE_ABSOLUTE_PATH = DEMO_ABSOLUTE_PATH + "demo";
    public static final String DEMO_URI = "http://localhost:3333/demo/";
    public static final String ARSENAL_LOGO = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1113375911,3381445023&fm=26&gp=0.jpg";
    public static final String NOHARA_SINNOSUKE = RESOURCES_ABSOLUTE_PATH + "static/static/img/people/NoharaSinnosuke.png";
    public static final String JDBC_PROP_FILENAME = "jdbc.properties";
    public static final SimpleDateFormat DATE_SDF = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_TIME_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat SDF = DATE_SDF;

    public static void setCountDownLatch(int n) {
        countDownLatch = new CountDownLatch(n);
    }

    /**
     * 简写 System.out.println()
     */
    public static void p() {
        System.out.println();
    }

    public static <T> void p(T obj) {
        p(obj, false);
    }

    public static <T> void p(T obj, boolean original) {
        if (null == obj) return;
        // 数组
        if (obj.getClass().isArray()) {
            if (obj instanceof long[]) {
                System.out.println(Arrays.toString((long[]) obj));
                return;
            }
            if (obj instanceof int[]) {
                System.out.println(Arrays.toString((int[]) obj));
                return;
            }
            if (obj instanceof short[]) {
                System.out.println(Arrays.toString((short[]) obj));
                return;
            }
            if (obj instanceof char[]) {
                System.out.println(Arrays.toString((char[]) obj));
                return;
            }
            if (obj instanceof byte[]) {
                System.out.println(Arrays.toString((byte[]) obj));
                return;
            }
            if (obj instanceof boolean[]) {
                System.out.println(Arrays.toString((boolean[]) obj));
                return;
            }
            if (obj instanceof float[]) {
                System.out.println(Arrays.toString((float[]) obj));
                return;
            }
            if (obj instanceof double[]) {
                System.out.println(Arrays.toString((double[]) obj));
                return;
            }
            System.out.println(Arrays.toString((Object[]) obj));
            return;
        }
        // 日期时间
        if (obj instanceof Date) {
            if (original) {
                System.out.println(obj);
                return;
            }
            System.out.println(DATE_TIME_SDF.format(obj));
            return;
        }
        System.out.println(obj);
    }

    /**
     * 模拟 Thread.sleep()，为了避免 Thread.sleep() 而需要捕获 InterruptedException 而带来的理解上的困惑
     */
    public static void sleep(long millis, TimeUnit timeUnit) {
        long now = System.currentTimeMillis();
        while (System.currentTimeMillis() - now < timeUnit.toMillis(millis)) {
            // Thread.onSpinWait(); 需要升级到 JDK8
        }
    }

    /**
     * 随机返回 true 或 false
     */
    public static boolean randomBoolean() {
        return new Random().nextBoolean();
    }

    /**
     * 随机返回 0, 1, 2...
     * 最大为 Integer.MAX_VALUE
     */
    public static int randomInt(int end) {
        if (end < 0) throw new RuntimeException("end < 0");
        return randomInt(0, end);
    }

    /**
     * 随机返回范围内整数
     * 最大为 Integer.MAX_VALUE
     */
    public static int randomInt(int start, int end) {
        if (start > end) throw new RuntimeException("start > end");
        if (start < 0) throw new RuntimeException("start < 0");
        if (randomBoolean()) {
            // 方法1
            return new Random().ints(start, ++end).limit(1).sum();
        } else {
            // 方法2
            return new Random().nextInt(++end - start) + start;
        }
    }

    public static class MyTask implements Runnable {
        int taskId;

        public MyTask(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            try {
                Thread t = Thread.currentThread();
                p(t.getName() + "：正在运行任务 " + taskId + " ...");
                TimeUnit.MILLISECONDS.sleep(300);
                if (null != countDownLatch) {
                    countDownLatch.countDown();
                }
                p(t.getName() + "：运行任务 " + taskId + " 完毕！");
            } catch (InterruptedException e) {
                p("线程被中断了！");
            }
        }
    }

    public static class MyCallable implements Callable<Map<Integer, String>> {
        int taskId;

        public MyCallable(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public Map<Integer, String> call() throws Exception {
            Thread t = Thread.currentThread();
            p(t.getName() + "：正在运行任务 " + taskId + " ...");
            TimeUnit.MILLISECONDS.sleep(300);
            Map<Integer, String> map = new HashMap<>();
            map.put(taskId, Thread.currentThread().getName());
            if (null != countDownLatch) {
                countDownLatch.countDown();
            }
            return map;
        }
    }

    /**
     * 给线程池的线程命名
     */
    public static class MyThreadFactory implements ThreadFactory {

        private final AtomicInteger count = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(map2.get(count.getAndIncrement()));
            return thread;
        }
    }
}
