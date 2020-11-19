package springboot.config;

import l.demo.Demo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 * <p>
 * https://blog.csdn.net/xtj332/article/details/80595768
 *
 * @author ljh
 * created on 2019/8/21 16:19
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends Demo {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
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
                .contact(new Contact("ljh", "localhost:3333/swagger-ui.html", MY_EMAIL))
                .version("1.0")
                .build();
    }

}