package springboot.controller;

import jar.apache.poi.ExcelUtils;
import l.demo.Demo;
import l.demo.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
