package l.demo;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * Demo
 *
 * @author ljh
 * created on 2020/9/3 10:15
 */
public class Demo {

    public String projectName = "epitome";
    public String numString = "123456789";
    public Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    public List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    public List<String> sList = new ArrayList<>(Arrays.asList("1 2 3 4 5 6 7 8 9".split(" ")));
    public List<Integer> descList = Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1);
    public List<Integer> repeatList = new ArrayList<>(Arrays.asList(1, 1, 2, 2, 3, 3, 4, 5));
    public List<Integer> subList = new ArrayList<>(list.subList(1, 6));
    public List<Integer> subList2 = list.subList(3, 8);
    public Map<Integer, String> map = new HashMap<Integer, String>(3) {
        {
            put(1, "A");
            put(2, "B");
            put(3, "C");
        }
    };
    public Map<Integer, String> map2 = new HashMap<Integer, String>(5) {
        {
            put(1, "A");
            put(2, "B");
            put(3, "C");
            put(4, "E");
            put(5, "D");
        }
    };
    public static CountDownLatch countDownLatch;
    public static final SimpleDateFormat DATE_SDF = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_TIME_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public static final SimpleDateFormat SDF = DATE_SDF;
    public static final String UTF_8 = String.valueOf(StandardCharsets.UTF_8);
    public static final String DEMO_URL = "https://developer.mozilla.org/en-US/search?q=URL#search-results-close-container";
    public static final String BAIDU_URL = "https://www.baidu.com/";
    public static final String USER_DIR = System.getProperty("user.dir");
    public static final String BAIDU_HOST = "www.baidu.com";
    public static final String RESOURCES_PATH = "src/main/resources/";
    public static final String DEMO_PATH = RESOURCES_PATH + "demo/";
    public static final String ARSENAL_LOGO = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1113375911,3381445023&fm=26&gp=0.jpg";
    public static final String JDBC_PROP_FILENAME = "jdbc.properties";

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
        if (obj == null) return;
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
}
