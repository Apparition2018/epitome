package springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/online")
public class OnlineController {

    @RequestMapping("/test")
    public String online(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Object count = session.getServletContext().getAttribute("count");
        return "count : " + count;
    }

}
