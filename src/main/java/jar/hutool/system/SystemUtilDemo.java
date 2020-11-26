package jar.hutool.system;

import cn.hutool.system.SystemUtil;
import org.junit.Test;

import static l.demo.Demo.p;

/**
 * SystemUtil   系统属性
 * <p>
 * static String	    get(String name[, boolean quiet])       取得系统属性，如果因为 Java 安全的限制而失败，则将错误打在 Log 中，然后返回 null
 * static String	    get(String name[, String defaultValue]) 取得系统属性，如果因为 Java 安全的限制而失败，则将错误打在 Log 中，然后返回 defaultValue
 * static Properties    props()                                 确定当前的系统属性
 * <p>
 * https://hutool.cn/docs/#/system/%E7%B3%BB%E7%BB%9F%E5%B1%9E%E6%80%A7%E8%B0%83%E7%94%A8-SystemUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/system/SystemUtil.html
 *
 * @author ljh
 * created on 2020/11/4 17:23
 */
public class SystemUtilDemo {

    @Test
    public void testSystemUtil() {
        // 获取当前进程 PID
        p(SystemUtil.getCurrentPID());          // 12088
        // 总线程数
        p(SystemUtil.getTotalThreadCount());    // 6

        // JVM 内存总大小
        p(SystemUtil.getTotalMemory());         // 128974848
        // JVM 内存剩余大小
        p(SystemUtil.getFreeMemory());          // 110491504
        // JVM 内存可用大小
        p(SystemUtil.getMaxMemory());           // 1897922560
    }

    @Test
    public void testInfo() {
        // 打印以下所有 Info
        // SystemUtil.dumpSystemInfo();

        // Java Virtual Machine Specification
        p(SystemUtil.getJvmSpecInfo());
        // JavaVM Spec. Name:    Java Virtual Machine Specification
        // JavaVM Spec. Version: 1.8
        // JavaVM Spec. Vendor:  Oracle Corporation

        // Java Virtual Machine Implementation
        p(SystemUtil.getJvmInfo());
        // JavaVM Name:    Java HotSpot(TM) 64-Bit Server VM
        // JavaVM Version: 25.221-b11
        // JavaVM Vendor:  Oracle Corporation
        // JavaVM Info:    mixed mode

        // Java Specification
        p(SystemUtil.getJavaSpecInfo());
        // Java Spec. Name:    Java Platform API Specification
        // Java Spec. Version: 1.8
        // Java Spec. Vendor:  Oracle Corporation

        // Java Implementation
        p(SystemUtil.getJavaInfo());
        // Java Version:    1.8.0_221
        // Java Vendor:     Oracle Corporation
        // Java Vendor URL: http://java.oracle.com/

        // Java Runtime Environment
        // p(SystemUtil.getJavaRuntimeInfo());
        // Java Runtime Name:      Java(TM) SE Runtime Environment
        // Java Runtime Version:   1.8.0_221-b11
        // Java Home Dir:          D:\Java\jdk1.8.0_221\jre
        // Java Extension Dirs:    D:\Java\jdk1.8.0_221\jre\lib\ext;C:\WINDOWS\Sun\Java\lib\ext
        // Java Endorsed Dirs:     D:\Java\jdk1.8.0_221\jre\lib\endorsed
        // Java Class Path:         ...

        // Operating System
        p(SystemUtil.getOsInfo());
        // OS Arch:        amd64
        // OS Name:        Windows 10
        // OS Version:     10.0
        // File Separator: \
        // Line Separator: 
        // Path Separator: ;

        // User
        p(SystemUtil.getUserInfo());
        // User Name:        NL-PC001
        // User Home Dir:    C:\Users\NL-PC001
        // User Current Dir: D:\L\git\epitome
        // User Temp Dir:    C:\Users\NL-PC001\AppData\Local\Temp\
        // User Language:    zh
        // User Country:     CN

        // Host
        p(SystemUtil.getHostInfo());
        // Host Name:    JS3-LJH
        // Host Address: 192.168.8.223

        // Runtime
        p(SystemUtil.getRuntimeInfo());
        // ax Memory:    1.77 GB
        // Total Memory:     123 MB
        // Free Memory:     94.92 MB
        // Usable Memory:     1.74 GB
    }

    @Test
    public void testMXBeans() {
        // Java 虚拟机 类加载 系统相关属性
        SystemUtil.getClassLoadingMXBean();
        // Java 虚拟机 内存 系统相关属性
        SystemUtil.getMemoryMXBean();
        // Java 虚拟机 线程 系统相关属性
        SystemUtil.getThreadMXBean();
        // Java 虚拟机 运行时 系统相关属性
        SystemUtil.getRuntimeMXBean();
        // Java 虚拟机 编译 系统相关属性
        SystemUtil.getCompilationMXBean();
        // Java 虚拟机 操作 系统相关信息属性
        SystemUtil.getOperatingSystemMXBean();
        // Java 虚拟机 MemoryPoolMXBean 列表
        SystemUtil.getMemoryPoolMXBeans();
        // Java 虚拟机 MemoryManagerMXBean 列表
        SystemUtil.getMemoryManagerMXBeans();
        // Java 虚拟机 GarbageCollectorMXBean 列表
        SystemUtil.getGarbageCollectorMXBeans();
    }


}
