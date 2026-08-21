package org.ljh.dependencies.hutool.swing;

import cn.hutool.core.swing.ScreenUtil;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;

import static com.ljh.commons.Demo.HU_DEMO_DIR_PATH;
import static com.ljh.commons.Demo.p;

/**
 * <a href="https://plus.hutool.cn/apidocs/cn/hutool/core/swing/ScreenUtil.html">ScreenUtil</a> 屏幕工具类
 *
 * @author ljh
 * @since 2020/11/19 15:44
 */
public class ScreenUtilTests {

    @Test
    public void test() {
        // 截图
        Rectangle rectangle = new Rectangle();
        rectangle.setBounds(60, 40, 250, 200);
        ScreenUtil.captureScreen(rectangle, new File(HU_DEMO_DIR_PATH + "capture.jpg"));

        p(ScreenUtil.getRectangle());   // java.awt.Rectangle[x=0,y=0,width=1920,height=1080]
        p(ScreenUtil.getWidth());       // 1920
        p(ScreenUtil.getHeight());      // 1080
    }
}
