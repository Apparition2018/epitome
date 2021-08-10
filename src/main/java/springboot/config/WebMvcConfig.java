package springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.*;
import springboot.converter.IntegerToEnumConverterFactory;
import springboot.converter.StringToEnumConverterFactory;
import springboot.interceptor.HttpInterceptor;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * WebMvcConfigurer 与 WebMvcConfigurationSupport：https://blog.csdn.net/lvyuanj/article/details/108554170
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${spring.mvc.static-path-pattern}")
    private String staticPathPatterns;

    @Value("${spring.web.resources.static-locations}")
    private String staticLocations;

    /**
     * 配置内容协议：一个请求放回多种数据格式
     * <p>
     * Path Matching and Content Negotiation：https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.developing-web-applications.spring-mvc.content-negotiation
     * Spring MVC Content Negotiation：https://www.baeldung.com/spring-mvc-content-negotiation-json-xml
     * ContentNegotiation 内容协商机制(一) --- Spring MVC 内置支持的4种内容协商方式：https://blog.csdn.net/f641385712/article/details/100020664
     * ContentNegotiation 内容协商机制(二) --- Spring MVC 内容协商实现原理及自定义配置：https://blog.csdn.net/f641385712/article/details/100060445
     * ContentNegotiation 内容协商机制(三) --- 在视图 View 上的应用：ContentNegotiatingViewResolver 深度解析：https://blog.csdn.net/f641385712/article/details/100079806
     * 1. Producers
     * 2. URL suffixes (eg .xml/.json) (5.2.4 弃用)
     * 3. URL parameter (eg ?format=json)
     * 4. Accept header
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON)
                // 是否通过请求参数来决定返回数据，默认 false
                .favorParameter(true)
                .parameterName("mediaType")
                .ignoreAcceptHeader(true);
        WebMvcConfigurer.super.configureContentNegotiation(configurer);
    }

    /**
     * 配置异步线程池
     */
    @Bean
    public ThreadPoolTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor asyncTaskExecutor = new ThreadPoolTaskExecutor();
        asyncTaskExecutor.setCorePoolSize(10);
        asyncTaskExecutor.setMaxPoolSize(100);
        asyncTaskExecutor.setKeepAliveSeconds(60);
        asyncTaskExecutor.setQueueCapacity(1000);
        asyncTaskExecutor.setThreadNamePrefix("Async-Task-");
        asyncTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        asyncTaskExecutor.initialize();
        return asyncTaskExecutor;
    }

    /**
     * 配置异步
     * <p>
     * 异步请求和异步调用有区别：https://mp.weixin.qq.com/s/Vqj7L9hQL9b11LEdDWp-HQ
     * SpringBoot 的四种异步处理：https://hello.blog.csdn.net/article/details/113924477
     * 1.@Async
     * 2.Runnable/Callable
     * 3.WebAsyncTask
     * 4.DeferredResult
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(asyncTaskExecutor());
        // Callable 超时时间
        configurer.setDefaultTimeout(1000 * 30);
        WebMvcConfigurer.super.configureAsyncSupport(configurer);
    }

    /*
     * 下面代码相当于：
     * @Controller
     * public class IndexController {
     *      @GetMapping("index")
     *      public String index() {
     *          return "index";
     *      }
     * }
     */

    /**
     * 无业务逻辑跳转
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        WebMvcConfigurer.super.addViewControllers(registry);
    }

    /**
     * 静态资源路径配置
     * extends WebMvcConfigurationSupport 会使默认配置失效，需重写 addResourceHandlers
     * implements WebMvcConfigurer 则不需要，在 application.yml 配置即可
     * http://localhost:3333/img/Event-Y.jpg
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
        // Swagger3：http://localhost:3333/swagger-ui/index.html
        registry.addResourceHandler("/swagger-ui/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/META-INF/resources/webjars/springfox-swagger-ui/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    /**
     * 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/demo/**").excludePathPatterns("/demo/post");
        WebMvcConfigurer.super.addInterceptors(registry);
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
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(responseBodyStringConverter());
    }

    /**
     * Spring Boot 使用枚举类型作为请求参数：https://xkcoding.com/2019/01/30/spring-boot-request-use-enums-params.html
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new IntegerToEnumConverterFactory());
        registry.addConverterFactory(new StringToEnumConverterFactory());
    }
}
