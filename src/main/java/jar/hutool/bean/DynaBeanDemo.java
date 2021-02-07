package jar.hutool.bean;

import cn.hutool.core.bean.DynaBean;
import l.demo.Person;
import org.junit.jupiter.api.Test;

import static l.demo.Demo.p;

/**
 * DynaBean
 * 使用反射机制动态操作 JavaBean 的一个封装类
 * https://hutool.cn/docs/#/core/JavaBean/DynaBean
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/bean/DynaBean.html
 *
 * @author ljh
 * created on 2020/11/9 15:55
 */
public class DynaBeanDemo {

    @Test
    public void testDynaBean() {
        DynaBean dynaBean = DynaBean.create(Person.class);

        dynaBean.set("id", 1);
        dynaBean.set("name", "张三");

        p(dynaBean.get("name"));

        dynaBean.invoke("getName");
    }
}
