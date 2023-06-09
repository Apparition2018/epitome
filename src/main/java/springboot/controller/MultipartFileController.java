package springboot.controller;

import com.google.common.net.HttpHeaders;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jar.apache.poi.ExcelUtils;
import l.demo.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static l.demo.Demo.DEMO_DIR_NAME;
import static l.demo.Demo.UPLOAD_ABSOLUTE_PATH;

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

    /** <a href="http://localhost:3333/front/html/elements/内联文本语义/a-demo.html#downloadFile">download file</a> */
    @GetMapping("download/file")
    @Operation(summary = "下载文件")
    public void downloadFile(@RequestParam("filename") String filename, HttpServletResponse response) {
        URL classesUrl = Thread.currentThread().getContextClassLoader().getResource(StringUtils.EMPTY);
        String classesPath = StringUtils.substringAfter(Objects.requireNonNull(classesUrl).toString(), "file:/");
        File template = new File(classesPath + DEMO_DIR_NAME + File.separator + filename);

        try (InputStream inputStream = Files.newInputStream(template.toPath());
             OutputStream outputStream = response.getOutputStream()) {
            response.setContentType(MediaTypeFactory.getMediaType(filename).toString());
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="
                    // 防止文件名有中文时显示为下划线
                    + new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /** <a href="http://localhost:3333/front/html/elements/内联文本语义/a-demo.html#downloadExcel">download excel</a> */
    @GetMapping("download/excel")
    @Operation(summary = "下载 excel")
    public void downloadExcel(@RequestParam("filename") String filename, HttpServletResponse response) {
        URL classesUrl = Thread.currentThread().getContextClassLoader().getResource(StringUtils.EMPTY);
        String classesPath = StringUtils.substringAfter(Objects.requireNonNull(classesUrl).toString(), "file:/");
        File template = new File(classesPath + DEMO_DIR_NAME + File.separator + filename);

        try (Workbook workbook = new XSSFWorkbook(template);
             OutputStream outputStream = response.getOutputStream()) {
            Sheet sheet = workbook.getSheetAt(0);
            /* 第一行数据 */
            Row row = sheet.createRow(1);
            Cell cell = row.createCell(0);
            cell.setCellValue("1");
            cell = row.createCell(1);
            cell.setCellValue("zs");
            cell = row.createCell(2);
            cell.setCellValue("18");
            /* 第一行数据 */
            row = sheet.createRow(2);
            cell = row.createCell(0);
            cell.setCellValue("2");
            cell = row.createCell(1);
            cell.setCellValue("ls");
            cell = row.createCell(2);
            cell.setCellValue("22");

            response.setContentType(MediaTypeFactory.getMediaType(filename).toString());
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="
                    + new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            workbook.write(outputStream);
        } catch (IOException | InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <a href="http://localhost:3333/front/bootstrap/bootstrap-fileinput.html#file-input">bootstrap-fileinput</a>
     *
     * @see <a href="https://www.cnblogs.com/zgghb/p/6020581.html">bootstrap-fileInput 示例</a>
     */
    @PostMapping("upload/file")
    @Operation(summary = "上传文件")
    public Result<String> uploadFile(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        if (file != null && !file.isEmpty()) {
            log.info("ContentType: {}", file.getContentType());             // application/vnd.ms-excel
            log.info("Size: {}", file.getSize());                           // 18944
            log.info("Name: {}", file.getName());                           // file
            log.info("OriginalFilename: {}", file.getOriginalFilename());   // Yearly Plan.xls

            // 将接收到的文件传输到给定的目标文件
            file.transferTo(new File(UPLOAD_ABSOLUTE_PATH + file.getOriginalFilename()));
            return Result.success("success");
        } else {
            return Result.failure(ResultCode.PARAM_MISS, "上传文件不能为空");
        }
    }

    /** <a href="http://localhost:3333/front/html/elements/表单/form-demo.html#form1">upload excel</a> */
    @PostMapping("upload/excel")
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
            throw new RuntimeException(e);
        }
    }
}
