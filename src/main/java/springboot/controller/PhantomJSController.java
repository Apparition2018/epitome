package springboot.controller;

import jar.phantomjs.PhantomJS;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/phantomjs")
@RestController
public class PhantomJSController {

    @RequestMapping("/screenshot")
    public String screenshot() {
        return PhantomJS.screenshot();
    }

}
