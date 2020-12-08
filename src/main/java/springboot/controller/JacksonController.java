package springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import l.demo.Person.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author ljh
 * created on 2019/8/8 19:39
 */
@Api("jackson")
@RestController
@RequestMapping("/jackson")
public class JacksonController {

    /**
     * http://localhost:3333/jackson/test
     */
    @ApiOperation(value = "测试")
    @GetMapping("test")
    public Student test() {
        Student student = new Student();
        student.setPassword("ABC");
        student.setName("Mary");
        student.setAge(20);
        student.setBirth(new Date());
        return student;
    }

}


