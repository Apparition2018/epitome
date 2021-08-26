package springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static l.demo.Demo.MY_EMAIL;
import static l.demo.Demo.MY_NAME;

/**
 * SwaggerConfig
 * 使用 Swagger2 构建强大的 API 文档：https://blog.didispace.com/spring-boot-learning-21-2-2/
 * Swagger 接口分类与各元素排序问题详解：https://blog.didispace.com/spring-boot-learning-21-2-4/
 * springfox2 与 springfox3 的区别：https://blog.csdn.net/qq_42375133/article/details/115692716
 * Swagger3 (Open API 3)：https://blog.csdn.net/qq_35425070/article/details/105347336
 *
 * @author ljh
 * created on 2019/8/21 16:19
 */
@Profile("druid")
@EnableOpenApi
@Configuration
public class Swagger2Config {

    @Bean
    public Docket createDocket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("springboot"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("epitome")
                .description("epitome")
                .contact(new Contact(MY_NAME, "http://localhost:3333/swagger-ui/", MY_EMAIL))
                .version("1.0.0")
                .build();
    }

}