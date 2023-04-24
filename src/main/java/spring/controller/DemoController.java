package spring.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static l.demo.Demo.personList;

/**
 * DemoController
 *
 * @author ljh
 * @since 2023/4/24 9:17
 */
@Controller
@RequestMapping("demo")
public class DemoController {

    @GetMapping
    public String test(HttpServletRequest request) {
        // 页面js获取后端传递的List参数
        request.setAttribute("personList", personList);
        return "demo";
    }
}
