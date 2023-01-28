package spring.api.beans.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.lang.NonNull;

/**
 * MyBeanFactoryPostProcessor
 *
 * @author ljh
 * @since 2021/12/14 9:49
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public MyBeanFactoryPostProcessor() {
        System.err.println("BeanFactoryPostProcessor's construct()");
    }

    @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.err.println("BeanFactoryPostProcessor's postProcessBeanFactory()");
    }
}
