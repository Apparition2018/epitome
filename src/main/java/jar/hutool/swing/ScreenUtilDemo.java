package jar.hutool.swing;

import cn.hutool.core.swing.ScreenUtil;
import l.demo.Demo;
import org.junit.Test;

import java.awt.*;
import java.io.File;

/**
 * ScreenUtil   屏幕工具类
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/swing/ScreenUtil.html
 *
 * @author ljh
 * created on 2020/11/19 15:44
 */
public class ScreenUtilDemo extends Demo {
    
    @Test
    public void testScreenUtil() {
        // 截图
        Rectangle rectangle = new Rectangle();
        rectangle.setBounds(60, 40, 400, 250);
        ScreenUtil.captureScreen(rectangle, new File(DEMO_PATH + "capture.jpg"));
        
        p(ScreenUtil.getRectangle());   // java.awt.Rectangle[x=0,y=0,width=1920,height=1080]
        p(ScreenUtil.getWidth());       // 1920
        p(ScreenUtil.getHeight());      // 1080
    }
}
