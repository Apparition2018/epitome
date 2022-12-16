package jar.hutool.swing;

import cn.hutool.core.swing.DesktopUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * DesktopUtil  桌面工具类
 *
 * @author ljh
 * @since 2020/11/19 15:46
 */
public class DesktopUtilDemo extends Demo {
    
    @Test
    public void testDesktopUtil() {
        // 使用平台默认浏览器打开指定 URL 地址
        DesktopUtil.browse(BAIDU_URL);

        // 邮件
        // DesktopUtil.mail(MY_EMAIL);
        
        // 启动关联应用程序来打开文件
        DesktopUtil.open(new File(DEMO_PATH));
        
        // 启动关联编辑器应用程序并打开用于编辑的文件
        DesktopUtil.edit(new File(DEMO_PATH + "QRCode.png"));
        
        // 使用关联应用程序的打印命令, 用本机桌面打印设备来打印文件
        // DesktopUtil.print(new File(DEMO_PATH + "QRCode.png"));
    }
}
