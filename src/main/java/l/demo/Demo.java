package l.demo;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Demo
 *
 * @author ljh
 * created on 2020/9/3 10:15
 */
public class Demo {

    public List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    public List<String> sList = new ArrayList<>(Arrays.asList("1 2 3 4 5 6 7 8 9".split(" ")));
    public List<Integer> descList = Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1);
    public List<Integer> subList = new ArrayList<>(list.subList(1, 6));
    public List<Integer> subList2 = list.subList(3, 8);
    public static Map<Integer, String> map = new HashMap<>();
    public final static String DEMO_PATH = "src/main/resources/demo/";

    static {
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
    }

    public static <T> void p() {
        System.out.println();
    }

    public static <T> void p(T obj) {
        p(obj, false);
    }

    /**
     * 简写 System.out.println()
     */
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
            System.out.println(Arrays.toString((Object[]) obj));
            return;
        }
        // 日期时间
        if (obj instanceof Date) {
            if (original) {
                System.out.println(obj);
                return;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            System.out.println(sdf.format(obj));
            return;
        }
        System.out.println(obj);
    }
}
