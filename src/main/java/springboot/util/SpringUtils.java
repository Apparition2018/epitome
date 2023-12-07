package springboot.util;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * SpringUtils
 * RuoYi (SpringUtils)
 *
 * @author ljh
 * @since 2021/6/21 16:59
 */
@Component
public final class SpringUtils implements ApplicationContextAware {
    private SpringUtils() {
        throw new AssertionError(String.format("No %s instances for you!", this.getClass().getName()));
    }

    @Getter
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) throws BeansException {
        return applicationContext.getBean(clazz);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) throws BeansException {
        return applicationContext.getBeansOfType(clazz);
    }

    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }

    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getType(name);
    }

    public static String[] getActiveProfiles() {
        return applicationContext.getEnvironment().getActiveProfiles();
    }
}
