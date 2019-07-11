package springboot.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chinese")
public class ChineseController {

    @RequestMapping("/test")
    public void test(@RequestBody String data) {
        System.out.println(data);
    }

}
