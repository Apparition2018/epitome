package knowledge.api.lang;

import org.junit.jupiter.api.Test;

import static l.demo.Demo.p;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/lang/System.html">System</a>
 * <pre>
 * static void                  exit(int status)                        终止当前正在运行的 Java 虚拟机
 * static void                  gc()                                    运行垃圾回收器
 * static void                  load(String filename)                   从作为动态库的本地文件系统中以指定的文件名加载代码文件
 * static void                  loadLibrary(String libname)             加载由 libname 参数指定的系统库
 * static void                  setProperties(Properties props)         将系统属性设置为 Properties 参数
 * static void                  exit(int status)                        终止当前正在运行的 Java 虚拟机
 * static String                setProperty(String key, String value)   设置指定键指示的系统属性
 * static String                clearProperty(String key)               移除指定键指示的系统属性
 * static Map<String,String>    getenv()                                返回一个不能修改的当前系统环境的字符串映射视图
 * static Console               console()                               返回与当前 Java 虚拟机关联的唯一 Console 对象（如果有）
 * static int                   identityHashCode(Object x)              返回给定对象的哈希码，该代码与默认的方法 hashCode() 返回的代码一样，无论给定对象的类是否重写 hashCode()
 * static long                  currentTimeMillis()                     返回以毫秒为单位的当前时间
 * static long                  nanoTime()                              返回最准确的可用系统计时器的当前值，以毫微秒为单位
 * </pre>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class SystemDemo {

    /**
     * static void	        arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
     * <pre>
     * 从指定源数组中复制一个数组，复制从指定的位置开始，到目标数组的指定位置结束
     * 相对于数组来说是深克隆
     * 相对于数组元素来说：只有数组为一维数组，并且元素为基本类型、包装类或String类型时，才是深克隆，其它都属于浅克隆
     * native 接口，建议在复制大量数组元素时用该方法，以取得更高的效率
     * </pre>
     */
    @Test
    public void arraycopy() {
        int[] arr1 = new int[]{1, 2, 3};
        int[] arr2 = new int[arr1.length + 1];
        System.arraycopy(arr1, 0, arr2, 0, 3);
        p(arr2); // [1, 2, 3, 0]
    }

    /**
     * <p>static String	    getProperty([String key[, String def]]) 获取用指定键描述的系统属性
     * <p>static Properties	getProperties()                         确定当前的系统属性
     */
    @Test
    public void getProperty() {
        p(System.getProperty("user.name"));             // NL-PC001
        p(System.getProperty("user.home"));             // C:\Users\NL-PC001
        p(System.getProperty("user.dir"));              // D:\L\git\epitome
        p(System.getProperty("os.arch"));               // amd64
        p(System.getProperty("os.name"));               // Windows 10
        p(System.getProperty("os.version"));            // 10.0
        p(System.getProperty("java.vendor"));           // Oracle Corporation
        p(System.getProperty("java.vendor.url"));       // http://java.oracle.com/
        p(System.getProperty("java.version"));          // 1.8.0_221
        p(System.getProperty("java.home"));             // D:\Java\jdk1.8.0_221\jre
        p(System.getProperty("java.class.version"));    // 52.0
        p(System.getProperty("java.class.path"));       // ...
        p(System.getProperty("file.separator"));        // "/" on Unix, "\\" on windows
        p(System.getProperty("path.separator"));        // ":" on Unix, ";" on windows
        p(System.getProperty("line.separator"));        // "\n" on Unix, "\r\n" on windows
    }
}
