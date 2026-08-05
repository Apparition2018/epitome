package knowledge.reflect.proxy.domain;

import l.demo.Demo;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * Man
 *
 * @author ljh
 * @since 2020/11/10 16:01
 */
@Service
public class Man extends Demo implements People {

    @SneakyThrows
    @Override
    @AOP
    public void work() {
        p("work ...");
        TimeUnit.MILLISECONDS.sleep(500);
    }

    @SneakyThrows
    @Override
    @AOP
    public void sleep() {
        p("sleep ...");
        TimeUnit.MILLISECONDS.sleep(100);
        throw new RuntimeException("nightmare ...");
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface AOP {
    }
}
