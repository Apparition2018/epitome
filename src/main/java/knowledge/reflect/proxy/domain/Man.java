package knowledge.reflect.proxy.domain;

import l.demo.Demo;
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
 * created on 2020/11/10 16:01
 */
@Service
public class Man extends Demo implements People {

    @Override
    @AOP
    public void work() {
        p("work ...");
        sleep(500, TimeUnit.MILLISECONDS);
    }

    @Override
    @AOP
    public void sleep() {
        p("sleep ...");
        sleep(100, TimeUnit.MILLISECONDS);
        throw new RuntimeException("nightmare ...");
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface AOP {

    }
}
