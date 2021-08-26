package springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import springboot.controller.WebMvcConfigController;
import springboot.converter.IntegerToEnumConverterFactory;
import springboot.converter.StringToEnumConverterFactory;
import springboot.formatter.BooleanFormatAnnotationFormatterFactory;
import springboot.handler.MyHandlerMethodReturnValueHandler;
import springboot.interceptor.HttpInterceptor;
import springboot.resolver.argument.PersonArgumentResolver;
import springboot.util.SpringContextUtils;

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

    /**
     * 1.配置路径匹配
     * A Quick Guide to Spring MVC Matrix Variables：https://www.baeldung.com/spring-mvc-matrix-variables
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 给匹配的 Controller 增加前缀
        configurer.addPathPrefix("mvc", p -> p.isInstance(SpringContextUtils.getBean(WebMvcConfigController.class)));
        configurer.setUseTrailingSlashMatch(true);
        WebMvcConfigurer.super.configurePathMatch   (configurer);
    }

    @Value("${spring.mvc.static-path-pattern}")
    private String staticPathPatterns;

    @Value("${spring.web.resources.static-locations}")
    private String staticLocations;

    /**
     * 2.配置内容协议：一个请求放回多种数据格式
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
                // 是否通过请求参数来决定返回数据，默认 false；和 addReturnValueHandlers 冲突
                .favorParameter(true)
                .parameterName("mediaType")
                .ignoreAcceptHeader(true);
        WebMvcConfigurer.super.configureContentNegotiation(configurer);
    }

    /**
     * 异步线程池
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
     * 3.配置异步
     * <p>
     * Asynchronous Programming in Java：https://www.baeldung.com/java-asynchronous-programming
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

    /**
     * 4.配置默认 Servlet 处理器
     * 常见用于 DispatcherServlet 被映射到 "/"，从而覆盖 Servlet 容器对静态资源的默认处理
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        WebMvcConfigurer.super.configureDefaultServletHandling(configurer);
    }

    /**
     * 5.添加格式化器
     * 枚举：https://xkcoding.com/2019/01/30/spring-boot-request-use-enums-params.html
     * 注解：https://www.cnblogs.com/eclipse-/p/10998269.html
     * Binding Individual Objects to Request Parameters：https://www.baeldung.com/spring-mvc-custom-data-binder#binding-individual-objects-to-request-parameters
     * Binding a Hierarchy of Objects：https://www.baeldung.com/spring-mvc-custom-data-binder#binding-a-hierarchy-of-objects
     * Guide to Spring Type Conversions：https://www.baeldung.com/spring-type-conversions
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new IntegerToEnumConverterFactory());
        registry.addConverterFactory(new StringToEnumConverterFactory());
        registry.addFormatterForFieldAnnotation(new BooleanFormatAnnotationFormatterFactory());
        WebMvcConfigurer.super.addFormatters(registry);
    }

    @Bean
    public HttpInterceptor httpInterceptor() {
        return new HttpInterceptor();
    }

    /**
     * 6.添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpInterceptor()).addPathPatterns("/mvc/**").excludePathPatterns("");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    /**
     * 7.添加静态处理
     * extends WebMvcConfigurationSupport 会使默认配置失效，需重写 addResourceHandlers
     * implements WebMvcConfigurer 则不需要，在 application.yml 配置即可
     * @link {http://localhost:3333/img/Event-Y.jpg}
     * <p>
     * webjars 默认映射规则：/webjars/** ==> classpath:/META-INF/resources/webjars/
     * 静态资源默认映射规则：/** ==> classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/
     * <p>
     * Springboot2 静态资源配置：https://www.cnblogs.com/xiaomaomao/p/14278402.html
     * SpringBoot2 静态资源访问问题：https://blog.csdn.net/afgasdg/article/details/106474734
     * Spring MVC 配置静态资源：https://www.cnblogs.com/zhangcaihua/p/12829083.html
     * Serve Static Resources with Spring：https://www.baeldung.com/spring-mvc-static-resources
     *
     * @see org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter#addResourceHandlers
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // addResourceHandler       设置资源映射路径（前缀）
        // addResourceLocations     设置资源位置
        registry.addResourceHandler("/webjars/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/META-INF/resources/webjars/");
        registry.addResourceHandler(staticPathPatterns).addResourceLocations(StringUtils.split(staticLocations, ","));
        registry.addResourceHandler("/swagger-ui/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/META-INF/resources/webjars/springfox-swagger-ui/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    /**
     * 8.添加跨域映射
     * https://www.cnblogs.com/520playboy/p/7306008.html
     * CORS with Spring：https://www.baeldung.com/spring-cors
     * <p>
     * 也可以使用 @CrossOrigin 针对单独的 Controller
     * `@CrossOrigin(origins = "http://192.168.1.123:8080", originPatterns = "*",
     * -    methods = {RequestMethod.GET, RequestMethod.POST}, allowCredentials = "false", maxAge = 3600)
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 设置允许的方法
                .allowedMethods("*")
                // 是否允许证书
                .allowCredentials(true)
                // 跨域允许时间
                .maxAge(3600);
        WebMvcConfigurer.super.addCorsMappings(registry);
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
     * 9.添加视图控制器
     * 常用于无业务逻辑的页面跳转，如：主页、URL 重定向、404 页面等
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        WebMvcConfigurer.super.addViewControllers(registry);
    }

    /**
     * 10.配置视图解析器
     * A Guide to the ViewResolver in Spring MVC：https://www.baeldung.com/spring-mvc-view-resolver-tutorial
     * Customize ViewResolvers：https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto.spring-mvc.customize-view-resolvers
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        WebMvcConfigurer.super.configureViewResolvers(registry);
    }

    @Bean
    public PersonArgumentResolver personArgumentResolver() {
        return new PersonArgumentResolver();
    }

    /**
     * 11.添加参数解析器
     * 要自定义参数解析的内置支持，请直接配置 RequestMappingHandlerAdapter
     * Binding Domain Objects：https://www.baeldung.com/spring-mvc-custom-data-binder#binding-a-hierarchy-of-objects
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(personArgumentResolver());
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
    }

    @Bean
    public MyHandlerMethodReturnValueHandler myHandlerMethodReturnValueHandler() {
        return new MyHandlerMethodReturnValueHandler();
    }

    /**
     * 12.添加返回值处理器
     * 要自定义处理返回值的内置支持，请直接配置 RequestMappingHandlerAdapter
     */
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(myHandlerMethodReturnValueHandler());
        WebMvcConfigurer.super.addReturnValueHandlers(handlers);
    }

    /**
     * 13.配置消息转换器
     * SpringMVC HttpMessageConverter 匹配规则：https://segmentfault.com/a/1190000012659486
     * 如何扩展 XML 格式的请求和响应：https://blog.didispace.com/spring-boot-learning-21-2-8/
     * Http Message Converters with the Spring Framework：https://www.baeldung.com/spring-httpmessageconverter-rest
     * Binary Data Formats in a Spring REST API：https://www.baeldung.com/spring-rest-api-with-binary-data-formats
     * Returning Image/Media Data with Spring MVC：https://www.baeldung.com/spring-mvc-image-media-data
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.configureMessageConverters(converters);
    }

    /**
     * 14.继承消息转换器
     * 扩展或修改默认配置的消息转换器列表
     * configureMessageConverters 和 configureMessageConverters：https://www.cnblogs.com/woyujiezhen/p/12105852.html
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.extendMessageConverters(converters);
    }

    /**
     * 15.配置处理异常解析器
     * 自定义异常处理 HandlerExceptionResolver：https://www.cnblogs.com/yihuihui/p/11673496.html
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        WebMvcConfigurer.super.configureHandlerExceptionResolvers(resolvers);
    }

    /**
     * 16.继承处理异常解析器
     * 扩展或修改默认配置的异常解析器列表
     */
    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        WebMvcConfigurer.super.extendHandlerExceptionResolvers(resolvers);
    }

    /**
     * 17.验证器
     */
    @Override
    public Validator getValidator() {
        return WebMvcConfigurer.super.getValidator();
    }

    /**
     * 18.消息代码解析器
     * 用于生成错误代码
     */
    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return WebMvcConfigurer.super.getMessageCodesResolver();
    }
}
