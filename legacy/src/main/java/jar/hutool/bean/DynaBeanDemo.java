package jar.hutool.bean;

import cn.hutool.core.bean.DynaBean;
import l.demo.Person;

import static l.demo.Demo.p;

/**
 * <a href="https://doc.hutool.cn/pages/DynaBean/">DynaBean</a>
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/bean/DynaBean.html">DynaBean api</a>
 * <p>使用反射机制动态操作 JavaBean 的一个封装类
 *
 * @author ljh
 * @since 2020/11/9 15:55
 */
public class DynaBeanDemo {

    public static void main(String[] args) {
        DynaBean dynaBean = DynaBean.create(Person.class);

        dynaBean.set("id", 1);
        dynaBean.set("name", "张三");

        p(dynaBean.get("name"));

        dynaBean.invoke("getName");
    }
}
