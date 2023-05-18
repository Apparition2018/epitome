package knowledge.api.awt;

import javax.swing.*;
import java.awt.*;

import static l.demo.Demo.XIAO_XIN_PNG;

/**
 * SystemTray   系统托盘
 *
 * @author ljh
 * @since 2023/2/8 10:31
 */
public class SystemTrayDemo {

    public static void main(String[] args) throws AWTException {
        TrayIcon trayIcon = new TrayIcon(new ImageIcon(XIAO_XIN_PNG).getImage());
        SystemTray.getSystemTray().add(trayIcon);
    }
}
