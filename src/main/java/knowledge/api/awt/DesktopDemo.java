package knowledge.api.awt;

import l.demo.Demo;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/awt/Desktop.html">Desktop</a>
 * <p><a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/awt/Desktop.Action.html">Desktop.Action</a>
 *
 * @author ljh
 * @since 2020/9/25 17:42
 */
public class DesktopDemo extends Demo {

    public static void main(String[] args) throws IOException {
        // static boolean	    isDesktopSupported()        测试当前平台是否支持此类
        if (Desktop.isDesktopSupported()) {
            // static Desktop	getDesktop()                返回当前浏览器上下文的 Desktop 实例
            Desktop desktop = Desktop.getDesktop();

            // void	            browse(URI uri)             启动默认浏览器来显示 URI
            desktop.browse(URI.create(BAIDU_URL));

            // void	            mail([URI mailtoURI])       启动用户默认邮件客户端的邮件组合窗口，填充由 mailto: URI 指定的消息字段
            desktop.mail();

            // void	            open(File file              启动关联应用程序来打开文件
            desktop.open(new File(DEMO_PATH));

            // void	            edit(File file)             启动关联编辑器应用程序并打开用于编辑的文件
            // void	            print(File file)            使用关联应用程序的打印命令，用本机桌面打印设施来打印文件

            // boolean	        isSupported(Desktop.Action action)
            // 测试当前平台是否支持某一动作：BROWSE, EDIT, MAIL, OPEN, PRINT
        } else {
            p("当前平台不支持 Desktop");
        }
    }
}
