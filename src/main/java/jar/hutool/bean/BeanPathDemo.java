package jar.hutool.bean;

import cn.hutool.core.bean.BeanPath;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

/**
 * BeanPath
 * 使用表达式来获取 JavaBean 指定深度的对象
 * <p>
 * https://hutool.cn/docs/#/core/JavaBean/%E8%A1%A8%E8%BE%BE%E5%BC%8F%E8%A7%A3%E6%9E%90-BeanPath
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/bean/BeanPath.html
 *
 * @author ljh
 * @since 2022/1/19 8:49
 */
public class BeanPathDemo extends Demo {

    @Test
    public void testBeanPath() {
        BeanPath beanPath = new BeanPath("home.address");
        p("address = " + beanPath.get(personList.get(0))); // gd
    }
}
