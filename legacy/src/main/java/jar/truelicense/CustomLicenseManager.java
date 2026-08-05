package jar.truelicense;

import de.schlichtherle.license.*;
import de.schlichtherle.xml.GenericCertificate;
import jar.truelicense.extra.LicenseExtraContent;
import jar.truelicense.extra.factory.LicenseExtraContentFactory;
import jar.truelicense.extra.factory.LinuxLicenseExtraContentFactory;
import jar.truelicense.extra.factory.WindowsLicenseExtraContentFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

/**
 * 自定义 LicenseManager
 *
 * @author ljh
 * @since 2024/1/18 9:48
 */
@Slf4j
public class CustomLicenseManager extends LicenseManager {

    private static final int BUFF_SIZE = 1028 * 8;

    public CustomLicenseManager(LicenseParam param) {
        super(param);
    }

    // @Override
    // protected synchronized byte[] create(LicenseContent content, LicenseNotary notary) throws Exception {
    //     initialize(content);
    //     this.validate2(content);
    //     final GenericCertificate certificate = notary.sign(content);
    //     return getPrivacyGuard().cert2key(certificate);
    // }

    @Override
    protected synchronized LicenseContent install(final byte[] key, final LicenseNotary notary) throws Exception {
        final GenericCertificate certificate = getPrivacyGuard().key2cert(key);
        notary.verify(certificate);
        final LicenseContent content = (LicenseContent) this.load(certificate.getEncoded());
        this.validate(content);
        setLicenseKey(key);
        setCertificate(certificate);

        return content;
    }

    @Override
    protected synchronized LicenseContent verify(final LicenseNotary notary) throws Exception {
        GenericCertificate certificate = getCertificate();

        // Load license key from preferences,
        final byte[] key = getLicenseKey();
        if (null == key) {
            throw new NoLicenseInstalledException(getLicenseParam().getSubject());
        }
        certificate = getPrivacyGuard().key2cert(key);
        notary.verify(certificate);
        final LicenseContent content = (LicenseContent) this.load(certificate.getEncoded());
        this.validate(content);
        setCertificate(certificate);

        return content;
    }

    @Override
    protected synchronized void validate(final LicenseContent content) throws LicenseContentException {
        /* 1. 调用父类 validate() */
        super.validate(content);

        /* 2. 检验 LicenseExtraContent */
        LicenseExtraContent expectedExtraContent = (LicenseExtraContent) content.getExtra();
        LicenseExtraContent serverExtraContent = this.getLicenseExtraContent();
        if (expectedExtraContent != null && serverExtraContent != null) {
            if (!checkAddress(expectedExtraContent.getIpAddress(), serverExtraContent.getIpAddress())) {
                throw new LicenseContentException("当前服务器 IP 地址不在授权范围内");
            }
            if (!checkAddress(expectedExtraContent.getMacAddress(), serverExtraContent.getMacAddress())) {
                throw new LicenseContentException("当前服务器 Mac 地址不在授权范围内");
            }
            if (!checkSerial(expectedExtraContent.getMainBoardSerial(), serverExtraContent.getMainBoardSerial())) {
                throw new LicenseContentException("当前服务器主板序列号不在授权范围内");
            }
            if (!checkSerial(expectedExtraContent.getCpuSerial(), serverExtraContent.getCpuSerial())) {
                throw new LicenseContentException("当前服务器的 CPU 序列号不在授权范围内");
            }
        } else {
            throw new LicenseContentException("检验 LicenseExtraContent 异常");
        }
    }

    protected synchronized void validate2(final LicenseContent content) throws LicenseContentException {
        final Date now = new Date();
        final Date notBefore = content.getNotBefore();
        final Date notAfter = content.getNotAfter();
        if (null != notBefore && now.before(notBefore)) {
            throw new LicenseContentException("license is not yet valid");
        }
        if (null != notAfter && now.after(notAfter)) {
            throw new LicenseContentException("license has expired");
        }
        final String consumerType = content.getConsumerType();
        if (null == consumerType) {
            throw new LicenseContentException("consumer type is null");
        }
    }

    /** 重写 XMLDecoder 解析 XML */
    private Object load(String encoded) throws IOException {
        try (BufferedInputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(encoded.getBytes(StandardCharsets.UTF_8)));
             XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(inputStream, BUFF_SIZE), null, null);) {
            return decoder.readObject();
        }
    }

    private LicenseExtraContent getLicenseExtraContent() throws LicenseContentException {
        String osName = System.getProperty("os.name").toLowerCase();
        LicenseExtraContentFactory extraContentFactory;

        if (osName.startsWith("windows")) {
            extraContentFactory = new WindowsLicenseExtraContentFactory();
        } else if (osName.startsWith("linux")) {
            extraContentFactory = new LinuxLicenseExtraContentFactory();
        } else {
            throw new LicenseContentException("unknown operating system");
        }

        return extraContentFactory.create();
    }

    private boolean checkAddress(List<String> expectedList, List<String> serverList) {
        if (expectedList != null && !expectedList.isEmpty()
            && serverList != null && !serverList.isEmpty()) {
            for (String expected : expectedList) {
                if (serverList.contains(expected.trim())) {
                    return true;
                }
            }
            return false;
        } else {
            return true;
        }
    }

    private boolean checkSerial(String expectedSerial, String serverSerial) {
        if (StringUtils.isNotBlank(expectedSerial) && StringUtils.isNotBlank(serverSerial)) {
            return expectedSerial.equals(serverSerial);
        } else {
            return true;
        }
    }
}
