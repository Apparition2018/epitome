package l.demo;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.lang.NonNull;
import org.springframework.util.StopWatch;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.PrintStream;
import java.io.Serial;
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
 * @since 2020/9/3 10:15
 */
public class Demo {

    public static Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
    public static List<String> sList = new ArrayList<>(List.of("1 2 3 4 5 6 7 8 9".split(" ")));
    public static List<Integer> descList = List.of(9, 8, 7, 6, 5, 4, 3, 2, 1);
    public static List<Integer> repeatList = new ArrayList<>(List.of(1, 1, 2, 2, 3, 3, 4, 5));
    public static List<Integer> subList = new ArrayList<>(list.subList(1, 6));
    public static List<Integer> subList2 = list.subList(3, 8);
    public static List<Person> personList = new ArrayList<>() {
        @Serial
        private static final long serialVersionUID = 17362850008309337L;

        {
            add(new Person(1, "张三"));
            add(new Person(2, "李四"));
            add(new Person(3, "王五"));
        }
    };
    public static Map<Integer, String> map = new HashMap<>() {
        @Serial
        private static final long serialVersionUID = -6695635216046532571L;

        {
            put(1, "A");
            put(2, "B");
            put(3, "C");
        }
    };
    public static Map<Integer, String> map2 = new HashMap<>() {
        @Serial
        private static final long serialVersionUID = -2963536074355318510L;

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
    public static StopWatch stopWatch = new StopWatch("demo");
    public static JsonMapper jsonMapper = JsonMapper.builder().build();
    public static CountDownLatch countDownLatch;
    public static final int THOUSAND = 1000;
    public static final int MILLION = THOUSAND * 1000;
    public static final int TEN_MILLION = MILLION * 10;
    public static final int HUNDRED_MILLION = MILLION * 100;
    public static final int PORT = 3333;
    public static final String HELLO_WORLD = "Hello World!";
    public static final String MY_NAME = "ljh";
    public static final String MY_EMAIL = "88850180@163.com";
    public static final String MY_CY = "中国";
    public static final String MOBILE = "13800589230";
    public static final String ID_CARD = "110101199003077950";
    public static final String BAIDU_HOST = "www.baidu.com";
    public static final String BAIDU_URL = "https://" + BAIDU_HOST;
    public static final String MOZILLA_DEMO_URL = "https://developer.mozilla.org/en-US/search?q=URL#search-results-close-container";
    public static final String BASE_URL = "http://localhost:3333/";
    public static final String DEMO_URL = "http://localhost:3333/demo/";
    public static final String RANDOM_URL = " https://quoters.apps.pcfone.io/api/random";
    public static final String USER_DIR = System.getProperty("user.dir");
    public static final String DESKTOP = FileSystemView.getFileSystemView().getHomeDirectory().getPath() + File.separator;
    public static final String JAVA_PATH = "src/main/java/";
    public static final String RESOURCES_PATH = "src/main/resources/";
    public static final String RESOURCES_ABSOLUTE_PATH = USER_DIR + File.separator + RESOURCES_PATH;
    public static final String DEMO_PATH = RESOURCES_PATH + "demo/";
    public static final String HU_DEMO_PATH = DEMO_PATH + "hutool/";
    public static final String DEMO_ABSOLUTE_PATH = USER_DIR + File.separator + DEMO_PATH;
    public static final String HU_DEMO_ABSOLUTE_PATH = USER_DIR + File.separator + HU_DEMO_PATH;
    public static final String DEMO_FILE_PATH = DEMO_PATH + "demo";
    public static final String DEMO_FILE_ABSOLUTE_PATH = DEMO_ABSOLUTE_PATH + "demo";
    public static final String BIRD_IMG = "https://i.postimg.cc/1zRqNgPw/bird.jpg";
    public static final String XIAO_XIN_IMG = RESOURCES_ABSOLUTE_PATH + "static/public/img/people/NoharaSinnosuke.png";
    public static final String VIDEO = RESOURCES_ABSOLUTE_PATH + "static/public/video/movie.ogg";
    public static final String JDBC_PROPS_FILENAME = "jdbc.properties";

    public static final String APP_PROPS_FILENAME = "application.properties";
    public static final ThreadLocal<SimpleDateFormat> DATE_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat(DatePattern.NORM_DATE_PATTERN));
    public static final ThreadLocal<SimpleDateFormat> DATE_TIME_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN));

    public static void setCountDownLatch(int n) {
        countDownLatch = new CountDownLatch(n);
    }

    public static void p() {
        System.out.println();
    }

    public static <T> void p(T obj) {
        p(System.out, obj);
    }

    public static <T> void pe(T obj) {
        p(System.err, obj);
    }

    public static <T> void p(PrintStream printStream, T obj) {
        if (null == obj) return;
        // 数组
        if (obj.getClass().isArray()) {
            if (obj instanceof long[]) {
                printStream.println(Arrays.toString((long[]) obj));
                return;
            }
            if (obj instanceof int[]) {
                printStream.println(Arrays.toString((int[]) obj));
                return;
            }
            if (obj instanceof short[]) {
                printStream.println(Arrays.toString((short[]) obj));
                return;
            }
            if (obj instanceof char[]) {
                printStream.println(Arrays.toString((char[]) obj));
                return;
            }
            if (obj instanceof byte[]) {
                printStream.println(Arrays.toString((byte[]) obj));
                return;
            }
            if (obj instanceof boolean[]) {
                printStream.println(Arrays.toString((boolean[]) obj));
                return;
            }
            if (obj instanceof float[]) {
                printStream.println(Arrays.toString((float[]) obj));
                return;
            }
            if (obj instanceof double[]) {
                printStream.println(Arrays.toString((double[]) obj));
                return;
            }
            printStream.println(Arrays.toString((Object[]) obj));
            return;
        }
        printStream.println(obj);
    }

    /**
     * 模拟 Thread.sleep()，为了避免 Thread.sleep() 而需要捕获 InterruptedException 而带来的理解上的困惑
     */
    public static void sleep(long timeout, TimeUnit timeUnit) {
        long now = System.currentTimeMillis();
        while (System.currentTimeMillis() - now < timeUnit.toMillis(timeout)) {
            Thread.onSpinWait();
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
            // 方法一
            return new Random().ints(start, ++end).limit(1).sum();
        } else {
            // 方法二
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
                p(String.format("%s：任务 %s 正在运行 ...", t.getName(), taskId));
                TimeUnit.MILLISECONDS.sleep(300);
                if (null != countDownLatch) {
                    countDownLatch.countDown();
                }
                p(String.format("%s：任务 %s 运行完毕！", t.getName(), taskId));
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
            p(String.format("%s：任务 %s 正在运行 ...", t.getName(), taskId));
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
        public Thread newThread(@NonNull Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("MyPool-" + map2.get(count.getAndIncrement()));
            return thread;
        }
    }
}
