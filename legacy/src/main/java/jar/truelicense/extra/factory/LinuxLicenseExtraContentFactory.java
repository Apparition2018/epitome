package jar.truelicense.extra.factory;

/**
 * Linux LicenseExtraContent 工厂
 *
 * @author ljh
 * @since 2024/1/18 11:33
 */
public class LinuxLicenseExtraContentFactory extends LicenseExtraContentFactory {

    @Override
    protected String getCPUSerial() throws Exception {
        return RuntimeUtils.getLinuxSerial("/bin/bash", "-c", "dmidecode -t processor | grep 'ID' | awk -F ':' '{print $2}' | head -n 1");
    }

    @Override
    protected String getMainBoardSerial() throws Exception {
        return RuntimeUtils.getLinuxSerial("/bin/bash", "-c", "dmidecode | grep 'Serial Number' | awk -F ':' '{print $2}' | head -n 1");
    }
}
