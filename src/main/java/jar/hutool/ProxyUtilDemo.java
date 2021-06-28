package jar.hutool;

import cn.hutool.aop.ProxyUtil;
import cn.hutool.aop.aspects.TimeIntervalAspect;
import l.demo.Animal.Cat;
import org.junit.jupiter.api.Test;

/**
 * ProxyUtil    切面代理工具
 * <p>
 * SimpleAspect         简单切面对象，重写自己需要的方法
 * TimeIntervalAspect   执行时间切面对象
 * <p>
 * https://hutool.cn/docs/#/aop/%E5%88%87%E9%9D%A2%E4%BB%A3%E7%90%86%E5%B7%A5%E5%85%B7-ProxyUtil
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/aop/ProxyUtil.html
 *
 * @author ljh
 * created on 2020/11/2 15:46
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
