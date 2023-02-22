package springboot.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
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

import java.io.IOException;
import java.util.Objects;

/**
 * jquery-pjax 和 freemarker 的使用
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
@Slf4j
@Controller
@RequestMapping("weather")
@Tag(name = "jQuery-pjax")
public class WeatherController {

    private final ObjectMapper objectMapper;

    public WeatherController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping("{city}")
    @Operation(summary = "获取城市天气")
    public String city(@PathVariable String city, HttpServletRequest request, Model model) {
        // pjax 请求
        if (request.getHeader("X-PJAX") != null) {
            log.info("pjax request");
            return String.format("forward:/weather/json/%s", city);
        }
        // 普通请求
        log.info("normal request");
        model.addAttribute("weather", getCityWeather(city));
        model.addAttribute("city", city);
        return "weather";
    }

    @GetMapping("json/{city}")
    @ResponseBody
    @Operation(summary = "获取城市天气(pjax)")
    public String cityJson(@PathVariable String city) {
        return this.getCityWeather(city);
    }

    private static final String WEATHER_API = "http://wthrcdn.etouch.cn/weather_mini?city=%s";
    private static final String TEXTAREA = "<textarea>%s</textarea>";

    /**
     * 获取城市天气
     */
    private String getCityWeather(String city) {
        Request request = new Request.Builder().url(String.format(WEATHER_API, city)).build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            JsonNode jsonNode = objectMapper.readTree(Objects.requireNonNull(response.body()).string()).get("data");
            return String.format(TEXTAREA, jsonNode.toPrettyString());
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
