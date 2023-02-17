package jar.hutool.swing;

import cn.hutool.core.swing.RobotUtil;
import l.demo.Demo;

import java.awt.*;
import java.io.File;

/**
 * <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/swing/RobotUtil.html">RobotUtil</a>  Robot 封装工具类
 *
 * @author ljh
 * @since 2020/11/19 15:37
 */
public class RobotUtilDemo extends Demo {

    public static void main(String[] args) {
        // 截图
        Rectangle rectangle = new Rectangle();
        rectangle.setBounds(60, 40, 400, 250);
        RobotUtil.captureScreen(rectangle, new File(DEMO_PATH + "capture.jpg"));
    }
}
