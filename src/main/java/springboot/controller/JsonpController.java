package springboot.controller;

import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Jsonp 的两个参数 jsonp 和 jsonpCallback：https://www.cnblogs.com/yeminglong/archive/2013/06/24/3152976.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 * @link {http://localhost:3333/front/other/jsonp/jsonp-demo.html}
 */
@RestController
@RequestMapping("/jsonp")
@Tag(name = "Jsonp")
public class JsonpController {

    @GetMapping("/json")
    @Operation(summary = "获取回调函数")
    public String callbackJson(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        map.put("result", "Arsenal");
        String json = new Gson().toJson(map);
        String callback = request.getParameter("callback");
        return String.format("%s(%s)", callback, json);
    }
}
