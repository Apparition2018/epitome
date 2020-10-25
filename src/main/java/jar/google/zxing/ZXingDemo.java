package jar.google.zxing;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import l.demo.Demo;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.HashMap;

public class ZXingDemo extends Demo {

    /**
     * 生成 QR Code
     */
    @Test
    public void CreateQRCode() {
        int width = 300;
        int height = 300;
        String format = "png";
        String content = "epitome";

        // 定义二维码参数
        HashMap<EncodeHintType, Serializable> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, UTF_8);
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M); // 纠错等级
        hints.put(EncodeHintType.MARGIN, 2);                                // 边距

        // 生成二维码
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            Path file = new File(DEMO_PATH + "QRCode.png").toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取 QR Code
     */
    @Test
    @SuppressWarnings(value = {"unchecked", "rawtypes"})
    public void ReadQRCode() {
        try {
            MultiFormatReader formatReader = new MultiFormatReader();

            File file = new File(DEMO_PATH + "QRCode.png");
            BufferedImage image = ImageIO.read(file);

            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));

            // 定义二维码参数
            HashMap hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8);

            Result result = formatReader.decode(binaryBitmap, hints);

            p("解析结果：" + result.toString());
            p("二维码格式类型：" + result.getBarcodeFormat());
            p("二维码文本内容：" + result.getText());
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
        }
    }

}
