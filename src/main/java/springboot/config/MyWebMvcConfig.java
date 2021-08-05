package springboot.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.*;
import springboot.converter.IntegerToEnumConverterFactory;
import springboot.converter.StringToEnumConverterFactory;
import springboot.interceptor.HttpInterceptor;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * SpringBoot 1.0
 * addInterceptors 拦截器：
 * public class WebMvcConfig implements WebMvcConfigurer {}
 * addCorsMappings 跨域：
 * public class WebMvcConfig extends WebMvcConfigurerAdapter {}
 * <p>
 * 继承 WebMvcConfigurationSupport 导致自动配置不生效：https://blog.csdn.net/qq_36850813/article/details/87859047
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
@Configuration
public class MyWebMvcConfig extends WebMvcConfigurationSupport {

    @Value("${spring.mvc.static-path-pattern}")
    private String staticPathPatterns;

    @Value("${spring.web.resources.static-locations}")
    private String staticLocations;

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
    }

    /**
     * 静态资源路径配置
     * http://localhost:3333/static/img/Event-Y.jpg
     * <p>
     * webjars 默认映射规则：/webjars/** ==> classpath:/META-INF/resources/webjars/
     * 静态资源默认映射规则：/** ==> classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/
     * <p>
     * Springboot2 静态资源配置：https://www.cnblogs.com/xiaomaomao/p/14278402.html
     * SpringBoot2 静态资源访问问题：https://blog.csdn.net/afgasdg/article/details/106474734
     *
     * @see org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter#addResourceHandlers
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // addResourceHandler       设置资源映射路径（前缀）
        // addResourceLocations     设置资源位置
        registry.addResourceHandler("/webjars/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/META-INF/resources/webjars/");
        registry.addResourceHandler(staticPathPatterns).addResourceLocations(StringUtils.split(staticLocations, ","));
        registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/");
        // Swagger3：http://localhost:3333/swagger-ui/index.html
        registry.addResourceHandler("/swagger-ui/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/META-INF/resources/webjars/springfox-swagger-ui/");
        super.addResourceHandlers(registry);
    }

    /**
     * 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/demo/**").excludePathPatterns("/demo/post");
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
                .allowedOriginPatterns("*")
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

    /**
     * Spring Boot 使用枚举类型作为请求参数：https://xkcoding.com/2019/01/30/spring-boot-request-use-enums-params.html
     */
    @Override
    protected void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new IntegerToEnumConverterFactory());
        registry.addConverterFactory(new StringToEnumConverterFactory());
    }
}
