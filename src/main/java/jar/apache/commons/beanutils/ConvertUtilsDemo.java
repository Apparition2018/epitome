package jar.apache.commons.beanutils;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static l.demo.Demo.p;

/**
 * ConvertUtils
 * String   →   T
 * String[] →   T[]
 * http://commons.apache.org/proper/commons-beanutils/javadocs/v1.9.4/apidocs/org/apache/commons/beanutils/ConvertUtils.html
 *
 * @author Arsenal
 * created on 2020/11/14 21:54
 */
public class ConvertUtilsDemo {

    @Test
    @SuppressWarnings("unchecked")
    public void testConvertUtils() {
        ConvertUtils.register(new Converter() {
            @Override
            public <T> T convert(Class<T> clazz, Object o) {
                try {
                    return (T) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) o);
                } catch (ParseException e) {
                    return null;

                }
            }
        }, Date.class);

        String DateStr = "2008-08-08 20:08:08";
        p(ConvertUtils.convert(DateStr, Date.class), true); // Fri Aug 08 20:08:08 CST 2008

        String[] DateStrArr = new String[]{DateStr, DateStr};
        p(ConvertUtils.convert(DateStrArr, Date.class));    // [Fri Aug 08 20:08:08 CST 2008, Fri Aug 08 20:08:08 CST 2008]
    }
}
