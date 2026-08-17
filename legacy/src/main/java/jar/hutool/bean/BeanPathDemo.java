package jar.hutool.bean;

import cn.hutool.core.bean.BeanPath;

import static com.ljh.commons.Demo.personList;

/**
 * <a href="https://doc.hutool.cn/pages/BeanPath/">BeanPath</a> 表达式解析
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/bean/BeanPath.html">BeanPath</a>
 * <p>使用表达式来获取 JavaBean 指定深度的对象
 *
 * @author ljh
 * @since 2022/1/19 8:49
 */
public class BeanPathDemo {

    public static void main(String[] args) {
        BeanPath beanPath = new BeanPath("home.address");
        System.out.println("address = " + beanPath.get(personList.getFirst())); // gd
    }
}
