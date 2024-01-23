package jar.truelicense;

import jar.truelicense.create.LicenseCreator;
import jar.truelicense.create.LicenseCreatorParam;
import jar.truelicense.extra.LicenseExtraContent;
import jar.truelicense.extra.factory.RuntimeUtils;
import knowledge.network.NetworkInterfaceUtils;
import l.demo.Demo;
import org.apache.commons.lang3.time.DateUtils;

import java.net.InetAddress;
import java.util.Collections;

/**
 * <a href="https://zhuanlan.zhihu.com/p/648911291">TrueLicense 实现 License 验证</a>
 * <pre>
 * keytool -genkeypair -keysize 1024 -validity 3650 -alias privateKey -keyalg RSA -keystore D:\Liang\git\epitome\src\main\resources\demo\privateKey.keystore -storepass abc123 -keypass abc123 -dname "CN=ljh, OU=ljh, O=ljh, L=ZS, ST=GD, C=CN"
 * keytool -exportcert -alias privateKey -keystore D:\Liang\git\epitome\src\main\resources\demo\privateKey.keystore -storepass abc123 -file D:\Liang\git\epitome\src\main\resources\demo\certfile.cer
 * keytool -import -alias publicCert -file D:\Liang\git\epitome\src\main\resources\demo\certfile.cer -keystore D:\Liang\git\epitome\src\main\resources\demo\publicCert.keystore -storepass abc123
 * </pre>
 *
 * @author ljh
 * @since 2023/2/23 15:54
 */
public class TrueLicenseDemo extends Demo {

    public static void main(String[] args) throws Exception {
        InetAddress inetAddress = InetAddress.getLocalHost();
        LicenseExtraContent extraContent = new LicenseExtraContent();
        extraContent.setIpAddress(Collections.singletonList(inetAddress.getHostAddress()));
        extraContent.setMacAddress(Collections.singletonList(NetworkInterfaceUtils.getMacByInetAddress(inetAddress)));
        extraContent.setCpuSerial(RuntimeUtils.getWindowsSerial("wmic cpu get processorid"));
        extraContent.setMainBoardSerial(RuntimeUtils.getWindowsSerial("wmic baseboard get serialnumber"));
        LicenseCreatorParam param = new LicenseCreatorParam();
        param.setSubject("ljh-license");
        param.setPrivateAlias("privateKey");
        param.setKeyPass("abc123");
        param.setStorePass("abc123");
        param.setLicensePath(DEMO_DIR_PATH + "license.lic");
        param.setPrivateKeyStorePath(DEMO_DIR_PATH + "privateKey.keystore");
        param.setExpiryTime(DateUtils.addDays(param.getIssuedTime(), 1));
        param.setLicenseExtraContent(extraContent);
        LicenseCreator licenseCreator = new LicenseCreator(param);
        licenseCreator.generateLicense();
    }
}
