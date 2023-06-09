package jar.hutool.extra;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import l.demo.Demo;

import java.awt.*;
import java.io.File;

/**
 * <a href="https://hutool.cn/docs/#/extra/二维码工具-QrCodeUtil">QrCodeUtil</a>     二维码工具
 * <p>需要引入 com.google.zxing:core
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/extra/qrcode/QrCodeUtil.html">QrCodeUtil api</a>
 *
 * @author ljh
 * @since 2020/11/5 14:53
 */
public class QrCodeUtilDemo extends Demo {

    public static void main(String[] args) {
        // 自定义参数
        QrConfig config = new QrConfig(300, 300);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(2);
        // 设置前景色，既二维码颜色
        config.setForeColor(Color.BLACK);
        // 设置背景色
        config.setBackColor(Color.WHITE);
        // 设置 logo
        config.setImg(XIAO_XIN_PNG);
        // 生成指定 content 对应的二维码到文件，宽和高都是 300 像素
        QrCodeUtil.generate("999999999999", config, new File(DEMO_DIR_PATH + "QRCode.png"));
        // 识别二维码
        p(QrCodeUtil.decode(new File(DEMO_DIR_PATH + "QRCode.png")));
    }
}
