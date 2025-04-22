package jar.google.Thumbnailator;

import cn.hutool.core.img.ImgUtil;
import l.demo.Demo;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Thumbnailator
 *
 * @author ljh
 * @since 2023/2/23 11:53
 */
public class ThumbnailatorDemo extends Demo {

    public static void main(String[] args) throws IOException {
        Thumbnails.of(XIAO_XIN_PNG)
                // 缩放
                .scale(0.5)
                // 旋转
                .rotate(360)
                // 输出质量
                .outputQuality(1)
                // 输出格式
                .outputFormat(ImgUtil.IMAGE_TYPE_PNG)
                // 水印
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(XIAO_XIN_PNG)), 0.1F)
                .toFile(DESKTOP + FilenameUtils.getName(XIAO_XIN_PNG));
    }
}
