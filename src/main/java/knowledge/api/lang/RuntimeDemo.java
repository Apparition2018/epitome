package knowledge.api.lang;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/lang/Runtime.html">Runtime</a>
 * <pre>
 * Process  exec(String/String[] command)                               在单独的进程中执行指定的命令
 * Process  exec(String/String[] command, String[] envp[, File dir])    在指定环境和工作目录的独立进程中执行指定的命令
 * </pre>
 * <p><a href="https://www.cnblogs.com/fclbky/p/6112180.html">使用 Runtime.getRuntime().exec() 需要注意的地方</a>
 *
 * @author ljh
 * @since 2020/12/1 13:08
 */
public class RuntimeDemo {

    public static void main(String[] args) {
        // 返回与当前 Java 应用程序相关的运行时对象
        Runtime runtime = Runtime.getRuntime();

        // 返回 Java 虚拟机可用处理器的数目
        System.out.println(runtime.availableProcessors());

        // 返回 Java 虚拟机剩余内存
        System.out.println(runtime.freeMemory());
    }
}
