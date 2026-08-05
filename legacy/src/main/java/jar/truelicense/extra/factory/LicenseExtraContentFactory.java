package jar.truelicense.extra.factory;

import jar.truelicense.extra.LicenseExtraContent;
import knowledge.network.NetworkInterfaceUtils;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.SocketException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LicenseExtraContent 工厂
 *
 * @author ljh
 * @since 2024/1/18 8:35
 */
@Slf4j
public abstract class LicenseExtraContentFactory {

    public LicenseExtraContent create() {
        LicenseExtraContent extraContent = new LicenseExtraContent();
        try {
            extraContent.setIpAddress(this.getIpAddress());
            extraContent.setMacAddress(this.getMacAddress());
            extraContent.setCpuSerial(this.getCPUSerial());
            extraContent.setMainBoardSerial(this.getMainBoardSerial());
        } catch (Exception e) {
            log.error("create LicenseExtraContent fail", e);
        }
        return extraContent;
    }

    public List<String> getIpAddress() throws Exception {
        List<String> ipAddressList = null;
        List<InetAddress> inetAddressList = NetworkInterfaceUtils.getInetAddressList();
        if (!inetAddressList.isEmpty()) {
            ipAddressList = inetAddressList.stream().map(InetAddress::getHostAddress).distinct().map(String::toLowerCase).collect(Collectors.toList());
        }
        return ipAddressList;
    }

    public List<String> getMacAddress() throws Exception {
        List<String> macAddressList = null;
        List<InetAddress> inetAddressList = NetworkInterfaceUtils.getInetAddressList();
        if (!inetAddressList.isEmpty()) {
            macAddressList = inetAddressList.stream().map(inetAddress -> {
				try {
					return NetworkInterfaceUtils.getMacByInetAddress(inetAddress);
				} catch (SocketException e) {
					throw new RuntimeException(e);
				}
			}).distinct().collect(Collectors.toList());
        }
        return macAddressList;
    }

    protected abstract String getCPUSerial() throws Exception;

    protected abstract String getMainBoardSerial() throws Exception;
}
