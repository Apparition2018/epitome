package knowledge.api.lang.system;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * System
 */
public class SystemDemo {

    /**
     * static void	    arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
     * 从指定源数组中复制一个数组，复制从指定的位置开始，到目标数组的指定位置结束
     */
    public static void arraycopy() {
        int[] arr1 = new int[]{1, 2, 3};
        int[] arr2 = new int[arr1.length + 1];

        System.arraycopy(arr1, 0, arr2, 0, 3);
        System.out.println(Arrays.toString(arr2)); // [1, 2, 3, 0]
    }

    /**
     * static long	    currentTimeMillis()
     * 返回以毫秒为单位的当前时间
     */
    @Test
    public void currentTimeMills() throws InterruptedException {
        long t1 = System.currentTimeMillis();
        Thread.sleep(DateUtils.MILLIS_PER_SECOND);
        long t2 = System.currentTimeMillis();
        long t3 = t2 - t1;
        System.out.println(t3);         // 1000

        long t4 = System.nanoTime();
        Thread.sleep(DateUtils.MILLIS_PER_SECOND);
        long t5 = System.nanoTime();
        long t6 = t5 - t4;
        System.out.println(t6);         // 999606313

        System.out.println(t6 / t3);    // ≈ 1000000
    }

    /**
     * static String	getProperty(String key[, String def])
     * 获取用指定键描述的系统属性
     */
    @Test
    public void getProperty() {
        System.out.println(System.getProperty("java.version"));         // 1.8.0_131
        System.out.println(System.getProperty("java.vendor"));          // Oracle Corporation
        System.out.println(System.getProperty("java.vendor.url"));      // http://java.oracle.com/
        System.out.println(System.getProperty("java.home"));            // C:\Program Files\Java\jdk1.8.0_131\jre
        System.out.println(System.getProperty("java.class.version"));   // 52.0
        System.out.println(System.getProperty("java.class.path"));      // ...
        System.out.println(System.getProperty("os.name"));              // Windows 7
        System.out.println(System.getProperty("os.arch"));              // amd64
        System.out.println(System.getProperty("os.version"));           // 6.1
        System.out.println(System.getProperty("file.separator"));       // "/" on Unix, "\\" on windows
        System.out.println(System.getProperty("path.separator"));       // ":" on Unix, ";" on windows
        System.out.println(System.getProperty("line.separator"));       // "\n" on Unix, "\r\n" on windows
        System.out.println(System.getProperty("user.name"));            // 234607
        System.out.println(System.getProperty("user.home"));            // C:\Users\234607
        System.out.println(System.getProperty("user.dir"));             // C:\Users\234607\git\mavenTest
    }

    /**
     * static long	    nanoTime()
     * 返回最准确的可用系统计时器的当前值，以毫微秒为单位
     */
    @Test
    public void nanoTime() throws InterruptedException {
        currentTimeMills();
    }

}
