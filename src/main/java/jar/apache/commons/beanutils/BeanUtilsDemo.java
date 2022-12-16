package jar.apache.commons.beanutils;

import l.demo.Demo;
import l.demo.User;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

/**
 * BeanUtils
 * http://commons.apache.org/proper/commons-beanutils/javadocs/v1.9.4/apidocs/org/apache/commons/beanutils/BeanUtils.html
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class BeanUtilsDemo extends Demo {

    @Test
    public void testBeanUtils() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        // JavaBean → JavaBean
        User origin = new User("2000-01-01");
        User user = new User();
        // static void      copyProperties(Object dest, Object orig)
        BeanUtils.copyProperties(user, origin);

        // JavaBean → Map
        Map<String, String> map = BeanUtils.describe(user);
        p(map); // User{birthString='2000-01-01'}

        // 注册本地日期转换器
        ConvertUtils.register(new DateLocaleConverter(), Date.class);
        
        // Map → JavaBean
        BeanUtils.populate(user, map);
        p(user); // User{birthString='2000-01-01'}
        
        // 设置属性值
        BeanUtils.setProperty(user, "birth", "2001-01-01");
        p(user); // User{birthString='2001-01-01'}
    }

    /**
     * HttpServletRequest → JavaBean
     * https://www.cnblogs.com/vmax-tam/p/4159985.html
     */
    @SuppressWarnings({"unchecked", "unused"})
    public static <T> T requestToBean(HttpServletRequest request, Class<T> clazz) {
        Object obj = null;
        try {
            obj = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Enumeration<String> e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String name = e.nextElement();
            String value = request.getParameter(name);
            try {
                BeanUtils.setProperty(obj, name, value);
            } catch (IllegalAccessException | InvocationTargetException e1) {
                e1.printStackTrace();
            }
        }

        return (T) obj;
    }

}
