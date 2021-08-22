package springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import l.demo.Person;
import l.demo.Person.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * DemoController
 *
 * @author ljh
 * created on 2020/11/13 8:52
 */
@Slf4j
@RestController
@RequestMapping("/demo")
@Api(tags = "Demo")
public class DemoController {

    /**
     * http://localhost:3333/demo/get
     */
    @GetMapping("/get")
    @ApiOperation("GET 请求")
    public Student get(@RequestParam(value = "id", required = false) Integer id,
                       @RequestParam(value = "name", required = false) String name) {
        return new Student(id, name).setBirth(new Date());
    }

    /**
     * http://localhost:3333/demo/post
     */
    @PostMapping("/post")
    @ApiOperation("POST 请求，@RequestParam")
    public Student post(@RequestParam(value = "id", required = false) Integer id,
                        @RequestParam(value = "name", required = false) String name) {
        return new Student(id, name);
    }

    /**
     * http://localhost:3333/demo/post2
     */
    @PostMapping("/post2")
    @ApiOperation("POST 请求，JavaBean")
    public Student post2(Person person) {
        return new Student(person.getId(), person.getName());
    }

    /**
     * http://localhost:3333/demo/post3
     */
    @PostMapping("/post3")
    @ApiOperation("POST 请求，@RequestBody")
    public Student post3(@RequestBody Person person) {
        return new Student(person.getId(), person.getName());
    }

    /**
     * http://localhost:3333/demo/path/{id}/{name}
     * `@PathVariable：https://www.cnblogs.com/fangpengchengbupter/p/7823493.html
     */
    @GetMapping("/path/{id:[0-9]+}/{name}")
    @ApiOperation("@PathVariable")
    public Student path(@PathVariable("id") Integer id, @PathVariable("name") String name) {
        log.info("path 参数：id-{}，name-{}", id, name);
        return new Student(id, name);
    }

}
