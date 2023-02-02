package knowledge.throwable;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/lang/Throwable.html">Throwable</a>
 * <p>Throwable 有两个子类 Error 和 Exception
 * <pre>
 * 1 Error 指示运行时环境发生的错误，一般发生在严重的故障时，java 程序通常不捕获此类错误，它们在 java 程序处理范畴之外
 *      InternalError, NoClassDefFoundError, IOError, StackOverflowError, outOfMemoryError,
 * 2 Exception 分为检测性异常 (Checked Exception)，和非检测性异常 (Unchecked Exception)
 *   2.1 检测性异常：强制处理，捕获异常或向上抛；继承 Exception
 *       IOException, ClassNotFoundException, NoSuchMethodException, NoFieldException, CloneNotSupportedException
 *   2.2 非检测性异常：不强制；继承 RuntimeException 或 Error
 *       NullPointerException, ClassCastException, UnsupportedOperationException
 *       ArithmeticException, ArrayStoreException, IllegalArgumentException, IndexOutOfBoundException, NumberFormatException, NegativeArraySizeException
 * </pre>
 * 阿里异常处理：
 * <pre>
 * 1 Java 类库中定义的可以通过预检查方式规避的 RuntimeException 异常不应该通过 catch 的方式来处理，比如：NullPointerException，IndexOutOfBoundsException 等等
 * 2 对大段代码进行 try-catch，使程序无法根据不同的异常做出正确的应激反应，也不利于定位问题，这是一种不负责任的表现
 * 3 不要在 finally 块中使用 return
 * 4 在调用 RPC、二方包、或动态生成类的相关方法时，捕捉异常使用 Throwable 类进行拦截 ???
 * 5 方法的返回值可以为 null，不强制返回空集合，或者空对象等，必须添加注释充分说明什么情况下会返回 null 值
 * 6 防止 NPE，是程序员的基本修养，注意 NPE 产生的场景
 *   6.1 返回类型为基本数据类型，return 包装数据类型的对象时，自动拆箱有可能产生 NPE
 *   6.2 数据库的查询结果可能为 null
 *   6.3 集合里的元素即使 isNotEmpty，取出的数据元素也可能为 null
 *   6.4 远程调用返回对象时，一律要求进行空指针判断，防止 NPE
 *   6.5 对于 Session 中获取的数据，建议进行 NPE 检查，避免空指针
 *   6.6 级联调用 obj.getA().getB().getC()；一连串调用，易产生 NPE
 * 7 定义时区分 unchecked / checked 异常，避免直接抛出 new RuntimeException()，更不允许抛出 Exception 或者 Throwable，应使用有业务含义的自定义异常
 *   推荐业界已定义过的自定义异常，如：DAOException / ServiceException 等
 * 8 对于公司外的 http / api 开放接口必须使用 errorCode，而应用内部推荐异常抛出；跨应用间 RPC 调用优先考虑使用 Result 方式，封装 isSuccess() 方法、errorCode、errorMessage；应用内部推荐异常抛出
 * </pre>
 * 参考：
 * <pre>
 * <a href="https://blog.csdn.net/kingzone_2008/article/details/8535287">选择 Checked Exception 还是 Unchecked Exception</a>
 * <a href="https://www.cnblogs.com/jurendage/p/11255197.html">Java 生鲜电商平台-统一异常处理及架构实战</a>
 * </pre>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class ThrowableDemo extends Demo {

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

            // String	            getMessage()            返回此 throwable 的详细消息字符串
            p(e.getMessage());

            // String	            getLocalizedMessage()   返回此 throwable 的详本地化描述
            p(e.getLocalizedMessage());

            // Throwable	        getCause()              返回此 throwable 的 cause；如果 cause 不存在或未知，则返回 null
            e.getCause().getMessage();

            // void	                printStackTrace([PrintStream s / PrintWriter s])
            // 将此 throwable 及其追踪输出到指定的 错误流 / 输出流 / PrintWriter
            e.printStackTrace();
        } catch (Exception e) {
            // StackTraceElement[]	getStackTrace()         供编程访问由 printStackTrace() 输出的堆栈跟踪信息
            e.getStackTrace();

            // Throwable	        fillInStackTrace()      在异常堆栈跟踪中填充
            e.fillInStackTrace();
        } finally {
            p("finally");
        }
    }

    /**
     * <a href="https://www.runoob.com/java/java9-try-with-resources-improvement.html">try-with-resources</a> 是 JDK1.7 中一个新的异常处理机制
     * <p>在 JDK9，如果你已经有一个资源是 final 或等效于 final 变量,您可以在 try-with-resources 语句中使用该变量，而无需在 try-with-resources 语句中声明一个新变量
     */
    @Test
    public void testTryWithResources() {
        try (FileInputStream fis = new FileInputStream(DEMO_FILE_PATH)) {
            p(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * <pre>
     * void         addSuppressed(Throwable exception)      将指定的异常附加到为了传递此异常而被抑制的异常
     * Throwable[]  getSuppressed()                         返回一个包含所有被抑制的异常的数组，通常由 try-with-resources 语句来传递这个异常
     * </pre>
     * 以下代码为上面方法的反编译代码
     */
    @Test
    public void suppressed() {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/demo/demo");
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
            throw new RuntimeException(var14.getMessage(), var14);
        }
    }

    /**
     * <pre>
     * throw    抛出一个具体的异常类型
     * throws   声明一个方法可能产生的所有异常，不做任何处理而是将异常往上传
     * </pre>
     */
    @Test
    public void testThrowThrows() throws Exception {
        throw new RuntimeException();
    }
}
