package jar.freemarker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

/**
 * <a href="http://blog.csdn.net/u012759397/article/details/54091895">SpringMVC 整合 Freemarker</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
@Controller
public class FreeMarkerController {

    @RequestMapping("helloFtl")
    public String HelloFtl(Model model) {
        model.addAttribute("username", "nishuibaichuan");
        return "helloFtl";
    }

    @RequestMapping("helloJsp")
    public String HelloJsp(Model model) {
        model.addAttribute("username", "nishuibaichuan");
        return "helloJsp";
    }

    @RequestMapping("helloTestFtl")
    public String helloTestFtl(Model model) {
        List<Employer> emps = Arrays.asList(new Employer(1, "小李", 22), new Employer(2, "八戒", 444),
                new Employer(3, "刘德华", 54));
        model.addAttribute("emps", emps);
        return "helloTestFtl";
    }
}
