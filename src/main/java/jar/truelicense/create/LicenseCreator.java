package jar.truelicense.create;

import de.schlichtherle.license.*;
import jar.truelicense.CustomKeyStoreParam;
import jar.truelicense.CustomLicenseManager;
import lombok.extern.slf4j.Slf4j;

import javax.security.auth.x500.X500Principal;
import java.io.File;
import java.text.MessageFormat;
import java.util.prefs.Preferences;

/**
 * License Creator
 *
 * @author ljh
 * @since 2024/1/18 11:51
 */
@Slf4j
public class LicenseCreator {
    private final static X500Principal DEFAULT_HOLDER_AND_ISSUER = new X500Principal("CN=ljh, OU=ljh, O=ljh, L=ZS, ST=GD, C=CN");

    private final LicenseCreatorParam PARAM;

    public LicenseCreator(LicenseCreatorParam param) {
        this.PARAM = param;
    }

    public boolean generateLicense() {
        try {
            LicenseManager licenseManager = new CustomLicenseManager(initLicenseParam());
            LicenseContent licenseContent = initLicenseContent();
            licenseManager.store(licenseContent, new File(PARAM.getLicensePath()));
            return true;
        } catch (Exception e) {
            log.error(MessageFormat.format("证书生成失败：{0}", PARAM), e);
            return false;
        }
    }

    private LicenseParam initLicenseParam() {
        Preferences preferences = Preferences.userNodeForPackage(LicenseCreator.class);
        CipherParam cipherParam = new DefaultCipherParam(PARAM.getStorePass());
        KeyStoreParam privateStoreParam = new CustomKeyStoreParam(LicenseCreator.class,
            PARAM.getPrivateKeyStorePath(), PARAM.getPrivateAlias(),
            PARAM.getStorePass(), PARAM.getKeyPass());
		return new DefaultLicenseParam(PARAM.getSubject(), preferences, privateStoreParam, cipherParam);
    }


    private LicenseContent initLicenseContent() {
        LicenseContent licenseContent = new LicenseContent();
        licenseContent.setHolder(DEFAULT_HOLDER_AND_ISSUER);
        licenseContent.setIssuer(DEFAULT_HOLDER_AND_ISSUER);
        licenseContent.setSubject(PARAM.getSubject());
        licenseContent.setIssued(PARAM.getIssuedTime());
        licenseContent.setNotBefore(PARAM.getIssuedTime());
        licenseContent.setNotAfter(PARAM.getExpiryTime());
        licenseContent.setConsumerType(PARAM.getConsumerType());
        licenseContent.setConsumerAmount(PARAM.getConsumerAmount());
        licenseContent.setInfo(PARAM.getInfo());
        licenseContent.setExtra(PARAM.getLicenseExtraContent());
        return licenseContent;
    }
}
