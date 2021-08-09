package springboot.controller;

import l.demo.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ljh
 * created on 2021/8/9 9:20
 */
@RestController
@RequestMapping("mvc")
public class WebMvcController {

    @GetMapping("contentNegotiation")
    public Person.Student contentNegotiation() {
        return new Person.Student(1, "ljh");
    }

    @GetMapping("asyncSupport")
    public void asyncSupport() {
//        return new Person.Student(1, "ljh");
    }

}
