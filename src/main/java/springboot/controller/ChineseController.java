package springboot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ljh
 * created on 2019/8/8 19:39
 */
@RestController
@RequestMapping("/chinese")
public class ChineseController {

    /**
     * http://localhost:3333/chinese/test
     * {
     *     "String": "中文"
     * }
     */
    @PostMapping("/test")
    public void test(@RequestBody String data) {
        System.out.println(data);
    }

}
