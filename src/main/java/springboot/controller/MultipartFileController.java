package springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jar.apache.poi.ExcelUtils;
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static l.demo.Demo.DEMO_ABSOLUTE_PATH;

/**
 * <a href="http://doc.ruoyi.vip/ruoyi/document/htsc.html#%E4%B8%8A%E4%BC%A0%E4%B8%8B%E8%BD%BD">RuoYi 上传下载 (CommonController)</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
@Slf4j
@RestController
@RequestMapping("multipart-file")
@Tag(name = "MultipartFile")
public class MultipartFileController {

    /**
     * <a href="http://localhost:3333/front/html/elements/内联文本语义/a-demo.html">a-demo.html</a>
     */
    @GetMapping("file")
    @Operation(summary = "下载文件")
    public void downloadFile(@RequestParam("filename") String filename, HttpServletResponse response) {
        URL classesUrl = Thread.currentThread().getContextClassLoader().getResource("");
        String classesPath = StringUtils.substringAfter(Objects.requireNonNull(classesUrl).toString(), "file:/");
        File template = new File(classesPath + "demo" + File.separator + filename + ".xlsx");

        try (InputStream inputStream = Files.newInputStream(template.toPath());
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
     * <a href="http://localhost:3333/front/html/elements/表单/form/form-demo.html">form-demo.html</a>
     */
    @PostMapping("excel")
    @Operation(summary = "上传 excel")
    public void uploadExcel(@RequestPart MultipartFile[] files) {
        try {
            for (MultipartFile file : files) {
                ExcelUtils<Person> excelUtils = new ExcelUtils<>(Person.class);
                Map<Integer, String> colNumAndFieldNameMap = Map.of(0, "id", 1, "name", 2, "age");
                if (!file.isEmpty()) {
                    List<Person> personList = excelUtils.excel2BeanList(file.getInputStream(), colNumAndFieldNameMap);
                    System.out.println(personList);
                }
            }
        } catch (IllegalAccessException | InstantiationException | IOException | NoSuchMethodException |
                 NoSuchFieldException | InvocationTargetException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * <a href="https://www.cnblogs.com/zgghb/p/6020581.html">bootstrap-fileInput 示例</a>
     * <p>
     * <a href="http://localhost:3333/front/bootstrap/fileinput/bootstrap-fileinput.html">bootstrap-fileinput.html</a>
     */
    @PostMapping("file")
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
