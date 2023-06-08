package spring.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static l.demo.Demo.list;

/**
 * JSTL
 *
 * @author ljh
 * @since 2023/6/8 3:04
 */
@Controller
@RequestMapping("jstl")
public class JstlController {

    /** <a href="http://localhost:3333/jstl/core">核心标签</a> */
    @GetMapping("core")
    public String core(HttpServletRequest request) {
        request.setAttribute("list", list);
        return "jstl/core";
    }

    /** <a href="http://localhost:3333/jstl/fmt">格式化标签</a> */
    @GetMapping("fmt")
    public String fmt() {
        return "jstl/fmt";
    }

    /** <a href="http://localhost:3333/jstl/xml">XML 标签</a> */
    @GetMapping("xml")
    public String xml() {
        return "jstl/xml";
    }

    /** <a href="http://localhost:3333/jstl/functions">JSTL 函数</a> */
    @GetMapping("fn")
    public String fn() {
        return "jstl/fn";
    }
}
