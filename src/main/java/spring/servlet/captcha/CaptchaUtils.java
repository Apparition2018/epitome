package spring.servlet.captcha;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.hc.core5.http.HttpHeaders;
import org.springframework.http.MediaTypeFactory;

import javax.imageio.ImageIO;
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
public final class CaptchaUtils {
    private CaptchaUtils() {
        throw new AssertionError(String.format("No %s instances for you!", this.getClass().getName()));
    }

    private static int width = 80;
    private static int height = 30;
    private static final int FONT_SIZE = 18;
    // 运算结果
    private static int result = -1;
    private static final Random RANDOM = new Random();
    private static final String OPERATORS = "+-";
    private static final String RANDOM_STR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String[] FONTS = {"Consolas", "Arial", "Algerian"};
    // 干扰线数量
    private static final int INTERFERING_LINE = 2;
    // 噪点率
    private static final double NOISE_RATE = 0.03D;

    /** 前后分离获取验证码方式 */
    public static String createCaptcha() throws IOException {
        // 生成随机 key，作为 redis key，用于后期验证码验证
        String key = UUID.randomUUID().toString();
        // 生成验证码画布
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // ***************
        // * 画图过程省略 *
        // ***************
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final String extension = ImgUtil.IMAGE_TYPE_JPG;
        // 将图片按照指定的格式（jpeg）画到流上
        ImageIO.write(bufferedImage, extension, baos);
        String contentType = MediaTypeFactory.getMediaType(StrUtil.DOT + extension).orElseThrow().toString();
        // 将流转换成 base64 字符串
        return String.format("data:%s;base64,%s", contentType, Base64.getEncoder().encodeToString(baos.toByteArray()));
    }

    /** Servlet 获取验证码方式 */
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
            pen.drawString(String.valueOf(code.charAt(i)), 4 + FONT_SIZE * i, (FONT_SIZE + height) / 2);
        }
        //  4.4绘制噪音线
        drawLine(image);
        //  4.5绘制噪点
        drawNoise(image);
        // 5.存为图片并发送
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(image, ImgUtil.IMAGE_TYPE_PNG, sos);
        sos.flush();
        sos.close();
    }

    public static void valid(HttpServletRequest request, HttpServletResponse response, String captchaType) throws IOException {
        // 1.得到数据
        String validCode = request.getSession().getAttribute("validCode").toString();
        String inCode = request.getParameter("inCode");
        // 2.验证是否正确
        if (inCode.equalsIgnoreCase(validCode)) {
            response.sendRedirect("index.jsp");
        } else {
            request.getSession().setAttribute("err", "验证码输入错误，请重新输入！");
            // 返回上一页
            response.sendRedirect(request.getHeader(HttpHeaders.REFERER));
        }
    }

    /** 随机算术表达式 */
    private static String randomExpression() {
        // 更改宽度
        width = 5 + FONT_SIZE * 7;
        int one = RANDOM.nextInt(100);
        int two = RANDOM.nextInt(100);
        char operator = OPERATORS.charAt(RANDOM.nextInt(OPERATORS.length()));
        switch (operator) {
            case '+' -> result = one + two;
            case '-' -> result = one - two;
            default -> {
                assert false : "error";
            }
        }
        return String.valueOf(one) + operator + two + "=?";
    }

    /** 最少4个字符的随机字符串 */
    private static String randomCode(int len) {
        if (len < 4) len = 4;
        // 更改宽度
        width = 6 + FONT_SIZE * len;
        // 生成字符串
        StringBuilder code = new StringBuilder();
        IntStream.rangeClosed(1, len).forEach(i -> code.append(RANDOM_STR.charAt(RANDOM.nextInt(RANDOM_STR.length()))));
        return code.toString();
    }

    /** 随机字体 */
    private static Font randomFont() {
        String name = FONTS[RANDOM.nextInt(FONTS.length)];
        int style = RANDOM.nextInt(3);
        int size = RANDOM.nextInt(5) + height;
        return new Font(name, style, size);
    }

    /** 随机颜色 */
    private static Color randomColor() {
        int r = RANDOM.nextInt(256);
        int g = RANDOM.nextInt(256);
        int b = RANDOM.nextInt(256);
        return new Color(r, g, b);
    }

    /** 添加干扰线 */
    private static void drawLine(BufferedImage image) {
        Graphics2D pen = (Graphics2D) image.getGraphics();
        for (int i = 0; i < INTERFERING_LINE; i++) {
            pen.setColor(randomColor());
            pen.drawLine(RANDOM.nextInt(width), RANDOM.nextInt(height), RANDOM.nextInt(width), RANDOM.nextInt(height));
        }
    }

    /** 添加噪点 */
    private static void drawNoise(BufferedImage image) {
        int area = (int) (NOISE_RATE * width * height);
        for (int i = 0; i < area; i++) {
            int x = RANDOM.nextInt(width);
            int y = RANDOM.nextInt(height);
            image.setRGB(x, y, randomColor().getRGB());
        }
    }
}
