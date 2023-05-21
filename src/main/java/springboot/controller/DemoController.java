package springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import l.demo.Person;
import l.demo.Person.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springboot.filter.HeaderMapFilter;

/**
 * DemoController
 *
 * @author ljh
 * @since 2020/11/13 8:52
 */
@Slf4j
@RestController
@RequestMapping("demo")
@Tag(name = "Demo")
public class DemoController {

    /** <a href="http://localhost:3333/demo/post">demo/post</a> */
    @PostMapping("post")
    @Operation(summary = "POST 请求，@RequestParam")
    public Student post(@RequestParam(value = "id", required = false) Integer id,
                        @RequestParam(value = "name", required = false) String name) {
        return new Student(id, name);
    }

    /** <a href="http://localhost:3333/demo/post2">demo/post2</a> ??? */
    @PostMapping("post2")
    @Operation(summary = "POST 请求，JavaBean")
    public Student post2(Person person) {
        return new Student(person.getId(), person.getName());
    }

    /** <a href="http://localhost:3333/demo/post3">demo/post3</a> */
    @PostMapping("post3")
    @Operation(summary = "POST 请求，@RequestBody")
    public Student post3(@RequestBody Person person) {
        return new Student(person.getId(), person.getName());
    }

    /**
     * <a href="https://www.cnblogs.com/fangpengchengbupter/p/7823493.html">@PathVariable</a>
     * <p><a href="http://localhost:3333/demo/path/1/John">demo/path/{id}/{name}</a>
     */
    @GetMapping("path/{id:[0-9]+}/{name}")
    @Operation(summary = "@PathVariable")
    public Student path(@PathVariable("id") Integer id, @PathVariable("name") String name) {
        return new Student(id, name);
    }

    /**
     * <a href="http://localhost:3333/header">demo/header</a><br/>
     * 添加 Cookie name=ljh，拦截器 {@link HeaderMapFilter} 把 Cookie 添加到了 Header
     */
    @GetMapping("header")
    @Operation(summary = "@RequestHeader")
    public Student path(@RequestHeader("name") String name, @CookieValue("name") String name2) {
        log.info("header name: {}, cookie name: {}", name, name2);
        return new Student(1, name);
    }
}
