package jar.hutool.lang;

import cn.hutool.core.lang.Assert;

import static l.demo.Demo.list;

/**
 * <a href="https://hutool.cn/docs/#/core/语言特性/断言-Assert">Assert</a>
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/lang/Assert.html">Assert api</a>
 * <p>主要对参数的有效性做校验，当不满足断言条件时，会抛出 IllegalArgumentException 或 IllegalStateException
 *
 * @author ljh
 * @since 2020/11/3 1:56
 */
public class AssertDemo {

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
    public static void main(String[] args) {
        // static <X extends Throwable> void    isTrue(boolean expression[, String errorMsgTemplate, Object... params])
        Assert.isTrue(list.size() == 9, "the list size is {}", list.size());
        // static <X extends Throwable> void    isFalse(boolean expression[, Supplier<? extends X> supplier])
        Assert.isFalse(list.size() == 0, () -> new IllegalArgumentException("the list size is " + list.size()));
    }
}
