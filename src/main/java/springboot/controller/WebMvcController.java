package springboot.controller;

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
public class WebMvcController {

    private final ApplicationContext applicationContext;

    @Autowired
    public WebMvcController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * configureContentNegotiation
     */
    @GetMapping("configureContentNegotiation")
    public Student configureContentNegotiation() {
        return new Student(1, "ljh");
    }

    /**
     * configureAsyncSupport
     *
     * @param resultType 1:asyncVoid 2:asyncResult
     */
    @GetMapping("configureAsyncSupport")
    public String configureAsyncSupport(int resultType) throws ExecutionException, InterruptedException {
        if (resultType == 1) {
            applicationContext.getBean(WebMvcController.class).asyncVoid();
        } else {
            Future<Student> asyncResult = applicationContext.getBean(WebMvcController.class).asyncResult();
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
     */
    @PostMapping("addFormatters")
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
    public Person addArgumentResolvers(Person person) {
        return person;
    }

}
