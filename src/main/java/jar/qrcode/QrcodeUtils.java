package jar.qrcode;

import com.swetake.util.Qrcode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;

/**
 * <a href="https://java.hotexamples.com/zh/examples/com.swetake.util/Qrcode/calQrcode/java-qrcode-calqrcode-method-examples.html">QrcodeUtils</a>
 *
 * @author ljh
 * @since 2023/11/3 10:17
 */
public final class QrcodeUtils {
    private QrcodeUtils() {
        throw new AssertionError(String.format("No %s instances for you!", this.getClass().getName()));
    }

    public static BufferedImage createQRCodeImage(String content) {
        return createQRCodeImage(content, 4, null, null);
    }

    public static BufferedImage createQRCodeImage(String content, int pixels) {
        return createQRCodeImage(content, pixels, null, null);
    }

    /**
     * 创建二维码 Image
     *
     * @param content       内容
     * @param pixels        每个格子的像素
     * @param offset        二维码周边空白像素
     * @param qrcodeVersion 二维码版本 (1~40)，值越大可存储信息越大。每增加1，长度增加4。版本1 (21*21)，版本2 (25*25)…
     * @return BufferedImage
     */
    public static BufferedImage createQRCodeImage(String content, int pixels, Integer offset, Integer qrcodeVersion) {
        offset = offset != null ? offset : pixels;
        qrcodeVersion = qrcodeVersion != null ? qrcodeVersion : 1;
        // 二维码边长
        int size = (21 + (qrcodeVersion - 1) * 4) * pixels + offset * 2;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setBackground(Color.WHITE);
        graphics.clearRect(0, 0, size, size);
        graphics.setColor(Color.BLACK);

        Qrcode qrcode = new Qrcode();
        // 模式：Numeric 数字，Alphanumeric 字母数字，Binary 二进制，Kanji 汉字
        qrcode.setQrcodeEncodeMode('B');
        // 排错率：可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但清晰度的要求越小
        qrcode.setQrcodeErrorCorrect('M');
        // 版本：1~40个版本，值越大可存储信息越大
        qrcode.setQrcodeVersion(qrcodeVersion);
        try {
            boolean[][] codeOut = qrcode.calQrcode(content.getBytes(StandardCharsets.UTF_8));
            for (int i = 0; i < codeOut.length; i++) {
                for (int j = 0; j < codeOut.length; j++) {
                    if (codeOut[j][i]) {
                        graphics.fillRect(j * pixels + offset, i * pixels + offset, pixels, pixels);
                    }
                }
            }
            graphics.dispose();
            image.flush();
            return image;
        } catch (ArrayIndexOutOfBoundsException e) {
            // 数组下标越界，版本+1
            image = createQRCodeImage(content, pixels, offset, ++qrcodeVersion);
        }
        return image;
    }
}
