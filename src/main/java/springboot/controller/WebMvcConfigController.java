package springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import l.demo.CompanyEnum;
import l.demo.Person;
import l.demo.Person.Student;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.formatter.BooleanFormat;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static l.demo.Demo.MY_NAME;

/**
 * @author ljh
 * @since 2021/8/9 9:20
 */
@Slf4j
@RestController
@Tag(name = "WebMvcConfig")
public class WebMvcConfigController {

    private final ApplicationContext applicationContext;

    @Autowired
    public WebMvcConfigController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * configureContentNegotiation
     */
    @GetMapping("configureContentNegotiation")
    @Operation(summary = "配置内容协议", description = "根据 mediaType 参数来觉得返回值格式(xml, json)")
    public Student configureContentNegotiation() {
        return new Student(1, MY_NAME);
    }

    /**
     * configureAsyncSupport
     */
    @GetMapping("configureAsyncSupport")
    @Operation(summary = "配置异步")
    @Parameter(name = "asyncIsReturnValue", description = "异步是否返回值", required = true, in = ParameterIn.QUERY,
            schema = @Schema(type = "integer", defaultValue = "0"))
    public String configureAsyncSupport(Integer asyncIsReturnValue) throws ExecutionException, InterruptedException {
        if (asyncIsReturnValue == 0) {
            applicationContext.getBean(WebMvcConfigController.class).asyncVoid();
        } else {
            log.info("async1 start");
            Future<Integer> asyncResult1 = applicationContext.getBean(WebMvcConfigController.class).asyncResult(1);
            log.info("async2 start");
            Future<Integer> asyncResult2 = applicationContext.getBean(WebMvcConfigController.class).asyncResult(2);
            log.info("{}: {}", Thread.currentThread().getName(), asyncResult1.get());
            log.info("{}: {}", Thread.currentThread().getName(), asyncResult2.get());
        }
        return "OK";
    }

    @Async
    public void asyncVoid() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        log.info("{}: {}", Thread.currentThread().getName(), 1);
    }

    @Async
    public Future<Integer> asyncResult(Integer i) throws InterruptedException {
        TimeUnit.SECONDS.sleep(i);
        log.info("{}: {}", Thread.currentThread().getName(), i);
        return new AsyncResult<>(i + 2);
    }

    /**
     * addFormatters
     * 这里如果使用 @RequestBody 接收参数会使用 Jackson，不会使用配置的 Converter
     */
    @PostMapping("addFormatters")
    @Operation(summary = "添加格式化器", description = "枚举格式化器和注解格式化器")
    @Parameters({
            @Parameter(name = "name", description = "姓名", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "string")),
            @Parameter(name = "company", description = "公司代码", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "integer")),
            @Parameter(name = "isAdult", description = "是否成年", required = true, in = ParameterIn.QUERY, schema = @Schema(type = "string"))
    })
    public User addFormatters(User user) {
        return user;
    }

    @Data
    static class User {
        private String name;
        private CompanyEnum company;
        @BooleanFormat
        private String isAdult;
        private Date currentTime = new Date();
    }

    /**
     * addArgumentResolvers
     */
    @PostMapping("addArgumentResolvers")
    @Operation(summary = "添加参数解析器", description = "通过在 Headers 中传 id 生成参数 person")
    @Parameter(name = "id", description = "id", required = true, in = ParameterIn.HEADER, schema = @Schema(type = "integer", allowableValues = {"1", "2", "3"}))
    public Person addArgumentResolvers(Person person) {
        return person;
    }

}
