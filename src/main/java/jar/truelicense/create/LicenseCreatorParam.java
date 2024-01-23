package jar.truelicense.create;

import com.fasterxml.jackson.annotation.JsonFormat;
import jar.truelicense.extra.LicenseExtraContent;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 生成 License 需要的参数
 *
 * @author ljh
 * @since 2024/1/17 17:29
 */
@Data
public class LicenseCreatorParam implements Serializable {
    @Serial
    private static final long serialVersionUID = -5787663536211022172L;

    /** 证书 */
    private String subject;
    /** 密钥别称 */
    private String privateAlias;
    /** 密钥密码（不能让使用者知道） */
    private String keyPass;
    /** 访问密钥库的密码 */
    private String storePass;
    /** 证书生成路径 */
    private String licensePath;
    /** 密钥库存储路径 */
    private String privateKeyStorePath;
    /** 证书生效时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date issuedTime = new Date();
    /** 证书失效时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expiryTime = new Date();
    /** 用户类型 */
    private String consumerType = "user";
    /** 用户数量 */
    private Integer consumerAmount = 1;
    /** 描述信息 */
    private String info = "LJH License";
    /** 额外检验的服务器参数 */
    private LicenseExtraContent licenseExtraContent;
}
