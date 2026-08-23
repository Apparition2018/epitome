package org.ljh.dependencies.group.hutool.lang;

import cn.hutool.core.lang.Singleton;
import com.ljh.commons.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://doc.hutool.cn/pages/Singleton/">Singleton</a>   单例工具
 * <p>一般获取单例方式：
 * <pre>
 * 1 手写单例，通过 getInstance() 获取
 * 2 通过 Spring 统一管理对象获取
 * </pre>
 * <a href="https://plus.hutool.cn/apidocs/cn/hutool/core/lang/Singleton.html">Singleton api</a>
 *
 * @author ljh
 * @since 2020/11/19 20:46
 */
public class SingletonTests {

    @Test
    public void test() {
        Person person = Singleton.get(Person.class);
        Person person2 = Singleton.get(Person.class);
        Assertions.assertSame(person, person2);
    }
}
