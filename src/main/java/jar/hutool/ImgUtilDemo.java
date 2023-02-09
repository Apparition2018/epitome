package jar.hutool;

import cn.hutool.core.img.ImgUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * <a href="https://hutool.cn/docs/#/core//图片/图片工具-ImgUtil">ImgUtil</a>
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/img/ImgUtil.html">ImgUtil api</a>
 * <pre>
 * static ImageInputStream  getImageInputStream(InputStream in)                         获取 ImageInputStream
 * static ImageOutputStream getImageOutputStream(File outFile / OutputStream out)       获取 ImageOutputStream
 * static ImageReader       getReader(String type)                                      获得 ImageReader
 * static ImageWriter       getWriter([Image img, ]String formatName)                   获取 ImageWriter
 *
 * static Graphics2D        createGraphics(BufferedImage image, Color color)            创建 Graphics2D
 * static Rectangle2D       getRectangle(String str, Font font)                         获取 Rectangle2D
 * static Font              createFont(InputStream fontStream / File fontFile)          根据文件创建字体
 * static Color             getColor(int rgb / String colorName)                        获取颜色
 * static Color             randomColor(Random random)                                  生成随机颜色
 * static Color             hexToColor(String hex)                                      16进制的颜色值转换为 Color 对象
 * static String            toHex(Color color)                                          Color 对象转16进制表示
 *
 * static BufferedImage     copyImage(Image img, int imageType, Color backgroundColor)  将已有 Image 复制新的一份出来
 * static BufferedImage     read(XXX)                                                   读取图片
 * static void              write[XXX](XXX ...)                                         写出图片
 * </pre>
 *
 * @author ljh
 * @since 2020/11/19 23:55
 */
public class ImgUtilDemo extends Demo {

    private static final File IMG = new File(HU_DEMO_PATH + "capture.jpg");

    @Test
    public void edit() {
        // 缩放
        ImgUtil.scale(IMG, new File(HU_DEMO_PATH + "capture_scale.jpg"), 0.5f);

        // 旋转
        ImgUtil.rotate(IMG, 180, new File(HU_DEMO_PATH + "capture_rotate.jpg"));

        // 水平翻转
        ImgUtil.flip(IMG, new File(HU_DEMO_PATH + "capture_flip.jpg"));

        // 裁剪
        ImgUtil.cut(IMG, new File(HU_DEMO_PATH + "capture_cut.jpg"), new Rectangle(0, 0, 100, 100));

        // 切片
        // ImgUtil.slice(IMG, new File(HU_DEMO_PATH), 100, 100);

        // 类型转换
        ImgUtil.convert(IMG, new File(HU_DEMO_PATH + "capture_convert.jpg"));

        // 黑白
        ImgUtil.gray(IMG, new File(HU_DEMO_PATH + "capture_gray.jpg"));

        // 压缩
        ImgUtil.compress(IMG, new File(HU_DEMO_PATH + "capture_compress.jpg"), 0.8f);

        // 文字水印
        ImgUtil.pressText(IMG, new File(HU_DEMO_PATH + "capture_pressText.jpg"),
                "版权所有", Color.PINK, // 文字
                new Font("黑体", Font.ITALIC, 36), //字体
                0,      // x 坐标修正值。 默认在中间，偏移量相对于中间偏移
                0,      // y 坐标修正值。 默认在中间，偏移量相对于中间偏移
                0.8f    // 透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
        );

        // 图片水印
        ImgUtil.pressImage(IMG, new File(HU_DEMO_PATH + "capture_pressImage.jpg"),
                ImgUtil.read(new File(XIAO_XIN_IMG)), // 水印图片
                0,      // x 坐标修正值。 默认在中间，偏移量相对于中间偏移
                0,      // y 坐标修正值。 默认在中间，偏移量相对于中间偏移
                0.1f
        );
    }

    @Test
    public void convert() {
        String imgBase64;
        BufferedImage bufferedImage;
        RenderedImage renderedImage;
        ByteArrayInputStream bais;

        // Image → Base64
        imgBase64 = ImgUtil.toBase64(ImgUtil.read(IMG), "jpg");
        imgBase64 = ImgUtil.toBase64DataUri(ImgUtil.read(IMG), "jpg");
        // Base64 → BufferedImage
        bufferedImage = ImgUtil.toImage(imgBase64);

        // Image → byte[]
        byte[] imgBytes = ImgUtil.toBytes(ImgUtil.read(IMG), "jpg");
        // byte[] → BufferedImage
        bufferedImage = ImgUtil.toImage(imgBytes);

        // Image → BufferedImage
        bufferedImage = ImgUtil.toBufferedImage(ImgUtil.read(IMG), "jpg");
        // Image → RenderedImage
        renderedImage = ImgUtil.toRenderedImage(ImgUtil.read(IMG));
        // Image → ByteArrayInputStream
        bais = ImgUtil.toStream(ImgUtil.read(IMG), "jpg");
    }

    /**
     * 创建文字图片
     */
    @Test
    public void create() throws IOException {
        ImgUtil.createImage("ABC", new Font("黑体", Font.PLAIN, 28), Color.WHITE, Color.BLACK,
                new FileImageOutputStream(new File(HU_DEMO_PATH + "capture_createImage.jpg")));
    }
}
