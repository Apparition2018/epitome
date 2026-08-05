package jar.hutool.swing;

import cn.hutool.core.swing.ScreenUtil;
import l.demo.Demo;

import java.awt.*;
import java.io.File;

/**
 * <a href="https://plus.hutool.cn/apidocs/cn/hutool/core/swing/ScreenUtil.html">ScreenUtil</a> 屏幕工具类
 *
 * @author ljh
 * @since 2020/11/19 15:44
 */
public class ScreenUtilDemo extends Demo {

    public static void main(String[] args) {
        // 截图
        Rectangle rectangle = new Rectangle();
        rectangle.setBounds(60, 40, 250, 200);
        ScreenUtil.captureScreen(rectangle, new File(HU_DEMO_DIR_PATH + "capture.jpg"));

        p(ScreenUtil.getRectangle());   // java.awt.Rectangle[x=0,y=0,width=1920,height=1080]
        p(ScreenUtil.getWidth());       // 1920
        p(ScreenUtil.getHeight());      // 1080
    }
}
