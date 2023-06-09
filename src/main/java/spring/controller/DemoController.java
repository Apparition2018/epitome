package spring.controller;

import cn.hutool.json.JSONUtil;
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
    public String demo(HttpServletRequest request) {
        // Javascript 接收集合参数：http://localhost:3333/demo?name=javascriptReceiveCollection
        request.setAttribute("personList", personList);
        request.setAttribute("personJsonArray", JSONUtil.parseArray(JSONUtil.toJsonStr(personList)));
        return "demo";
    }
}
