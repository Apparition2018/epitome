package jar.hutool;

import cn.hutool.core.lang.Assert;
import l.demo.Demo;
import org.junit.Test;

/**
 * Assert
 * 主要对参数的有效性做校验，当不满足断言条件时，会抛出 IllegalArgumentException 或 IllegalStateException
 * https://hutool.cn/docs/#/core/%E8%AF%AD%E8%A8%80%E7%89%B9%E6%80%A7/%E6%96%AD%E8%A8%80-Assert
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/lang/Assert.html
 *
 * @author Arsenal
 * created on 2020/11/3 1:56
 */
public class AssertDemo extends Demo {

    /**
     * isTrue(boolean expression, ...), isFalse(boolean expression, ...)
     * isNull(Object object, ...), notNull(Object object, ...)
     * notEmpty(T text, ...)
     * notBlank(T text, ...)
     * notContain(CharSequence textToSearch, T substring, ...)
     * notEmpty(T[] array, ...)
     * noNullElements(T[] array, ...)
     * notEmpty(T collection, ...)
     * notEmpty(T map, ...)
     * isInstanceOf(Class<?> type, T obj, ...)
     * isAssignable(Class<?> superType, Class<?> subType, ...)
     * state(boolean expression, ...)
     * checkIndex(int index, int size, ...)
     * checkBetween(int/long/double/Number value, int/long/double/Number min, int/long/double/Number max)
     * badIndexMsg(int index, int size, String desc, Object... params)
     */
    @Test
    public void testAssert() {
        // static <X extends Throwable> void    isTrue(boolean expression[, String errorMsgTemplate, Object... params])
        Assert.isTrue(list.size() == 9, "the list size is {}", list.size());
        // static <X extends Throwable> void    isFalse(boolean expression[, Supplier<? extends X> supplier])
        Assert.isFalse(list.size() == 0, () -> new IllegalArgumentException("the list size is " + list.size()));
    }
}
