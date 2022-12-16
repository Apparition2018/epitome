package jar.hutool.extra;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;

/**
 * QrCodeUtil   二维码工具
 * 需要引入 com.google.zxing:core
 * https://hutool.cn/docs/#/extra/%E4%BA%8C%E7%BB%B4%E7%A0%81%E5%B7%A5%E5%85%B7-QrCodeUtil
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/extra/qrcode/QrCodeUtil.html
 *
 * @author ljh
 * @since 2020/11/5 14:53
 */
public class QrCodeUtilDemo extends Demo {

    @Test
    public void testQrCodeUtil() {
        // 自定义参数
        QrConfig config = new QrConfig(300, 300);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(2);
        // 设置前景色，既二维码颜色
        config.setForeColor(Color.BLACK);
        // 设置背景色
        config.setBackColor(Color.WHITE);
        // 设置 logo
        config.setImg(XIAO_XIN);

        // 生成指定 content 对应的二维码到文件，宽和高都是 300 像素
        QrCodeUtil.generate("999999999999", config, new File(DEMO_PATH + "QRCode.png"));

        // 识别二维码
        p(QrCodeUtil.decode(new File(DEMO_PATH + "QRCode.png")));
    }
}
