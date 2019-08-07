package springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 解决跨域
 * 以下实例是全局配置，也可以针对对应的 controller 添加 @CrossOrigin
 * eg:  @CrossOrigin(origins = "http://192.168.1.97:8080", maxAge = 3600)
 *      @CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
 * <p>
 * https://www.cnblogs.com/520playboy/p/7306008.html
 */
@Configuration
public class CorsConfig extends WebMvcConfigurationSupport {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOrigins("*")
                //是否允许证书 不再默认开启
                .allowCredentials(true)
                //设置允许的方法
                .allowedMethods("*")
                //跨域允许时间
                .maxAge(3600);
    }

}