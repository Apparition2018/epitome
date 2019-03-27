package knowledge.异常和错误;

import org.junit.Test;

import java.io.IOException;

/**
 * Throwable
 * <p>
 * <p>
 * Exception 和 Error 是 Throwable 的子类
 * <p>
 * Error 指示运行时环境发生的错误，一般发生在严重的故障时，Java 程序通常不捕获错误，它们在 java 程序处理的范畴之外
 * <p>
 * Exception 分为检测性异常 (Check Exception)，和非检测性异常 (Uncheck Exception)
 */
public class ThrowableDemo {

    @Test
    public void tryCatch() {
        try {
            throw new IOException();
        } catch (IOException e) { // 子类异常 catch 放在上面
            e.printStackTrace();
        } catch (Exception e) {
            // String	    getMessage()    返回此 throwable 的详细消息字符串
            e.getMessage();

            // Throwable	getCause()      返回此 throwable 的 cause；如果 cause 不存在或未知，则返回 null
            e.getCause();

            // void	printStackTrace([PrintStream s / PrintWriter s])
            // 将此 throwable 及其追踪输出到指定的 错误流 / 输出流 / PrintWriter
            e.printStackTrace();

            // StackTraceElement[]	getStackTrace()
            // 供编程访问由 printStackTrace() 输出的堆栈跟踪信息
            e.getStackTrace();

            // Throwable	fillInStackTrace()
            // 在异常堆栈跟踪中填充
            e.fillInStackTrace();
        } finally {
            System.out.println("finally");
        }



        // Java9 try-with-resources
    }

    @Test
    public void throwThrows() throws Exception {
        throw new RuntimeException();
    }



}
