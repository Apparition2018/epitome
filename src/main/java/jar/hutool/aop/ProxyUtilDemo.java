package jar.hutool.aop;

import cn.hutool.aop.ProxyUtil;
import cn.hutool.aop.aspects.TimeIntervalAspect;
import l.demo.Animal.Cat;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://hutool.cn/docs/#/aop/切面代理工具-ProxyUtil">ProxyUtil</a>    切面代理工具
 * <pre>
 * SimpleAspect         简单切面对象，重写自己需要的方法
 * TimeIntervalAspect   执行时间切面对象
 * </pre>
 * <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/aop/ProxyUtil.html">ProxyUtil api</a>
 *
 * @author ljh
 * @since 2020/11/2 15:46
 */
public class ProxyUtilDemo {

    @Test
    public void testProxyUtil() {
        Cat proxyCat = ProxyUtil.proxy(new Cat(), new TimeIntervalAspect());
        proxyCat.eat();
        // 吃鱼
        // Method [l.demo.Animal$Cat.eat] execute spend [24]ms return value [null]
    }
}
