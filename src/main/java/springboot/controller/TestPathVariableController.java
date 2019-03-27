package springboot.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PathVariable
 * https://www.cnblogs.com/fangpengchengbupter/p/7823493.html
 */
@RestController
public class TestPathVariableController {

    @RequestMapping("/user/{username:[a-zA-Z00-9_]+}/blog/{blogId}")
    public String getUserBlog(@PathVariable String username, @PathVariable("blogId") int blogId) {
        return "user: " + username + ", blog-> " + blogId;
    }

}
