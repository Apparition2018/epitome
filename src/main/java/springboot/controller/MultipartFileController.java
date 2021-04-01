package springboot.controller;

import jar.apache.poi.ExcelUtils;
import l.demo.Demo;
import l.demo.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaTypeFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author ljh
 * created on 2019/8/8 19:39
 */
@Slf4j
@RestController
@RequestMapping("/multipart-file")
public class MultipartFileController extends Demo {

    @RequestMapping("/input")
    public String input(MultipartFile file) throws IOException {
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
    
    @RequestMapping("/input2")
    public void fileInput(MultipartFile file) {
        try {
            ExcelUtils<Person> excelUtils = new ExcelUtils<>(Person.class);
            Map<Integer, String> colNumAndFieldNameMap = new HashMap<>();
            colNumAndFieldNameMap.put(0, "id");
            colNumAndFieldNameMap.put(1, "name");
            colNumAndFieldNameMap.put(2, "age");
            List<Person> personList = excelUtils.excel2BeanList(file.getInputStream(), colNumAndFieldNameMap);
            System.out.println(personList);
        } catch (IllegalAccessException | InstantiationException | IOException | NoSuchMethodException | NoSuchFieldException | InvocationTargetException | ParseException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/download")
    @ResponseBody
    public void download(@RequestParam("filename") String filename, HttpServletResponse response) {
        URL classesUrl = Thread.currentThread().getContextClassLoader().getResource("");
        String classesPath = StringUtils.substringAfter(Objects.requireNonNull(classesUrl).toString(), "file:/");
        File template = new File(classesPath + "demo" + File.separator + filename + ".xlsx");

        try (InputStream inputStream = new FileInputStream(template);
             OutputStream outputStream = response.getOutputStream()) {
            response.setContentType(MediaTypeFactory.getMediaType(filename).toString());
            response.addHeader("Content-Disposition", "attachment;filename=download.xlsx");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
