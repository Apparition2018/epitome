package jar.apache.commons.beanutils;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * ConvertUtilsBean
 * <p>
 * http://commons.apache.org/proper/commons-beanutils/javadocs/v1.9.2/apidocs/org/apache/commons/beanutils/ConvertUtilsBean.html
 */
public class ConvertUtilsBeanDemo {

    /**
     * 案例：把一个 Bean 的所有 String 类型属性在输出之前加上一个前缀
     */
    @Test
    public void test() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // 一个简单的 Bean 对象
        Student s = new Student("张三", 18);
        // 转换工具
        ConvertUtilsBean cub = new ConvertUtilsBean();
        // 注册一个转换器
        cub.register(new Converter() {
            @Override
            public Object convert(Class type, Object value) {
                // 为每个 String 类型的属性加上前缀
                return "prefix-" + value;
            }
        }, String.class);
        // 建立一个依赖特定转换工具的 Bean 工具类
        // BeanUtilsBean([ConvertUtilsBean convertUtilsBean, PropertyUtilsBean propertyUtilsBean])
        BeanUtilsBean beanUtils = new BeanUtilsBean(cub);
        // 输出结果
        System.out.println(beanUtils.getProperty(s, "name")); // prefix-张三
    }

}

