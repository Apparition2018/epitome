package springboot.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * AsyncService
 *
 * @author NL-PC001
 * created on 2019/12/23 9:16
 */
@Service
public class AsyncService {

    @Async
    public void test1() {
        try {
            System.out.println("test1");
            test2();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void test2() {
        System.out.println("test2");
        throw new RuntimeException();
    }

    public void test3() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("test3");
    }
    
}
