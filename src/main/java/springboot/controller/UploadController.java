package springboot.controller;

import l.demo.Demo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author ljh
 * created on 2019/8/8 19:39
 */
@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController extends Demo {

    @RequestMapping("/input")
    public String excel(MultipartFile file) throws IOException {
        // 返回上传的文件是否为空
        if (!file.isEmpty()) {
            // 返回文件的内容类型
            log.info("ContentType: {}", file.getContentType());             // application/vnd.ms-excel
            // 返回文件的字节大小
            log.info("Size: {}", file.getSize());                           // 18944
            log.info("Name: {}", file.getName());                           // file
            log.info("OriginalFilename: {}", file.getOriginalFilename());   // Yearly Plan.xls

            // 将接收到的文件传输到给定的目标文件
            file.transferTo(new File(DEMO_ABSOLUTE_PATH + file.getOriginalFilename()));
            return "上传成功！";
        } else {
            return "上传失败！";
        }
    }

}
