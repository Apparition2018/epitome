package jar.truelicense.extra.factory;

/**
 * Windows LicenseExtraContent 工厂
 *
 * @author ljh
 * @since 2024/1/18 11:33
 */
public class WindowsLicenseExtraContentFactory extends LicenseExtraContentFactory {

    @Override
    protected String getCPUSerial() throws Exception {
        return RuntimeUtils.getWindowsSerial("wmic cpu get processorid");
    }

    @Override
    protected String getMainBoardSerial() throws Exception {
        return RuntimeUtils.getWindowsSerial("wmic baseboard get serialnumber");
    }
}
