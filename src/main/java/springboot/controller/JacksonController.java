package springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import l.demo.Demo;
import l.demo.Person.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/jackson")
@Api("jackson")
@Slf4j
public class JacksonController extends Demo {

    /**
     * http://localhost:3333/jackson/test
     */
    @GetMapping("test")
    @ApiOperation(value = "测试")
    public Student test() {
        Student student = new Student();
        student.setPassword("ABC");
        student.setName("Mary");
        student.setAge(20);
        student.setBirth(new Date());
        return student;
    }

}


