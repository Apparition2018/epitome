package jar.truelicense.extra;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 自定义需要检验的参数
 *
 * @author ljh
 * @since 2024/1/17 17:25
 */
@Data
public class LicenseExtraContent implements Serializable {
    @Serial
    private static final long serialVersionUID = -6876644769814675067L;

    private List<String> ipAddress;

    private List<String> macAddress;

    private String cpuSerial;

    private String mainBoardSerial;
}
