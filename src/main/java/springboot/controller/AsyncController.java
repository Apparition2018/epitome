package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.service.AsyncService;

/**
 * AsyncController
 *
 * @author NL-PC001
 * created on 2019/12/23 9:00
 */
@RestController
@RequestMapping("/async")
public class AsyncController {
    
    private final AsyncService asyncService;

    @Autowired
    public AsyncController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping("test")
    public void test() {
        try {
            asyncService.test1();
            asyncService.test3();
        } catch (Exception e) {
            System.out.println(1);
            throw new RuntimeException();
        }
        System.out.println(2);
    }
    
}
