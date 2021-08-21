package springboot.controller;

import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Jsonp 的两个参数 jsonp 和 jsonpCallback：https://www.cnblogs.com/yeminglong/archive/2013/06/24/3152976.html
 * @link {http://localhost:3333/front/other/jsonp/jsonp-demo.html}
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
@RestController
@RequestMapping("/jsonp")
@Api(tags = "Jsonp")
public class JsonpController {

    @GetMapping("/json")
    @ApiOperation("获取回调函数")
    public String callbackJson(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        map.put("result", "Arsenal");
        String json = new Gson().toJson(map);
        String callback = request.getParameter("callback");
        return String.format("%s(%s)", callback, json);
    }
}
