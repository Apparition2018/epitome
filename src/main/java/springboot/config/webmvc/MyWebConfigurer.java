//package springboot.config.webmvc;
//
//import org.springframework.util.ResourceUtils;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * MyWebConfigurer
// *
// * @author ljh
// * created on 2021/7/28 10:18
// */
//public class MyWebConfigurer implements WebMvcConfigurer {
//
//    /**
//     * 静态资源路径配置
//     * addResourceHandler:  设置访问路径前缀
//     * addResourceLocations:设置资源路径
//     * http://localhost:3333/static/static/img/Event-Y.jpg
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
//        registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/");
//        // Swagger3：http://localhost:3333/swagger-ui/index.html
//        registry.addResourceHandler("/swagger-ui/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/META-INF/resources/webjars/springfox-swagger-ui/");
////        super.addResourceHandlers(registry);
//    }
//
//
//}
