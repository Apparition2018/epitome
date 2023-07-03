package springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springboot.domain.master.Demo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <a href="http://localhost:3333/front/js/Web%20APIs/Fetch%20API/Fetch-API-demo.html">Fetch-API-demo.html</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
@Slf4j
@RestController
@RequestMapping("fetch")
@Tag(name = "Fetch")
public class FetchController {

    @PostMapping("string")
    @Operation(summary = "传递普通类型的数据，如 String")
    public String string(@RequestParam String data) {
        return data;
    }

    @PostMapping("json-clazz")
    @Operation(summary = "传递 Json 类型的数据，接收方为 Bean")
    public Demo jsonClazz(@RequestBody Demo demo) {
        return demo;
    }

    @PostMapping("json-map")
    @Operation(summary = "传递 Json 类型的数据，接收方为 Map")
    public Map<String, String> jsonMap(@RequestBody Map<String, String> map) {
        return map;
    }

    @PostMapping("upload-picture")
    @Operation(summary = "上传单个文件")
    public String uploadPicture(@RequestParam("file") MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                File picture = new File("temp.png");
                BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(picture.toPath()));
                bos.write(bytes);
                bos.close();
                return "success";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "fail";
    }

    @PostMapping("upload-pictures")
    @Operation(summary = "上传多个文件")
    public String uploadPictures(HttpServletRequest request) {
        try {
            List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
            MultipartFile file;
            for (int i = 0; i < files.size(); i++) {
                file = files.get(i);
                if (!file.isEmpty()) {
                    byte[] bytes = file.getBytes();
                    File picture = new File("temp" + i + ".png");
                    BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(picture.toPath()));
                    bos.write(bytes);
                    bos.close();
                }
            }
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "fail";
    }

    @PostMapping("cookie")
    @Operation(summary = "发送 Cookie")
    public String cookie(@CookieValue(value = "cny", required = false) String cny, HttpServletRequest request) {
        String rtnString = StringUtils.EMPTY;
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            log.info("没有 cookie");
        } else {
            for (Cookie cookie : cookies) {
                if ("cny".equals(cookie.getName()) && Objects.equals(cny, cookie.getValue())) {
                    log.info(cookie.getName() + ": " + cookie.getValue());
                    rtnString = cookie.getName() + ": " + cookie.getValue();
                }
            }
        }
        return rtnString;
    }
}
