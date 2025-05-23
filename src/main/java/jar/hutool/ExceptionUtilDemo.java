package jar.hutool;

import cn.hutool.core.exceptions.ExceptionUtil;

import static l.demo.Demo.p;

/**
 * <a href="https://doc.hutool.cn/pages/ExceptionUtil/">ExceptionUtil</a>   异常工具
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/exceptions/ExceptionUtil.html">ExceptionUtil api</a>
 *
 * @author ljh
 * @since 2020/11/19 23:31
 */
public class ExceptionUtilDemo {

    public static void main(String[] args) {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            // 完整消息
            p(ExceptionUtil.getMessage(e));         // ArithmeticException: / by zero
            // 简单消息
            p(ExceptionUtil.getSimpleMessage(e));   // / by zero
            // 异常链中最尾端的异常的消息
            p(ExceptionUtil.getRootCauseMessage(e));// ArithmeticException: / by zero

            // 堆栈转为完整字符串
            p(ExceptionUtil.stacktraceToString(e));
            // 堆栈转为单行完整字符串
            p(ExceptionUtil.stacktraceToOneLineString(e));
        }
    }
}
