package org.ljh.dependencies.hutool;

import cn.hutool.core.img.ImgUtil;
import org.junit.jupiter.api.Test;

import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import static com.ljh.commons.Demo.HU_DEMO_DIR_PATH;
import static com.ljh.commons.Demo.XIAO_XIN_PNG;

/**
 * <a href="https://doc.hutool.cn/pages/ImgUtil/">ImgUtil</a>   图片工具
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/img/ImgUtil.html">ImgUtil api</a>
 *
 * @author ljh
 * @since 2020/11/19 23:55
 */
public class ImgUtilTests {

    private static final File IMG = new File(HU_DEMO_DIR_PATH + "capture.jpg");

    @Test
    public void edit() {
        // 缩放
        ImgUtil.scale(IMG, new File(HU_DEMO_DIR_PATH + "capture_scale.jpg"), 0.5f);

        // 旋转
        ImgUtil.rotate(IMG, 180, new File(HU_DEMO_DIR_PATH + "capture_rotate.jpg"));

        // 水平翻转
        ImgUtil.flip(IMG, new File(HU_DEMO_DIR_PATH + "capture_flip.jpg"));

        // 裁剪
        ImgUtil.cut(IMG, new File(HU_DEMO_DIR_PATH + "capture_cut.jpg"), new Rectangle(0, 0, 100, 100));

        // 切片
        // ImgUtil.slice(IMG, new File(HU_DEMO_PATH), 100, 100);

        // 类型转换
        ImgUtil.convert(IMG, new File(HU_DEMO_DIR_PATH + "capture_convert.jpg"));

        // 黑白
        ImgUtil.gray(IMG, new File(HU_DEMO_DIR_PATH + "capture_gray.jpg"));

        // 压缩
        ImgUtil.compress(IMG, new File(HU_DEMO_DIR_PATH + "capture_compress.jpg"), 0.8f);

        // 文字水印
        ImgUtil.pressText(IMG, new File(HU_DEMO_DIR_PATH + "capture_pressText.jpg"),
                "版权所有", Color.PINK, // 文字
                new Font("黑体", Font.ITALIC, 36), // 字体
                0,      // x 坐标修正值。 默认在中间，偏移量相对于中间偏移
                0,      // y 坐标修正值。 默认在中间，偏移量相对于中间偏移
                0.8f    // 透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
        );

        // 图片水印
        ImgUtil.pressImage(IMG, new File(HU_DEMO_DIR_PATH + "capture_pressImage.jpg"),
                ImgUtil.read(new File(XIAO_XIN_PNG)), // 水印图片
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
        imgBase64 = ImgUtil.toBase64(ImgUtil.read(IMG), ImgUtil.IMAGE_TYPE_JPG);
        imgBase64 = ImgUtil.toBase64DataUri(ImgUtil.read(IMG), ImgUtil.IMAGE_TYPE_JPG);
        // Base64 → BufferedImage
        bufferedImage = ImgUtil.toImage(imgBase64);

        // Image → byte[]
        byte[] imgBytes = ImgUtil.toBytes(ImgUtil.read(IMG), ImgUtil.IMAGE_TYPE_JPG);
        // byte[] → BufferedImage
        bufferedImage = ImgUtil.toImage(imgBytes);

        // Image → BufferedImage
        bufferedImage = ImgUtil.toBufferedImage(ImgUtil.read(IMG), ImgUtil.IMAGE_TYPE_JPG);
        // Image → RenderedImage
        renderedImage = ImgUtil.castToRenderedImage(ImgUtil.read(IMG), ImgUtil.IMAGE_TYPE_JPG);
        // Image → ByteArrayInputStream
        bais = ImgUtil.toStream(ImgUtil.read(IMG), ImgUtil.IMAGE_TYPE_JPG);
    }

    /** 创建文字图片 */
    @Test
    public void createImage() throws IOException {
        ImgUtil.createImage("ABC", new Font("黑体", Font.PLAIN, 28), Color.WHITE, Color.BLACK,
                new FileImageOutputStream(new File(HU_DEMO_DIR_PATH + "createImage.jpg")));
    }
}
