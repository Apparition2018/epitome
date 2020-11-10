package knowledge.反射.proxy;

import l.demo.Demo;
import org.springframework.stereotype.Service;

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
    public void hello() {
        p("hello ...");
        sleep(500, TimeUnit.MILLISECONDS);
    }

    @Override
    public void goodbye() {
        p("goodbye ...");
        sleep(500, TimeUnit.MILLISECONDS);
    }
}

interface People {

    void hello();

    void goodbye();

}
