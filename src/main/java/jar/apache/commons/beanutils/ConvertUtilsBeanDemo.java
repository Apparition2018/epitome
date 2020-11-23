package jar.apache.commons.beanutils;

        import l.demo.Person;
        import org.apache.commons.beanutils.BeanUtilsBean;
        import org.apache.commons.beanutils.ConvertUtilsBean;
        import org.apache.commons.beanutils.Converter;

        import java.lang.reflect.InvocationTargetException;

/**
 * ConvertUtilsBean
 * http://commons.apache.org/proper/commons-beanutils/javadocs/v1.9.4/apidocs/org/apache/commons/beanutils/ConvertUtilsBean.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class ConvertUtilsBeanDemo {

    /**
     * 案例：把一个 Bean 的所有 String 类型属性在输出之前加上一个前缀
     */
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Person person = new Person("张三", 18);
        // 转换工具 Bean
        ConvertUtilsBean cub = new ConvertUtilsBean();
        // 注册一个转换器
        cub.register(new Converter() {
            @Override
            @SuppressWarnings("unchecked")
            public <T> T convert(Class<T> type, Object value) {
                // 为每个 String 类型的属性加上前缀
                return (T) ("prefix-" + value);
            }
        }, String.class);
        // 建立一个依赖特定转换工具 Bean 的 BeanUtilsBean
        // BeanUtilsBean([ConvertUtilsBean convertUtilsBean, PropertyUtilsBean propertyUtilsBean])
        BeanUtilsBean beanUtils = new BeanUtilsBean(cub);
        // 输出结果
        System.out.println(beanUtils.getProperty(person, "name")); // prefix-张三
    }

}

