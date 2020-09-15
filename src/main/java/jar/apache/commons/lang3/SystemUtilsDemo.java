package jar.apache.commons.lang3;

import l.demo.Demo;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

/**
 * SystemUtils
 */
public class SystemUtilsDemo extends Demo {

    @Test
    public void system() {
        p(SystemUtils.getJavaHome());       // C:\Program Files\Java\jdk1.8.0_131\jre
        p(SystemUtils.getJavaIoTmpDir());   // C:\Users\234607\AppData\Local\Temp
        p(SystemUtils.getUserDir());        // C:\Users\234607\git\epitome
        p(SystemUtils.getUserHome());       // C:\Users\234607
        p(SystemUtils.isJavaAwtHeadless()); // false
        p(SystemUtils.AWT_TOOLKIT);         // sun.awt.windows.WToolkit
        p(SystemUtils.FILE_ENCODING);       // UTF-8
    }

}
