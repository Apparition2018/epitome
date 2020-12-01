package knowledge.api.lang;

import org.junit.Test;

/**
 * Runtime
 * <p>
 * Process	exec(String/String[] command)                               在单独的进程中执行指定的命令
 * Process	exec(String/String[] command, String[] envp[, File dir])    在指定环境和工作目录的独立进程中执行指定的命令
 *
 * @author ljh
 * created on 2020/12/1 13:08
 */
public class RuntimeDemo {

    @Test
    public void testRuntime() {
        // 返回与当前 Java 应用程序相关的运行时对象
        Runtime runtime = Runtime.getRuntime();

        // 向 Java 虚拟机返回可用处理器的数目
        System.out.println(runtime.availableProcessors());
    }
}
