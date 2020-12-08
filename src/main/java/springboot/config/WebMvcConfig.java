package springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springboot.interceptor.HttpInterceptor;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * SpringBoot 1.0 addInterceptors 拦截器
 * public class WebMvcConfig extends WebMvcConfigurer {}
 * <p>
 * SpringBoot 1.0 addCorsMappings 跨域
 * public class WebMvcConfig extends WebMvcConfigurerAdapter {}
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 静态资源路径配置
     * addResourceHandler:  设置访问路径前缀
     * addResourceLocations:设置资源路径
     * http://localhost:3333/static/static/img/Event-Y.jpg
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/");
        super.addResourceHandlers(registry);
    }

    /**
     * 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**").excludePathPatterns("/swagger-ui/**");
        super.addInterceptors(registry);
    }

    /**
     * 解决跨域
     * https://www.cnblogs.com/520playboy/p/7306008.html
     * <p>
     * 以下实例是全局配置，也可以针对对应的 controller 添加 @CrossOrigin
     * `@CrossOrigin(origins = "http://192.168.1.97:8080", maxAge = 3600)
     * `@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOrigins("*")
                // 是否允许证书 不再默认开启
                .allowCredentials(true)
                // 设置允许的方法
                .allowedMethods("*")
                // 跨域允许时间
                .maxAge(3600);
    }

    @Bean
    public HttpMessageConverter<String> responseBodyStringConverter() {
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }

    /**
     * 解决中文乱码
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(responseBodyStringConverter());
    }
}
