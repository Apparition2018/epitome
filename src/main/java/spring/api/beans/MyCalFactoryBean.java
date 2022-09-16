package spring.api.beans;

import org.springframework.beans.factory.FactoryBean;

import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * MyCalFactoryBean
 *
 * @author ljh
 * created on 2022/9/14 10:41
 */
public class MyCalFactoryBean implements FactoryBean<GregorianCalendar> {
    private Locale locale;

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public GregorianCalendar getObject() throws Exception {
        return new GregorianCalendar(locale);
    }

    @Override
    public Class<?> getObjectType() {
        return GregorianCalendar.class;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
