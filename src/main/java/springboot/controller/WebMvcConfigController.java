package springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

/**
 * @author ljh
 * created on 2021/8/9 9:20
 */
@Slf4j
@RestController
@Api(tags = "WebMvcConfig")
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
    @ApiOperation(value = "配置内容协议", notes = "根据 mediaType 参数来觉得返回值格式(xml, json)")
    public Student configureContentNegotiation() {
        return new Student(1, "ljh");
    }

    /**
     * configureAsyncSupport
     */
    @GetMapping("configureAsyncSupport")
    @ApiOperation("配置异步")
    @ApiImplicitParam(name = "isAsyncReturnValue", value = "是否异步返回值", required = true, paramType = "query", dataType = "Integer", dataTypeClass = Integer.class, defaultValue = "1")
    public String configureAsyncSupport(int isAsyncReturnValue) throws ExecutionException, InterruptedException {
        if (isAsyncReturnValue == 0) {
            applicationContext.getBean(WebMvcConfigController.class).asyncVoid();
        } else {
            Future<Student> asyncResult = applicationContext.getBean(WebMvcConfigController.class).asyncResult();
            log.info("{}: {}", Thread.currentThread().getName(), asyncResult.get());
        }
        return "OK";
    }

    @Async
    public void asyncVoid() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        log.info("{}: {}", Thread.currentThread().getName(), new Student(1, "ljh"));
    }

    @Async
    public Future<Student> asyncResult() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        log.info("{}: {}", Thread.currentThread().getName(), new Student(1, "ljh"));
        return new AsyncResult<>(new Student(1, "ljh"));
    }

    /**
     * addFormatters
     * 这里如果使用 @RequestBody 接收参数会使用 Jackson，不会使用配置的 Converter
     */
    @PostMapping("addFormatters")
    @ApiOperation(value = "添加格式化器", notes = "枚举格式化器和注解格式化器")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", required = true, paramType = "query", dataTypeClass = String.class),
            @ApiImplicitParam(name = "company", value = "公司代码", required = true, paramType = "query", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "isAdult", value = "是否成年", required = true, paramType = "query", dataTypeClass = String.class)
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
    @ApiOperation(value = "添加参数解析器", notes = "通过在 Headers 中传 id 生成参数 person")
    @ApiImplicitParam(name = "id", value = "id", allowableValues = "1,2,3", required = true, paramType = "header", dataTypeClass = Integer.class)
    public Person addArgumentResolvers(Person person) {
        return person;
    }

}
