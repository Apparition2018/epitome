package jar.apache.commons.lang3;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

/**
 * SystemUtils
 */
public class SystemUtilsDemo {



    @Test
    public void system() {
        System.out.println(SystemUtils.getJavaHome());      // C:\Program Files\Java\jdk1.8.0_131\jre
        System.out.println(SystemUtils.getJavaIoTmpDir());  // C:\Users\234607\AppData\Local\Temp
        System.out.println(SystemUtils.getUserDir());       // C:\Users\234607\git\epitome
        System.out.println(SystemUtils.getUserHome());      // C:\Users\234607
        System.out.println(SystemUtils.isJavaAwtHeadless());// false

        System.out.println(SystemUtils.AWT_TOOLKIT);        // sun.awt.windows.WToolkit
        System.out.println(SystemUtils.FILE_ENCODING);      // UTF-8
    }


}
