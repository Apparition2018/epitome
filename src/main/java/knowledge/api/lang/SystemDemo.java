package knowledge.api.lang;

import l.demo.Demo;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * System
 * https://jdk6.net/lang/System.html
 * <p>
 * static void	                exit(int status)                终止当前正在运行的 Java 虚拟机
 * static void	                gc()                            运行垃圾回收器
 * static void	                load(String filename)           从作为动态库的本地文件系统中以指定的文件名加载代码文件
 * static void	                loadLibrary(String libname)     加载由 libname 参数指定的系统库
 * static void	                setProperties(Properties props) 将系统属性设置为 Properties 参数
 * static String	            setProperty(String key, String value)   设置指定键指示的系统属性
 * static String	            clearProperty(String key)       移除指定键指示的系统属性
 * static Map<String,String>	getenv()                        返回一个不能修改的当前系统环境的字符串映射视图
 * static Console	            console()                       返回与当前 Java 虚拟机关联的唯一 Console 对象（如果有）
 * static int	                identityHashCode(Object x)      返回给定对象的哈希码，该代码与默认的方法 hashCode() 返回的代码一样，无论给定对象的类是否重写 hashCode()
 */
public class SystemDemo extends Demo {

    @Test
    public void test() {
        p(System.getenv());
    }

    /**
     * static void	    arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
     * 从指定源数组中复制一个数组，复制从指定的位置开始，到目标数组的指定位置结束
     * <p>
     * PS：native 接口，建议在复制大量数组元素时用该方法，以取得更高的效率
     */
    @Test
    public void arraycopy() {
        int[] arr1 = new int[]{1, 2, 3};
        int[] arr2 = new int[arr1.length + 1];

        System.arraycopy(arr1, 0, arr2, 0, 3);
        p(arr2); // [1, 2, 3, 0]
    }

    /**
     * static long	    currentTimeMillis()                     回以毫秒为单位的当前时间
     */
    @Test
    public void currentTimeMills() throws InterruptedException {
        long t1 = System.currentTimeMillis();
        TimeUnit.SECONDS.sleep(1);
        long t2 = System.currentTimeMillis();
        p(t2 - t1); // 1000
    }

    /**
     * static long	    nanoTime()                              返回最准确的可用系统计时器的当前值，以毫微秒为单位
     */
    @Test
    public void nanoTime() throws InterruptedException {
        long t1 = System.nanoTime();
        TimeUnit.SECONDS.sleep(1);
        long t2 = System.nanoTime();
        p(t2 - t1); // 999885400
    }

    /**
     * static String	getProperty([String key[, String def]]) 获取用指定键描述的系统属性
     */
    @Test
    public void getProperty() {
        p(System.getProperty("java.version"));         // 1.8.0_131
        p(System.getProperty("java.vendor"));          // Oracle Corporation
        p(System.getProperty("java.vendor.url"));      // http://java.oracle.com/
        p(System.getProperty("java.home"));            // C:\Program Files\Java\jdk1.8.0_131\jre
        p(System.getProperty("java.class.version"));   // 52.0
        p(System.getProperty("java.class.path"));      // ...
        p(System.getProperty("os.name"));              // Windows 7
        p(System.getProperty("os.arch"));              // amd64
        p(System.getProperty("os.version"));           // 6.1
        p(System.getProperty("file.separator"));       // "/" on Unix, "\\" on windows
        p(System.getProperty("path.separator"));       // ":" on Unix, ";" on windows
        p(System.getProperty("line.separator"));       // "\n" on Unix, "\r\n" on windows
        p(System.getProperty("user.name"));            // 234607
        p(System.getProperty("user.home"));            // C:\Users\234607
        p(System.getProperty("user.dir"));             // C:\Users\234607\git\epitome
    }

}
