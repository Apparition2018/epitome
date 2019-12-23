package spring.servlet.captcha;

import com.google.code.kaptcha.Constants;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * CaptchaUtils
 *
 * @author Arsenal
 * created on 2019/12/23 23:08
 */
public class CaptchaUtils {

    private static Random random = new Random(); // 随机数对象
    private static int width = 140;    // 宽度
    private static int height = 24;    // 高度
    private static int fontSize = 18;  // 字体大小
    private static String randoms = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String operators = "+-";
    private static int result = -1; // 保存计算结果

    public static void createCaptcha(HttpServletRequest request, HttpServletResponse response, String captchaType) throws IOException {
        // 1.生成随机内容
        String code;
        if ("random".equals(captchaType)) {
            code = ranCode(4);
            request.getSession().setAttribute("validCode", code);
        } else {
            code = ranCode();
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
        pen.setFont(new Font("微软雅黑", Font.BOLD, fontSize));
        //  4.3按顺序逐个绘制字符
        for (int i = 0; i < code.length(); i++) {
            pen.setColor((ranColor()));
            // 绘制字符
            pen.drawString(code.charAt(i) + "", 5 + fontSize * i, (fontSize + height) / 2);
        }
        //  4.4绘制噪音线
        for (int i = 0; i < 2; i++) {
            pen.setColor(ranColor());
            // 线条宽度
            pen.setStroke(new BasicStroke(2));
            pen.drawLine(random.nextInt(width / 2), random.nextInt(height), random.nextInt(width), random.nextInt(height));
        }
        // 5.存为图片并发送
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(image, "png", sos);
        sos.flush();
        sos.close();
    }
    
    public static void valid (HttpServletRequest request, HttpServletResponse response, String captchaType) throws IOException {
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
    private static String ranCode() {
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
            case '*':
                result = one * two;
                break;
            case '/':
                result = one / two;
                break;
        }
        return "" + one + operator + two + "=?";
    }

    /**
     * 生成最少4个字符的随机字符串
     */
    private static String ranCode(int len) {
        if (len < 4) {
            len = 4;
        }
        // 更改宽度
        width = 5 + fontSize * len;
        // 生成字符串
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < len; i++) {
            code.append(randoms.charAt(random.nextInt(randoms.length())));
        }
        return code.toString();
    }

    /**
     * 生成随机颜色
     */
    private static Color ranColor() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
    }
}
