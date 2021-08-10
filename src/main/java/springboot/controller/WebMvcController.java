package springboot.controller;

import l.demo.Person.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author ljh
 * created on 2021/8/9 9:20
 */
@Slf4j
@RestController
@RequestMapping("mvc")
public class WebMvcController {

    private final ApplicationContext applicationContext;

    @Autowired
    public WebMvcController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @GetMapping("contentNegotiation")
    public Student contentNegotiation() {
        return new Student(1, "ljh");
    }

    /**
     * @param resultType 1:asyncVoid 2:asyncResult
     */
    @GetMapping("asyncSupport")
    public String asyncSupport(int resultType) throws ExecutionException, InterruptedException {
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
}
