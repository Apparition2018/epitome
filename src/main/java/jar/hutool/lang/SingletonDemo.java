package jar.hutool.lang;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Singleton;
import l.demo.Person;
import org.junit.jupiter.api.Test;

/**
 * Singleton    单例工具
 * 一般获取单例方式：
 * 1.手写单例，通过 getInstance() 获取
 * 2.通过 Spring 统一管理对象获取
 * <p>
 * static void	    put([String key, ]Object obj)           将已有对象放入单例中，其 Class 做为键
 * static void	    remove(Class<?> clazz/String key)       移除指定 Singleton 对象
 * static void	    destroy()                               清除所有 Singleton 对象
 * <p>
 * https://hutool.cn/docs/#/core/%E8%AF%AD%E8%A8%80%E7%89%B9%E6%80%A7/%E5%8D%95%E4%BE%8B%E5%B7%A5%E5%85%B7-Singleton
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/lang/Singleton.html
 *
 * @author Arsenal
 * created on 2020/11/19 20:46
 */
public class SingletonDemo {

    @Test
    public void testSingleton() {
        Person person = Singleton.get(Person.class);
        Person person2 = Singleton.get(Person.class);
        Assert.isTrue(person.equals(person2));
    }
}
