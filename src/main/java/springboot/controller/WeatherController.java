package springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

/**
 * jquery-pjax 和 freemarker 的使用
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
@Slf4j
@Controller
@RequestMapping("/weather")
@Api(tags = "jQuery-pjax")
public class WeatherController {

    @GetMapping(value = "/{city}")
    @ApiOperation("获取城市天气")
    public String index(@PathVariable String city, HttpServletRequest req, Model model) {
        // pjax 请求
        if (req.getHeader("X-PJAX") != null) {
            log.info("pjax request");
            return String.format("forward:/weather/pjax/%s", city);
        }
        // 普通请求
        log.info("normal request");
        model.addAttribute("weather", getCityWeather(city));
        model.addAttribute("city", city);
        return "weather";
    }

    @GetMapping(value = "/pjax/{city}")
    @ResponseBody
    @ApiOperation("获取城市天气(pjax)")
    public String testPjax(@PathVariable String city) {
        return getCityWeather(city);
    }

    private static final String WEATHER_API = "http://wthrcdn.etouch.cn/weather_mini?city=%s";
    private static final String TEXTAREA = "<textarea>%s</textarea>";
    private static final String ERROR_MSG = "网络异常";

    /**
     * 获取城市天气
     */
    private static String getCityWeather(String city) {
        Request request = new Request.Builder().url(String.format(WEATHER_API, city)).build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            JSONObject jsonObject = JSON.parseObject(Objects.requireNonNull(response.body()).string()).getJSONObject("data");
            return String.format(TEXTAREA, JSON.toJSONString(jsonObject, true));
        } catch (IOException e) {
            log.error(e.getMessage());
            return ERROR_MSG;
        }
    }
}
