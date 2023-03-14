package spring.api.beans.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.NonNull;

import static l.demo.Demo.pe;

/**
 * MyBeanPostProcessor
 * 在 Spring 容器完成实例化、配置和初始化 Bean 之后实现一些自定义逻辑
 *
 * @author ljh
 * @since 2021/12/14 9:40
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor() {
        pe("BeanPostProcessor's construct()");
    }

    @Override
    public Object postProcessBeforeInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        pe("BeanPostProcessor's postProcessBeforeInitialization(): " + beanName);
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        pe("BeanPostProcessor's postProcessAfterInitialization(): " + beanName);
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
