package springboot.controller;

import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Jsonp：https://www.cnblogs.com/dream0530/p/6179819.html
 * http://localhost:3333/front/other/jsonp/jsonp-demo3.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
@RestController
@RequestMapping("/jsonp")
@Api("Jsonp")
public class JsonpController {

    @GetMapping("/base/json.do")
    public void exchangeJson(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/plain");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            Map<String, String> map = new HashMap<>();
            map.put("result", "content");
            String json = new Gson().toJson(map);
            String jsonpCallback = request.getParameter("jsonpCallback");
            PrintWriter pw = response.getWriter();
            pw.println(jsonpCallback + "(" + json + ")");
            pw.flush();
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
