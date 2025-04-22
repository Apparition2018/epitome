package jar.google.zxing;

import cn.hutool.core.img.ImgUtil;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * zxing
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class ZXingDemo extends Demo {

    private static final String FORMAT = ImgUtil.IMAGE_TYPE_PNG;
    private static final String CONTENT = "999999999999";
    private static final String QR_CODE_PATH = DEMO_DIR_PATH + "QRCode.png";
    private static final String EAN_13_PATH = DEMO_DIR_PATH + "EAN-13.png";

    /** 生成 QR Code */
    @Test
    public void createQRCode() {
        this.createCode(BarcodeFormat.QR_CODE, QR_CODE_PATH);
    }

    /** 读取 QR Code */
    @Test
    public void readQRCode() {
        Result result = this.readCode(BarcodeFormat.QR_CODE, QR_CODE_PATH);
        if (null != result) {
            p("二维码格式类型：" + result.getBarcodeFormat());
            p("二维码文本内容：" + result.getText());
        } else {
            p("生成条形码失败!");
        }
    }

    /** 生成条形码 (EAN-13) */
    @Test
    public void createEan13() {
        this.createCode(BarcodeFormat.EAN_13, EAN_13_PATH);
    }

    /** 读取条形码 (EAN-13) */
    @Test
    public void readEan13() {
        Result result = this.readCode(BarcodeFormat.EAN_13, EAN_13_PATH);
        if (null != result) {
            p("条形码格式类型：" + result.getBarcodeFormat());
            p("条形码文本内容：" + result.getText());
        } else {
            p("生成条形码失败!");
        }
    }

    private void createCode(BarcodeFormat barcodeFormat, String filePath) {
        int width = 0;
        int height = 0;
        Map<EncodeHintType, Serializable> hints = null;
        switch (barcodeFormat) {
            // 二维码 (QR Code)
            case QR_CODE:
                width = 300;
                height = 300;
                hints = Map.of(
                    EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name(),
                    // 纠错等级
                    EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M,
                    EncodeHintType.MARGIN, 2
                );
                break;
            // 条形码 (EAN-13)
            case EAN_13:
                width = 3 +     // start guard
                    (7 * 6) +   // left bars
                    5 +         // middle guard
                    (7 * 6) +   // right bars
                    3;          // end guard
                height = 30;
                break;
            default:
                assert false : "暂只支持 QR Code 和 EAN-13";
        }

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(CONTENT, barcodeFormat, width, height, hints);
            MatrixToImageWriter.writeToPath(bitMatrix, FORMAT, new File(filePath).toPath());
        } catch (IOException | WriterException e) {
            throw new RuntimeException(e);
        }
    }

    private Result readCode(BarcodeFormat barcodeFormat, String filePath) {
        Map<DecodeHintType, Charset> hints = null;
        switch (barcodeFormat) {
            // 二维码 (QR Code)
            case QR_CODE:
                hints = Map.of(DecodeHintType.CHARACTER_SET, StandardCharsets.UTF_8);
                break;
            // 条形码 (EAN-13)
            case EAN_13:
                break;
            default:
                assert false : "暂只支持 QR Code 和 EAN-13";
        }

        try {
            BinaryBitmap binaryBitmap =
                new BinaryBitmap(
                    new HybridBinarizer(
                        new BufferedImageLuminanceSource(
                            ImageIO.read(new File(filePath))
                        )
                    )
                );

            return new MultiFormatReader().decode(binaryBitmap, hints);
        } catch (NotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
