package springboot.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jetbrains.annotations.NotNull;
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
import springboot.util.SpringUtils;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <a href="https://blog.csdn.net/lvyuanj/article/details/108554170">WebMvcConfigurer vs WebMvcConfigurationSupport</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Value("${spring.mvc.static-path-pattern}")
    private String staticPathPatterns;

    @Value("${spring.web.resources.static-locations}")
    private String staticLocations;

    /**
     * 1.配置 HandlerMapping 路径匹配
     *
     * @see <a href="https://www.baeldung.com/spring-mvc-matrix-variables">A Quick Guide to Spring MVC Matrix Variables</a>
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 给匹配的 Controller 增加前缀
        configurer.addPathPrefix("mvc", p -> p.isInstance(SpringUtils.getBean(WebMvcConfigController.class)));
        configurer.setUseTrailingSlashMatch(true);
        WebMvcConfigurer.super.configurePathMatch(configurer);
    }

    /**
     * 2.配置内容协议
     * <pre>
     * 1 URL suffixes：根据 URL 后缀返回内容格式，5.2.4 弃用
     * 2 URL parameter：根据 URL 请求参数返回内容格式
     * 3 Accept header
     * 4 produces：@RequestMapping(produces)
     * </pre>
     *
     * @see <a href="https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.developing-web-applications.spring-mvc.content-negotiation">Path Matching and Content Negotiation</a>
     * @see <a href="https://www.baeldung.com/spring-mvc-content-negotiation-json-xml">Spring MVC Content Negotiation</a>
     * @see <a href="https://blog.csdn.net/f641385712/article/details/100020664">ContentNegotiation 内容协商机制(一) --- Spring MVC 内置支持的4种内容协商方式</a>
     * @see <a href="https://blog.csdn.net/f641385712/article/details/100060445">ContentNegotiation 内容协商机制(二) --- Spring MVC 内容协商实现原理及自定义配置</a>
     * @see <a href="https://blog.csdn.net/f641385712/article/details/100079806">ContentNegotiation 内容协商机制(三) --- 在视图 View 上的应用：ContentNegotiatingViewResolver 深度解析</a>
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // https://www.baeldung.com/spring-mvc-content-negotiation-json-xml#basics-4
        configurer.defaultContentType(MediaType.APPLICATION_JSON)
                .ignoreAcceptHeader(true)
                .favorParameter(true)
                .parameterName("mediaType")
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("json", MediaType.APPLICATION_JSON);
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
     * 3.配置异步请求
     * <pre>
     * 1 @Async：<a href="https://blog.csdn.net/weixin_42054155/article/details/106579769">@Async 无效原因</a>
     * 2 Runnable/Callable
     * 3 WebAsyncTask
     * 4 DeferredResult
     * </pre>
     *
     * @see <a href="https://www.baeldung.com/java-asynchronous-programming">Asynchronous Programming in Java</a>
     * @see <a href="https://mp.weixin.qq.com/s/Vqj7L9hQL9b11LEdDWp-HQ">异步请求和异步调用有区别</a>
     * @see <a href="https://hello.blog.csdn.net/article/details/113924477">SpringBoot 的四种异步处理</a>
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(asyncTaskExecutor());
        // Callable 超时时间
        configurer.setDefaultTimeout(DateUtils.MILLIS_PER_SECOND * 30);
        WebMvcConfigurer.super.configureAsyncSupport(configurer);
    }

    /**
     * 4.配置默认 Servlet 处理器
     * <p>常见用于 DispatcherServlet 被映射到 "/"，从而覆盖 Servlet 容器对静态资源的默认处理
     */
    @Override
    public void configureDefaultServletHandling(@NotNull DefaultServletHandlerConfigurer configurer) {
        WebMvcConfigurer.super.configureDefaultServletHandling(configurer);
    }

    /**
     * 5.添加转换器和格式化器
     *
     * @see <a href="https://xkcoding.com/2019/01/30/spring-boot-request-use-enums-params.html">使用枚举作为请求参数</a>
     * @see <a href="https://www.cnblogs.com/eclipse-/p/10998269.html">自定义注解转换请求参数</a>
     * @see <a href="https://www.baeldung.com/spring-mvc-custom-data-binder">A Custom Data Binder in Spring MVC</a>
     * @see <a href="https://www.baeldung.com/spring-type-conversions">Guide to Spring Type Conversions</a>
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
     * 6.添加 SpringMVC 生命周期拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpInterceptor()).addPathPatterns("/mvc/**").excludePathPatterns("");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    /**
     * 7.添加静态资源处理器
     * <pre>
     * 1 extends WebMvcConfigurationSupport 会使默认配置失效，需重写 addResourceHandlers
     * 2 implements WebMvcConfigurer 则不需要，在 application.yml 配置即可
     * </pre>
     * 配置后尝试访问：<a href="http://localhost:3333/public/img/Event-Y.jpg">Event-Y.jpg</a>
     * <p>映射规则：
     * <pre>
     * 1 webjars 默认映射规则：/webjars/** ==> classpath:/META-INF/resources/webjars/
     * 2 静态资源默认映射规则：/** ==> classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/
     * </pre>
     *
     * @see <a href="https://www.baeldung.com/spring-mvc-static-resources">Serve Static Resources with Spring</a>
     * @see <a href="https://blog.csdn.net/afgasdg/article/details/106474734">SpringBoot2 静态资源访问问题</a>
     * @see <a href="https://www.cnblogs.com/zhangcaihua/p/12829083.html">Spring MVC 配置静态资源</a>
     * @see org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter#addResourceHandlers
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // addResourceHandler       设置资源映射路径（前缀）
        // addResourceLocations     设置资源位置
        registry.addResourceHandler("/webjars/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/META-INF/resources/webjars/");
        registry.addResourceHandler(staticPathPatterns).addResourceLocations(StringUtils.split(staticLocations, ","));
        // registry.addResourceHandler("/swagger-ui/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/META-INF/resources/webjars/springfox-swagger-ui/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    /**
     * 8.配置全局跨域请求处理
     * <p>也可以使用 @CrossOrigin 针对单独的 Controller
     * <pre>
     * &#064;CrossOrigin(origins = "http://192.168.1.123:8080",
     *      originPatterns = "*",
     *      methods = {RequestMethod.GET, RequestMethod.POST},
     *      allowCredentials = "false", maxAge = 3600)
     * </pre>
     *
     * @see <a href="https://www.cnblogs.com/520playboy/p/7306008.html">SpringBoot 种通过 cors 协议解决跨域问题</a>
     * @see <a href="https://www.baeldung.com/spring-cors">CORS with Spring</a>
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

    /**
     * 9.添加简单的自动控制器
     * <p>常用于无业务逻辑的页面跳转，如：主页、URL 重定向、404 页面等
     * <p>下面代码相当于：
     * <pre>
     * &#064;Controller
     * public class IndexController {
     *      &#064;GetMapping("index")
     *      public String index() {
     *          return "index";
     *      }
     * }
     * </pre>
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        WebMvcConfigurer.super.addViewControllers(registry);
    }

    /**
     * 10.配置视图解析器
     *
     * @see <a href="https://www.baeldung.com/spring-mvc-view-resolver-tutorial">A Guide to the ViewResolver in Spring MVC</a>
     * @see <a href="https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto.spring-mvc.customize-view-resolvers">Customize ViewResolvers</a>
     */
    @Override
    public void configureViewResolvers(@NotNull ViewResolverRegistry registry) {
        WebMvcConfigurer.super.configureViewResolvers(registry);
    }

    @Bean
    public PersonArgumentResolver personArgumentResolver() {
        return new PersonArgumentResolver();
    }

    /**
     * 11.添加参数解析器
     * <p>要自定义参数解析的内置支持，请直接配置 RequestMappingHandlerAdapter
     *
     * @see <a href="https://www.baeldung.com/spring-mvc-custom-data-binder#binding-a-hierarchy-of-objects">Binding Domain Objects</a>
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
     * <p>要自定义处理返回值的内置支持，请直接配置 RequestMappingHandlerAdapter
     */
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(myHandlerMethodReturnValueHandler());
        WebMvcConfigurer.super.addReturnValueHandlers(handlers);
    }

    /**
     * 13.配置消息转换器
     *
     * @see <a href="https://www.baeldung.com/spring-httpmessageconverter-rest">Http Message Converters with the Spring Framework</a>
     * @see <a href="https://www.baeldung.com/spring-rest-api-with-binary-data-formats">Binary Data Formats in a Spring REST API</a>
     * @see <a href="https://www.baeldung.com/spring-mvc-image-media-data">Returning Image/Media Data with Spring MVC</a>
     * @see <a href="https://segmentfault.com/a/1190000012659486">SpringMVC HttpMessageConverter 匹配规则</a>
     * @see <a href="https://blog.didispace.com/spring-boot-learning-21-2-8/">SpringBoot 如何扩展 XML 格式的请求和响应</a>
     */
    @Override
    public void configureMessageConverters(@NotNull List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.configureMessageConverters(converters);
    }

    /**
     * 14.扩展或修改消息转换器
     *
     * @see <a href="https://www.cnblogs.com/woyujiezhen/p/12105852.html">configureMessageConverters vs extendMessageConverters</a>
     */
    @Override
    public void extendMessageConverters(@NotNull List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.extendMessageConverters(converters);
    }

    /**
     * 15.配置处理异常解析器
     *
     * @see <a href="https://www.cnblogs.com/yihuihui/p/11673496.html">自定义异常处理 HandlerExceptionResolver</a>
     */
    @Override
    public void configureHandlerExceptionResolvers(@NotNull List<HandlerExceptionResolver> resolvers) {
        WebMvcConfigurer.super.configureHandlerExceptionResolvers(resolvers);
    }

    /**
     * 16.扩展或修改处理异常解析器
     */
    @Override
    public void extendHandlerExceptionResolvers(@NotNull List<HandlerExceptionResolver> resolvers) {
        WebMvcConfigurer.super.extendHandlerExceptionResolvers(resolvers);
    }

    /**
     * 17.自定义 Validator
     */
    @Override
    public Validator getValidator() {
        return WebMvcConfigurer.super.getValidator();
    }

    /**
     * 18.自定义 MessageCodesResolver
     * <p>用于从数据绑定和验证错误代码构建消息代码
     */
    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return WebMvcConfigurer.super.getMessageCodesResolver();
    }
}
