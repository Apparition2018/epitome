package springboot.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springboot.domain.demo.Demo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fetch")
public class FetchController {

    @RequestMapping("/string")
    public String string(@RequestParam String data) {
        return data;
    }

    @RequestMapping("/json-clazz")
    public Demo jsonClazz(@RequestBody Demo demo) {
        return demo;
    }

    @RequestMapping("/json-map")
    public Map<String, String> jsonMap(@RequestBody Map<String, String> map) {
        return map;
    }

    @RequestMapping("/upload-picture")
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

    @RequestMapping("/upload-pictures")
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

    @RequestMapping("/cookie")
    public String cookie(@CookieValue(value = "cny", required = false) String cny, HttpServletRequest request) {

        String rtnString = "";

        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            System.out.println("没有cookie=========");
        } else {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                System.out.println(cookie.getValue());
                if ("cny".equals(cookie.getName()) && cny.equals(cookie.getValue())) {
                    rtnString = "cny: " + cookie.getValue();
                }
            }

        }

        return rtnString;

    }
}