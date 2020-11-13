package springboot.controller;

import l.demo.Demo;
import l.demo.Person;
import l.demo.Person.Student;
import org.springframework.web.bind.annotation.*;

/**
 * DemoController
 *
 * @author ljh
 * created on 2020/11/13 8:52
 */
@RestController
@RequestMapping("/demo")
public class DemoController extends Demo {

    /**
     * http://localhost:3333/demo/get
     */
    @GetMapping("/get")
    public Student get(@RequestParam(value = "id", required = false) Integer id,
                       @RequestParam(value = "name", required = false) String name) {
        return new Student(id, name);
    }

    /**
     * http://localhost:3333/demo/post
     */
    @PostMapping("/post")
    public Student post(@RequestParam(value = "id", required = false) Integer id,
                        @RequestParam(value = "name", required = false) String name) {
        return new Student(id, name);
    }

    /**
     * http://localhost:3333/demo/post2
     */
    @PostMapping("/post2")
    public Student post2(Person person) {
        return new Student(person.getId(), person.getName());
    }

    /**
     * http://localhost:3333/demo/post3
     */
    @PostMapping("/post3")
    public Student post3(@RequestBody Person person) {
        return new Student(person.getId(), person.getName());
    }

    /**
     * http://localhost:3333/demo/path/{id}/{name}
     */
    @RequestMapping("/path/{id}/{name}")
    public Student path(@PathVariable("id") Integer id, @PathVariable("name") String name) {
        return new Student(id, name);
    }

}
