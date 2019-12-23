package spring.servlet.captcha;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * CaptchaServlet
 *
 * @author NL-PC001
 * created on 2019/12/23 10:20
 */
public class CaptchaServlet extends HttpServlet {

    private Random random = new Random(); // 随机数对象
    private int width = 80;     // 宽度
    private int height = 24;    // 高度
    private int fontSize = 16;  // 字体大小
    private String str = "0123456789abcdef";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.生成随机内容
        String code = ranCode(4);
        request.getSession().setAttribute("validCode", code);
        // 2.创建画板
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 3.创建画笔
        Graphics2D pen = (Graphics2D) img.getGraphics();
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
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(img, "png", outputStream);
        outputStream.flush();
        outputStream.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * 生成最少4个字符的随机字符串
     */
    private String ranCode(int len) {
        if (len < 4) {
            len = 4;
        }
        // 更改宽度
        width = 5 + fontSize * len;
        // 生成字符串
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < len; i++) {
            code.append(str.charAt(random.nextInt(str.length())));
        }
        return code.toString();
    }

    /**
     * 生成随机颜色
     */
    private Color ranColor() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
    }
}
