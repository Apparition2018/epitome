package jar.apache.commons.lang3;

import l.demo.Demo;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

/**
 * SystemUtils
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/SystemUtils.html
 */
public class SystemUtilsDemo extends Demo {

    @Test
    public void system() {
        p(SystemUtils.getUserName());       // NL-PC001
        p(SystemUtils.getUserHome());       // C:\Users\NL-PC001
        p(SystemUtils.getUserDir());        // D:\L\git\epitome
        p(SystemUtils.getHostName());       // JS3-LJH
        p(SystemUtils.getJavaHome());       // D:\Java\jdk1.8.0_221\jre
        p(SystemUtils.getJavaIoTmpDir());   // C:\Users\NL-PC001\AppData\Local\Temp
        
        p(SystemUtils.USER_NAME);           // NL-PC001
        p(SystemUtils.USER_HOME);           // C:\Users\NL-PC001
        p(SystemUtils.USER_DIR);            // D:\L\git\epitome
        p(SystemUtils.USER_COUNTRY);        // CN
        p(SystemUtils.USER_TIMEZONE);       // Asia/Shanghai
        p(SystemUtils.USER_LANGUAGE);       // zh
        p(SystemUtils.OS_ARCH);             // amd64
        p(SystemUtils.OS_NAME);             // Windows 10
        p(SystemUtils.OS_VERSION);          // 10.0
        p(SystemUtils.IS_JAVA_1_8);         // true
        p(SystemUtils.JAVA_VM_NAME);        // Java HotSpot(TM) 64-Bit Server VM
        p(SystemUtils.JAVA_VM_INFO);        // mixed mode
        p(SystemUtils.JAVA_VENDOR);         // Oracle Corporation
        p(SystemUtils.JAVA_VENDOR_URL);     // http://java.oracle.com/
        p(SystemUtils.JAVA_VERSION);        // 1.8.0_221
        p(SystemUtils.JAVA_HOME);           // D:\Java\jdk1.8.0_221\jre
        p(SystemUtils.JAVA_CLASS_VERSION);  // 52.0
        p(SystemUtils.JAVA_CLASS_PATH);     // ...
        p(SystemUtils.FILE_ENCODING);       // UTF-8
    }

}
