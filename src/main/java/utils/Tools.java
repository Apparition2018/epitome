package utils;

import jar.seleniumhq.selenium.PageUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

public class Tools {

    public Tools() {
        throw new Error("Don't instantiate " + getClass());
    }


    // 简写 System.out.println()
    public static <T> void p(T obj) {
        if (obj == null) return;
        if (obj.getClass().isArray()) {
            System.out.println(Arrays.toString((Object[]) obj));
            return;
        }
        System.out.println(obj);
    }

    // 判断操作系统是否为Windows
    public static boolean isWindows() {
        String os = System.getProperty("os.name");
        return os != null && os.toLowerCase().contains("windows");
    }

    // 生成UUID
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    // 获取IP地址
    public static String getHostAddress() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    // 16进制字符串 → 字节数组
    public static byte[] hex2Byte(String s) {
        byte[] src = s.toLowerCase().getBytes();
        byte[] ret = new byte[src.length / 2];
        for (int i = 0; i < src.length; i += 2) {
            byte hi = src[i];
            byte low = src[i + 1];
            hi = (byte) ((hi >= 'a' && hi <= 'f') ? 0x0a + (hi - 'a')
                    : hi - '0');
            low = (byte) ((low >= 'a' && low <= 'f') ? 0x0a + (low - 'a')
                    : low - '0');
            ret[i / 2] = (byte) (hi << 4 | low);
        }
        return ret;
    }

    // byte[] → 16进制字符串，通过位运算
    public static String bytes2Hex1(byte[] bytes) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F'};
        char[] out = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            byte c = bytes[i];
            out[i * 2] = Digit[(c >>> 4) & 0X0F];
            out[i * 2 + 1] = Digit[c & 0X0F];
        }

        return new String(out);
    }

    // byte[] → 16进制字符串，通过BigInteger
    public static String bytes2Hex2(byte[] bytes) {
        BigInteger bi = new BigInteger(1, bytes);
        return bi.toString(16);
    }

    // byte[] → 16进制字符串，通过...
    public static String bytes2Hex3(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            int temp = 0xff & aByte; // 此处为什么添加Oxff?， https://blog.csdn.net/iblade/article/details/73288822
            String hexStr = Integer.toHexString(temp);
            if (hexStr.length() == 1) { // 如果是十六进制的0f，默认只显示f，此时要补上0
                sb.append("0").append(hexStr);
            } else {
                sb.append(hexStr);
            }
        }
        return sb.toString();
    }

    // 使用selenium获取ECharts Base64码
    public static String getECharts(String url) {
        // 开启个浏览器并且输入链接
        WebDriver driver = PageUtils.getChromeDriver(url);
        // 最长等待10秒
        WebDriverWait wait = new WebDriverWait(driver, 10);
        // 等待id='base64'的元素出现
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("end")));
        // 得到id为base64的标签
        WebElement submitElement = driver.findElement(By.id("base64"));
        // 获取内容
        String rtn = submitElement.getAttribute("innerHTML");
        // 关闭浏览器 下面是关闭所有标签页，还有一个代码是 driver.close();, 关闭当前标签页
        driver.quit();

        return rtn;
    }

    // 使用phantomjs抓取JS动态生成的页面内容
    public static String getECharts2(String url) throws Exception {

        String pjsPath;
        String jsPath;
        String os = System.getProperty("os.name");
        if (os.toLowerCase().contains("windows")) {
            pjsPath = "C:\\Users\\234607\\IdeaProjects\\com.dayang.subscription\\src\\main\\webapp\\WEB-INF\\phantomjs\\bin\\phantomjs.exe";
            jsPath = "C:\\Users\\234607\\IdeaProjects\\com.dayang.subscription\\src\\main\\webapp\\WEB-INF\\phantomjs\\exec\\codes.js";
        } else {
            pjsPath = "/usr/local/phantomjs/bin/phantomjs";
            jsPath = "/usr/local/phantomjs/exec/codes.js";
        }
        Process p = Runtime.getRuntime().exec(pjsPath + " " + jsPath + " " + url);
        Thread.sleep(8000);
        InputStream is = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sbf = new StringBuilder();
        String tmp;
        while ((tmp = br.readLine()) != null) {
            sbf.append(tmp);
        }

        return sbf.toString();
    }



}

