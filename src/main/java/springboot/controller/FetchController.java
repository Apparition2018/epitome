package springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springboot.domain.master.Score;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author ljh
 * created on 2019/8/8 19:39
 * @link {http://localhost:3333/front/js/Web%20APIs/Fetch%20API/Fetch-API-demo.html}
 */
@Slf4j
@RestController
@RequestMapping("/fetch")
@Tag(name = "Fetch")
public class FetchController {

    @PostMapping("/string")
    @Operation(summary = "传递普通类型的数据，如 String")
    public String string(@RequestParam String data) {
        return data;
    }

    @PostMapping("/json-clazz")
    @Operation(summary = "传递 Json 类型的数据，接收方为 Bean")
    public Score jsonClazz(@RequestBody Score score) {
        return score;
    }

    @PostMapping("/json-map")
    @Operation(summary = "传递 Json 类型的数据，接收方为 Map")
    public Map<String, String> jsonMap(@RequestBody Map<String, String> map) {
        return map;
    }

    @PostMapping("/upload-picture")
    @Operation(summary = "上传单个文件")
    public String uploadPicture(@RequestParam("file") MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                File picture = new File("temp.png");
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(picture));
                bos.write(bytes);
                bos.close();
                return "success";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "fail";
    }

    @PostMapping("/upload-pictures")
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
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(picture));
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

    @PostMapping("/cookie")
    @Operation(summary = "发送 Cookie")
    public String cookie(@CookieValue(value = "cny", required = false) String cny, HttpServletRequest request) {
        String rtnString = "";
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