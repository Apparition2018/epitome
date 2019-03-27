package springboot.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

@RestController
@RequestMapping("/httpclient")
public class HttpClientController {

    @RequestMapping("/map")
    public String testMap(HttpServletRequest request) {

        String a = request.getParameter("a");
        String b = request.getParameter("b");

        return a + b;
    }

    @RequestMapping("/json")
    public String testJson(HttpServletRequest request) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        Map map = (Map) JSON.parse(sb.toString());

        return (String) map.get("a") + map.get("b");
    }

}
