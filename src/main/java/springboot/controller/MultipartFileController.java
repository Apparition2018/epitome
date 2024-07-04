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
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.ss.extractor.EmbeddedData;
import org.apache.poi.ss.extractor.EmbeddedExtractor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.MediaTypeFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springboot.result.Result;
import springboot.result.ResultCode;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static l.demo.Demo.*;

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
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" +
                new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
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
    public Result<String> uploadFile(HttpServletRequest request) throws IOException, OpenXML4JException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        if (file != null && !file.isEmpty()) {
            System.err.printf("ContentType: %s%n", file.getContentType());
            System.err.printf("Size: %s%n", file.getSize());
            System.err.printf("Name: %s%n", file.getName());
            System.err.printf("OriginalFilename: %s%n", file.getOriginalFilename());

            try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
                // 提取器尝试识别 Excel 文件中的各种嵌入文档
                EmbeddedExtractor extractor = new EmbeddedExtractor();
                for (EmbeddedData embeddedData : extractor.extractAll(workbook.getSheetAt(0))) {
                    Shape shape = embeddedData.getShape();
                    XSSFClientAnchor anchor = (XSSFClientAnchor) shape.getAnchor();
                    String filename = new String(embeddedData.getFilename().getBytes(StandardCharsets.ISO_8859_1), Charset.forName("GBK"));
                    // 文件名：行-列
                    System.err.println(filename + "：" + anchor.getRow2() + "-" + anchor.getCol2());
                    byte[] bytes = embeddedData.getEmbeddedData();
                    // 判断是否为 ZIP（ZIP 文件的魔数是 0x50 0x4B 0x03 0x04）
                    if (bytes != null && bytes.length >= 4 && bytes[0] == 0x50 && bytes[1] == 0x4B && bytes[2] == 0x03 && bytes[3] == 0x04) {
                        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                             // 防止压缩包里的文件的文件名包含中文而报错
                             ZipInputStream zis = new ZipInputStream(bais, Charset.forName("GBK"))) {
                            ZipEntry zipEntry;
                            while ((zipEntry = zis.getNextEntry()) != null) {
                                // 包含文件夹路径的文件名
                                String fileName = zipEntry.getName();
                                boolean isFile = !fileName.endsWith("/");
                                if (isFile) {
                                    int separatorIndex = fileName.lastIndexOf('/');
                                    if (separatorIndex != -1) {
                                        // 不包含文件路径的文件名
                                        fileName = fileName.substring(separatorIndex + 1);
                                    }
                                    try (FileOutputStream fos = new FileOutputStream(DESKTOP + fileName)) {
                                        zis.transferTo(fos);
                                    } finally {
                                        zis.closeEntry();
                                    }
                                }
                            }
                        }
                    }
                }
            }

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
