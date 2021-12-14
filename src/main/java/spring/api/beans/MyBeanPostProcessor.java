package spring.api.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.NonNull;

/**
 * MyBeanPostProcessor
 *
 * @author ljh
 * created on 2021/12/14 9:40
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor() {
        System.out.println("BeanPostProcessor's construct()");
    }

    @Override
    public Object postProcessBeforeInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        System.out.println("BeanPostProcessor's postProcessBeforeInitialization()");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        System.out.println("BeanPostProcessor's postProcessAfterInitialization()");
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
