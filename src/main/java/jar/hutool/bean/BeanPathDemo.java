package jar.hutool.bean;

import cn.hutool.core.bean.BeanPath;
import l.demo.Demo;

/**
 * <a href="https://hutool.cn/docs/#/core/JavaBean/表达式解析-BeanPath">BeanPath</a>
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/bean/BeanPath.html">BeanPath</a>
 * <p>使用表达式来获取 JavaBean 指定深度的对象
 *
 * @author ljh
 * @since 2022/1/19 8:49
 */
public class BeanPathDemo extends Demo {

    public static void main(String[] args) {
        BeanPath beanPath = new BeanPath("home.address");
        p("address = " + beanPath.get(personList.get(0))); // gd
    }
}
