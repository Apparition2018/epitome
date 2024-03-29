package jar.apache.commons.beanutils;

import cn.hutool.core.date.DatePattern;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * <a href="http://commons.apache.org/proper/commons-beanutils/javadocs/v1.9.4/apidocs/org/apache/commons/beanutils/ConvertUtils.html">ConvertUtils</a>
 * <pre>
 * String   →   T
 * String[] →   T[]
 * </pre>
 *
 * @author ljh
 * @since 2020/11/14 21:54
 */
public class ConvertUtilsDemo {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ConvertUtils.register(new Converter() {
            @Override
            public <T> T convert(Class<T> clazz, Object o) {
                try {
                    return (T) DateUtils.parseDate((String) o, DatePattern.NORM_DATETIME_PATTERN);
                } catch (ParseException e) {
                    return null;
                }
            }
        }, Date.class);

        String DateStr = "2008-08-08 20:08:08";
        System.out.println(ConvertUtils.convert(DateStr, Date.class));       // Fri Aug 08 20:08:08 CST 2008

        String[] DateStrArr = new String[]{DateStr, DateStr};
        System.out.println(ConvertUtils.convert(DateStrArr, Date.class));    // [Fri Aug 08 20:08:08 CST 2008, Fri Aug 08 20:08:08 CST 2008]
    }
}
