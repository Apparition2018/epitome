package jar.hutool.bean;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.ListUtil;
import l.demo.Demo;
import l.demo.Person;
import l.demo.Person.Home;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * BeanUtil     Bean工具
 * <p>
 * static PropertyEditor        findEditor(Class<?> type)                               返回类型转换器 PropertyEditor
 * static PropertyDescriptor[]  getPropertyDescriptors(Class<?>[, fieldName][, ignoreCase])  返回 Bean 字段描述数组
 * static Map<String, PDMap>    getPropertyDescriptorMap(Class<?>, ignoreCase)          返回字段名和字段描述 Map，获得的结果会缓存在 BeanInfoCache 中
 * static BeanDesc              getBeanDesc(Class<?> clazz)                             返回 BeanDesc
 * <p>
 * static boolean               isBean(Class<?> clazz)                                  是否为 Bean 对象
 * static boolean               isReadableBean(Class<?> clazz)                          是否为可读的 Bean 对象
 * static boolean	            isEmpty(Object bean, String... ignoreFiledNames)        是否为空对象，本身为 null 或 所有属性为 null
 * static boolean	            isNotEmpty(Object bean, String... ignoreFiledNames)     是否不为空对象，本身不为 null 或 含有非 null 属性
 * static boolean	            hasNullField(Object bean, String... ignoreFiledNames)   是否含有 null 属性
 * static boolean	            isMatchName(Object bean, beanClassName, isSimple)       是否匹配类名
 * static boolean               hasSetter(Class<?> clazz)                               是否有 Setter 方法
 * static boolean               hasGetter(Class<?> clazz)                               是否有 Getter 方法
 * <p>
 * https://hutool.cn/docs/#/core/JavaBean/Bean%E5%B7%A5%E5%85%B7-BeanUtil
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/bean/BeanUtil.html
 *
 * @author ljh
 * @since 2020/11/9 14:54
 */
public class BeanUtilDemo extends Demo {

    @Test
    public void testBeanUtil() {
        // static void	    descForEach(Class<?>, Consumer<? super PropDesc>)
        // 遍历 Bean 的属性，进行 Consumer 操作
        BeanUtil.descForEach(Person.class, propDesc -> p(propDesc.getFieldName()));

        Person person = personList.get(0);
        person.setOtherInfo(ListUtil.toList("医生", "爸爸"));
        person.setHome(new Home().setAddress("北京").setTel("010"));
        p(person); // Person{id=1, name='张三', otherInfo=[医生, 爸爸], home=Home(address='GD', tel='123')}

        // static void	    setFieldValue(Object bean, fieldNameOrIndex, Object value)
        // 通过反射设置字段值，支持 Map 和 Collection
        BeanUtil.setFieldValue(person, "name", "张叄");
        // static Object    getFieldValue(Object bean, fieldNameOrIndex)
        // 通过反射获取字段值，支持 Map 和 Collection
        p(BeanUtil.getFieldValue(person, "name")); // 张叄

        // static void	    setProperty(Object bean, String expression, Object value)
        // 通过表达式设置字段值，支持 Map 和 Collection
        BeanUtil.setProperty(person, "home.address", "ZS");
        // static <T> T	    getProperty(Object bean, String expression)
        // 通过表达式获取字段值，支持 Map 和 Collection
        p(BeanUtil.getProperty(person, "otherInfo[1]")); // 爸爸

        // Bean → Map
        Map<String, Object> personMap = BeanUtil.beanToMap(person);
        p(personMap);
        // Map → Bean
        p(BeanUtil.mapToBean(personMap, Person.class, true, CopyOptions.create()));
        // Map → Bean
        p(BeanUtil.fillBeanWithMap(personMap, person, true, false));
        // Map → Bean
        p(BeanUtil.toBean(personMap, Person.class));

        // static <T> T	    copyProperties(Object source, Class<T> tClass[, String... ignoreProperties])
        // 复制属性
        Person copyPerson = BeanUtil.copyProperties(person, Person.class, "home");
        p(copyPerson);
        // static void	    copyProperties(Object source, Object target[, String... ignoreProperties/boolean ignoreCase/CopyOptions copyOptions])
        // 复制属性
        BeanUtil.copyProperties(person, copyPerson, "home");
        p(copyPerson);
    }
}
