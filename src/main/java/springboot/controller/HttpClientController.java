package springboot.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * @author ljh
 * created on 2019/8/8 19:39
 */
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
    @SuppressWarnings("unchecked")
    public String testJson(HttpServletRequest request) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String line;
        StringBuilder sb = new StringBuilder();
        while (null != (line = br.readLine())) {
            sb.append(line);
        }

        HashMap<String, String> map = (HashMap<String, String>) JSON.parse(sb.toString());

        return map.get("a") + map.get("b");
    }

}
