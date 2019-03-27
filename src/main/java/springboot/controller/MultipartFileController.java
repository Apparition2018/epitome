package springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/multipart-file")
public class MultipartFileController {

    @RequestMapping("/excel")
    public String excel(HttpServletRequest request, MultipartFile file) throws IOException {
        // 返回上传的文件是否为空
        if (!file.isEmpty()) {
            // 返回文件的内容类型
            log.info("~~> {}", file.getContentType());      // application/vnd.ms-excel
            // 返回文件的字节大小
            log.info("~~> {}", file.getSize());             // 18944
            log.info("~~> {}", file.getName());             // file
            log.info("~~> {}", file.getOriginalFilename()); // Yearly Plan.xls

            // 将接收到的文件传输到给定的目标文件
            file.transferTo(new File("C:\\Users\\234607\\git\\mavenTest\\src\\main\\java\\jar\\spring\\" + file.getOriginalFilename()));
            return "上传成功！";
        } else {
            return "上传失败！";
        }
    }

}
