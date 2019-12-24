package other.验证码;

import com.google.common.hash.Hashing;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

/**
 * Captcha
 *
 * @author Arsenal
 * created on 2019/12/20 14:09
 */
public class Captcha {

    /**
     * 画布宽
     */
    private static int width = 120;
    /**
     * 画布高
     */
    private static int height = 40;
    /**
     * 字体数组
     */
    private static String[] fonts = {"Consolas", "Arial", "Algerian"};
    /**
     * 验证码位数
     */
    private static int size = 4;
    /**
     * 随机字符
     */
    private static String randoms = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 干扰线条数
     */
    private static int line = 4;
    /**
     * 噪点率
     */
    private static double noiseRate = 0.05f;

    private static Random random = new Random();

    /**
     * 前后段分离方式
     */
    public static String frontBackStageDecoupling() throws IOException {
        // 生成随机 key，作为 redis key，用于后期验证码验证
        String key = UUID.randomUUID().toString();
        // 生成验证码画布
        BufferedImage bufferedImage = createBufferedImage(getNumber(size).toCharArray());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // 将图片按照指定的格式（jpeg）画到流上
        ImageIO.write(bufferedImage, "jpg", outputStream);
        // 将流转换成 base64 字符串
        return "data:image/jpg;base64," + Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }

    /**
     * 传统 servlet 方式
     * https://www.imooc.com/video/5768
     */
    public static void servlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取随机数（验证码）
        String number = getNumber(size);
        // 将验证码绑定到 session 对象上
        request.getSession().setAttribute("captcha", number);
        // 生成验证码画布
        BufferedImage bufferedImage = createBufferedImage(number.toCharArray());
        // 告诉浏览器，服务器返回的是一张图片
        response.setContentType("image/jpg");
        // 将图片按照指定的格式（jpeg）画到流上
        ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
    }

    /**
     * 生成验证码画布
     */
    private static BufferedImage createBufferedImage(char[] numbers) {
        // 创建画布
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获得画笔
        Graphics2D g = (Graphics2D) image.getGraphics();
        // 给笔设置颜色
        g.setColor(Color.WHITE);
        // 填充矩形区域
        g.fillRect(0, 0, width, height);
        for (int i = 0; i < numbers.length; i++) {
            // 重新给笔设置颜色
            g.setColor(randomColor());
            // 设置字体
            Font font = randomFont();
            g.setFont(font);
            // 在图片上绘制随机数
            g.drawString(Character.toString(numbers[i]), width / size * i, (height + font.getSize() * 2 / 3) / 2);
        }
        // 添加干扰线
        drawLine(image);
        // 添加噪点
        drawNoise(image);
        return image;
    }

    /**
     * 生成随机数
     *
     * @param size 位数
     */
    private static String getNumber(int size) {
        if (size < 4) {
            size = 4;
        }
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < size; i++) {
            number.append(randoms.charAt(random.nextInt(randoms.length())));
        }
        return number.toString();
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
     * 随机字体
     */
    private static Font randomFont() {
        String name = fonts[random.nextInt(fonts.length)];
        int style = random.nextInt(3);
        int size = random.nextInt(5) + height;
        return new Font(name, style, size);
    }

    /**
     * 添加干扰线
     */
    private static void drawLine(BufferedImage image) {
        Graphics2D g = (Graphics2D) image.getGraphics();
        for (int i = 0; i < line; i++) {
            g.setColor(randomColor());
            g.drawLine(random.nextInt(width / 2), random.nextInt(height), random.nextInt(width), random.nextInt(height));
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
