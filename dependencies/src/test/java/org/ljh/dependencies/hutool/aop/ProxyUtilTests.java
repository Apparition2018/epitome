package org.ljh.dependencies.hutool.aop;

import cn.hutool.aop.ProxyUtil;
import cn.hutool.aop.aspects.TimeIntervalAspect;
import com.ljh.commons.Animal.Cat;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://doc.hutool.cn/pages/ProxyUtil/">ProxyUtil</a>   切面代理工具
 * <a href="https://plus.hutool.cn/apidocs/cn/hutool/aop/ProxyUtil.html">ProxyUtil api</a>
 *
 * @author ljh
 * @since 2020/11/2 15:46
 */
public class ProxyUtilTests {

    @Test
    public void test() {
        Cat proxyCat = ProxyUtil.proxy(new Cat(), new TimeIntervalAspect());
        proxyCat.eat();
        // 吃鱼
        // Method [l.demo.Animal$Cat.eat] execute spend [24]ms return value [null]
    }
}
