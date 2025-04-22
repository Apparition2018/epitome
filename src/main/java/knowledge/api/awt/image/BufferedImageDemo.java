package knowledge.api.awt.image;

import cn.hutool.core.img.ImgUtil;
import l.demo.Demo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * BufferedImage
 *
 * @author ljh
 * @since 2023/1/10 16:36
 */
public class BufferedImageDemo extends Demo {

    public static void main(String[] args) throws IOException {
        BufferedImage bi = ImageIO.read(new File(XIAO_XIN_PNG));
        ImageIO.write(bi, ImgUtil.IMAGE_TYPE_PNG, new File(DEMO_DIR_PATH + "copy.png"));
    }
}
