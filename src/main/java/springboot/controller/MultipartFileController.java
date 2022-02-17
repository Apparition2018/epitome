package springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jar.apache.poi.ExcelUtils;
import l.demo.Demo;
import l.demo.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaTypeFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springboot.result.Result;
import springboot.result.ResultCode;

import javax.servlet.http.HttpServletRequest;
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
 * RuoYi 上传下载 (CommonController)：http://doc.ruoyi.vip/ruoyi/document/htsc.html#%E4%B8%8A%E4%BC%A0%E4%B8%8B%E8%BD%BD
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
@Slf4j
@RestController
@RequestMapping("/multipart-file")
@Tag(name = "MultipartFile")
public class MultipartFileController extends Demo {

    /**
     * @link {http://localhost:3333/front/html/elements/a/a-demo.html}
     */
    @GetMapping("file")
    @Operation(summary = "下载文件")
    public void downloadFile(@RequestParam("filename") String filename, HttpServletResponse response) {
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

    /**
     * @link {http://localhost:3333/front/html/elements/%E8%A1%A8%E5%8D%95/form/form-demo.html}
     */
    @PostMapping("/excel")
    @Operation(summary = "上传 excel")
    public void uploadExcel(@RequestPart MultipartFile[] files) {
        try {
            for (MultipartFile file : files) {
                ExcelUtils<Person> excelUtils = new ExcelUtils<>(Person.class);
                Map<Integer, String> colNumAndFieldNameMap = new HashMap<>();
                colNumAndFieldNameMap.put(0, "id");
                colNumAndFieldNameMap.put(1, "name");
                colNumAndFieldNameMap.put(2, "age");
                if (!file.isEmpty()) {
                    List<Person> personList = excelUtils.excel2BeanList(file.getInputStream(), colNumAndFieldNameMap);
                    System.out.println(personList);
                }
            }
        } catch (IllegalAccessException | InstantiationException | IOException | NoSuchMethodException | NoSuchFieldException | InvocationTargetException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Java + bootstrap-fileInput 示例：https://www.cnblogs.com/zgghb/p/6020581.html
     *
     * @link {http://localhost:3333/front/bootstrap/fileinput/bootstrap-fileinput.html}
     */
    @PostMapping("/file")
    @Operation(summary = "上传文件")
    public Result<String> uploadFile(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file-input");
        if (file != null && !file.isEmpty()) {
            log.info("ContentType: {}", file.getContentType());             // application/vnd.ms-excel
            log.info("Size: {}", file.getSize());                           // 18944
            log.info("Name: {}", file.getName());                           // file
            log.info("OriginalFilename: {}", file.getOriginalFilename());   // Yearly Plan.xls

            // 将接收到的文件传输到给定的目标文件
            file.transferTo(new File(DEMO_ABSOLUTE_PATH + file.getOriginalFilename()));
            return Result.success("success");
        } else {
            return Result.failure(ResultCode.PARAM_MISS, "上传文件不能为空");
        }
    }
}
