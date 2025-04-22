package jar.hutool.lang;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Singleton;
import l.demo.Person;

import java.util.Objects;

/**
 * <a href="https://doc.hutool.cn/pages/Singleton/">Singleton</a>   单例工具
 * <p>一般获取单例方式：
 * <pre>
 * 1 手写单例，通过 getInstance() 获取
 * 2 通过 Spring 统一管理对象获取
 * </pre>
 * <a href="https://plus.hutool.cn/apidocs/cn/hutool/core/lang/Singleton.html">Singleton api</a>
 * <pre>
 * static void      put([String key, ]Object obj)       将已有对象放入单例中，其 Class 做为键
 * static void      remove(Class<?> clazz/String key)   移除指定 Singleton 对象
 * static void      destroy()                           清除所有 Singleton 对象
 * </pre>
 *
 * @author ljh
 * @since 2020/11/19 20:46
 */
public class SingletonDemo {

    public static void main(String[] args) {
        Person person = Singleton.get(Person.class);
        Person person2 = Singleton.get(Person.class);
        Assert.isTrue(Objects.equals(person, person2));
    }
}
