package jar.phantomjs;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class PhantomJS {

    @SneakyThrows
    public static String screenshot() {
        String pjsPath = "C:\\Users\\234607\\git\\mavenTest\\src\\main\\resources\\phantomjs\\bin\\phantomjs.exe";  // PhantomJS 程序路径
        String jsPath = "C:\\Users\\234607\\git\\mavenTest\\src\\main\\resources\\phantomjs\\exec\\screenshot.js";  // JavaScript 路径
        String url = "http://localhost:3333/html/phantomjs/phantomjs.html";                                         // 网页路径
        String imgPath = "C:\\Users\\234607\\git\\mavenTest\\src\\main\\java\\jar\\phantomjs\\screenshot.png";      // 截图输出路径

        // 获取Runtime运行时，并执行命令
        Runtime.getRuntime().exec(pjsPath + " " + jsPath + " " + url + " " + imgPath);

        File file = new File(imgPath);

        // 每5秒检测一次是否截图成功
        int count = 10;
        do {
            log.info("~~> {}", count);
            if (--count < 0) {
                throw new RuntimeException("截图失败");
            }
            for (int i = 5; i > 0; i--) {
                Thread.sleep(1000);
            }
        } while (!file.exists());

        return "截图成功！";
    }

}
