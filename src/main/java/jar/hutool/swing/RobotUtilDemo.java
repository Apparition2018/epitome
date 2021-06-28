package jar.hutool.swing;

import cn.hutool.core.swing.RobotUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;

/**
 * RobotUtil    Robot 封装工具类
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/swing/RobotUtil.html
 *
 * @author ljh
 * created on 2020/11/19 15:37
 */
public class RobotUtilDemo extends Demo {

    @Test
    public void testRobotUtil() {
        // 截图
        Rectangle rectangle = new Rectangle();
        rectangle.setBounds(60, 40, 400, 250);
        RobotUtil.captureScreen(rectangle, new File(DEMO_PATH + "capture.jpg"));
    }
}
