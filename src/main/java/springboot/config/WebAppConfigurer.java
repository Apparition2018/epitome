package springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springboot.interceptor.HttpInterceptor;

@Configuration
public class WebAppConfigurer extends WebMvcConfigurationSupport {

    /**
     * SpringBoot 拦截器：https://blog.csdn.net/qq_35098526/article/details/88734991
     * <p>
     * 实现自定义拦截器只需要3步
     * 1、创建我们自己的拦截器类并实现 HandlerInterceptor 接口。
     * 2、创建一个Java类继承 WebMvcConfigurationSupport，并重写 addInterceptors 方法。
     * 3、实例化我们自定义的拦截器，然后将对像手动添加到拦截器链中（在addInterceptors方法中添加）。
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/jackson/**");
        super.addInterceptors(registry);
    }
}
