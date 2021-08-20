package springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

import static l.demo.Demo.MY_EMAIL;
import static l.demo.Demo.MY_NAME;

/**
 * SwaggerConfig
 * 使用 Swagger2 构建强大的 API 文档：https://blog.didispace.com/spring-boot-learning-21-2-2/
 * Swagger2 与 Swagger3 的区别：https://blog.csdn.net/qq_42375133/article/details/115692716
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
                .apis(RequestHandlerSelectors.basePackage("springboot"))
                .paths(PathSelectors.any())
                .build();
    }

    // 构建 api 文档的详细信息函数
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("epitome")
                .description("epitome")
                .contact(new Contact(MY_NAME, "localhost:3333/swagger-ui/index.html", MY_EMAIL))
                .version("1.0.0")
                .build();
    }

}