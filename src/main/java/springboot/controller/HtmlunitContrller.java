package springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/htmlunit")
@RestController
public class HtmlunitContrller {

    @RequestMapping("/test")
    public String test() {
        return "相思,登鹳雀楼";
    }

}
