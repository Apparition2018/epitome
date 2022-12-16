package spring.servlet.captcha;

import com.google.code.kaptcha.Constants;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * CaptchaUtils
 *
 * @author ljh
 * @since 2019/12/23 23:08
 */
public abstract class CaptchaUtils {

    private static Random random = new Random();
    private static int width = 80;      // 宽度
    private static int height = 30;     // 高度
    private static String[] fonts = {"Consolas", "Arial", "Algerian"};
    private static int fontSize = 18;   // 字体大小
    private static String randoms = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String operators = "+-";
    private static int result = -1;     // 保存计算结果
    private static int line = 2;                // 干扰线数量
    private static double noiseRate = 0.03D;    // 噪点率

    /**
     * 前后分离获取验证码方式
     */
    public static String createCaptcha() throws IOException {
        // 生成随机 key，作为 redis key，用于后期验证码验证
        String key = UUID.randomUUID().toString();
        // 生成验证码画布
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // ***************
        // * 画图过程省略 *
        // ***************
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 将图片按照指定的格式（jpeg）画到流上
        ImageIO.write(bufferedImage, "jpg", baos);
        // 将流转换成 base64 字符串
        return "data:image/jpg;base64," + Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    /**
     * Servlet 获取验证码方式
     */
    public static void createCaptcha(HttpServletRequest request, HttpServletResponse response, String captchaType) throws IOException {
        // 1.生成随机内容
        String code;
        if ("random".equals(captchaType)) {
            code = randomCode(4);
            request.getSession().setAttribute("validCode", code);
        } else {
            code = randomExpression();
            request.getSession().setAttribute("validCode", result);
        }
        // 2.创建画板
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 3.创建画笔
        Graphics2D pen = (Graphics2D) image.getGraphics();
        // 4.绘制内容
        //  4.1设置绘制区域
        pen.fillRect(0, 0, width, height);
        //  4.2设置字体
        pen.setFont(randomFont());
        //  4.3按顺序逐个绘制字符
        for (int i = 0; i < code.length(); i++) {
            pen.setColor((randomColor()));
            // 绘制字符
            pen.drawString(code.charAt(i) + "", 4 + fontSize * i, (fontSize + height) / 2);
        }
        //  4.4绘制噪音线
        drawLine(image);
        //  4.5绘制噪点
        drawNoise(image);
        // 5.存为图片并发送
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(image, "png", sos);
        sos.flush();
        sos.close();
    }

    public static void valid(HttpServletRequest request, HttpServletResponse response, String captchaType) throws IOException {
        // 1.得到数据
        String validCode;
        if (captchaType.contains("kaptcha")) {
            validCode = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString();
        } else {
            validCode = request.getSession().getAttribute("validCode").toString();
        }
        String inCode;
        if ("kaptcha2".equals(captchaType)) {
            inCode = request.getParameter("inCode2");
        } else {
            inCode = request.getParameter("inCode");
        }
        // 2.验证是否正确
        if (inCode.equalsIgnoreCase(validCode)) {
            response.sendRedirect("index.jsp");
        } else {
            if ("kaptcha2".equals(captchaType)) {
                request.getSession().setAttribute("err2", "验证码输入错误，请重新输入！");
            } else {
                request.getSession().setAttribute("err", "验证码输入错误，请重新输入！");
            }
            // 返回上一页
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    /**
     * 随机算术表达式
     */
    private static String randomExpression() {
        // 更改宽度
        width = 5 + fontSize * 7;
        int one = random.nextInt(100);
        int two = random.nextInt(100);
        char operator = operators.charAt(random.nextInt(operators.length()));
        switch (operator) {
            case '+':
                result = one + two;
                break;
            case '-':
                result = one - two;
                break;
            default:
                assert false : "error";
        }
        return "" + one + operator + two + "=?";
    }

    /**
     * 最少4个字符的随机字符串
     */
    private static String randomCode(int len) {
        if (len < 4) {
            len = 4;
        }
        // 更改宽度
        width = 6 + fontSize * len;
        // 生成字符串
        StringBuilder code = new StringBuilder();
        IntStream.rangeClosed(1, len).forEach(i -> code.append(randoms.charAt(random.nextInt(randoms.length()))));
        return code.toString();
    }

    /**
     * 随机字体
     */
    private static Font randomFont() {
        String name = fonts[random.nextInt(fonts.length)];
        int style = random.nextInt(3);
        int size = random.nextInt(5) + height;
        return new Font(name, style, size);
    }

    /**
     * 随机颜色
     */
    private static Color randomColor() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
    }

    /**
     * 添加干扰线
     */
    private static void drawLine(BufferedImage image) {
        Graphics2D pen = (Graphics2D) image.getGraphics();
        for (int i = 0; i < line; i++) {
            pen.setColor(randomColor());
            pen.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
        }
    }

    /**
     * 添加噪点
     */
    private static void drawNoise(BufferedImage image) {
        int area = (int) (noiseRate * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            image.setRGB(x, y, randomColor().getRGB());
        }
    }
}
