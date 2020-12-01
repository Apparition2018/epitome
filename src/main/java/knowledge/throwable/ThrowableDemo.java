package knowledge.throwable;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import static l.demo.Demo.p;

/**
 * Throwable
 * Throwable 有两个子类 Error 和 Exception
 * Error 指示运行时环境发生的错误，一般发生在严重的故障时，java 程序通常不捕获此类错误，它们在 java 程序处理范畴之外
 * -    InternalError, NoClassDefFoundError, IOError, StackOverflowError, outOfMemoryError,
 * Exception 分为检测性异常 (Check Exception)，和非检测性异常 (Uncheck Exception)
 * -    CheckedException：强制处理，捕获异常或向上抛
 * -        IOException, ClassNotFoundException, NoSuchMethod, NoFieldException, CloneNotSupportedException
 * -    RuntimeException：不强制
 * -        NullPointerException, ClassCastException, UnsupportedOperationException
 * ArithmeticException, ArrayStoreException, IllegalArgumentException, IndexOutOfBoundException, NumberFormatException, NegativeArraySizeException
 * https://www.runoob.com/manual/jdk1.6/java/lang/Throwable.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class ThrowableDemo {

    /**
     * try-catch 捕获异常
     */
    @Test
    public void testTryCatch() {
        try {
            p("before exception");
            throw new IOException("exception msg");
        } catch (IOException e) {
            // 子类异常 catch 放在上面
            p("io exception");

            // String	            getMessage()        返回此 throwable 的详细消息字符串
            p(e.getMessage());

            // Throwable	        getCause()          返回此 throwable 的 cause；如果 cause 不存在或未知，则返回 null
            e.getCause().getMessage();

            // void	                printStackTrace([PrintStream s / PrintWriter s])
            // 将此 throwable 及其追踪输出到指定的 错误流 / 输出流 / PrintWriter
            e.printStackTrace();
        } catch (Exception e) {
            // StackTraceElement[]	getStackTrace()     供编程访问由 printStackTrace() 输出的堆栈跟踪信息
            e.getStackTrace();

            // Throwable	        fillInStackTrace()  在异常堆栈跟踪中填充
            e.fillInStackTrace();
        } finally {
            p("finally");
        }

    }

    /**
     * try-with-resources 是 JDK7 中一个新的异常处理机制
     * 在 JDK9，如果你已经有一个资源是 final 或等效于 final 变量,您可以在 try-with-resources 语句中使用该变量，而无需在 try-with-resources 语句中声明一个新变量
     * https://www.runoob.com/java/java9-try-with-resources-improvement.html
     */
    @Test
    public void testTryWithResources() {
        try (FileInputStream fis = new FileInputStream(new File("test"))) {
            p(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * void	        addSuppressed(Throwable exception)      将指定的异常附加到为了传递此异常而被抑制的异常
     * Throwable[]	getSuppressed()                         返回一个包含所有被抑制的异常的数组，通常由 try-with-resources 语句来传递这个异常
     * <p>1
     * 以下代码为上面方法的反编译代码
     */
    @Test
    public void suppressed() {
        try {
            FileInputStream fis = new FileInputStream(new File("test"));
            Throwable var2 = null;

            try {
                p(fis.read());
            } catch (Throwable var12) {
                var2 = var12;
                throw var12;
            } finally {
                if (fis != null) {
                    if (var2 != null) {
                        try {
                            fis.close();
                        } catch (Throwable var11) {
                            var2.addSuppressed(var11);
                        }
                    } else {
                        fis.close();
                    }
                }
            }
        } catch (IOException var14) {
            p(Arrays.toString(var14.getSuppressed()));
            throw new RuntimeException(var14.getMessage(), var14);
        }
    }

    /**
     * throw:   抛出一个具体的异常类型
     * throws:  声明一个方法可能产生的所有异常，不做任何处理而是将异常往上传
     */
    @Test
    public void testThrowThrows() throws Exception {
        throw new RuntimeException();
    }

}
