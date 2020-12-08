package springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static l.demo.Demo.MY_EMAIL;

/**
 * SwaggerConfig
 * Swagger2：https://blog.csdn.net/xtj332/article/details/80595768
 * Swagger3：http://www.yihaomen.com/article/1831.html
 *
 * @author ljh
 * created on 2019/8/21 16:19
 */
@EnableOpenApi
@Configuration
public class Swagger3Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    // 构建 api 文档的详细信息函数
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("epitome")
                .description("epitome")
                .contact(new Contact("ljh", null, MY_EMAIL))
                .version("1.0")
                .build();
    }

}