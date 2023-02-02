package springboot.domain.slaver;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * SysRole
 *
 * @author ljh
 * @since 2022/4/20 10:09
 */
@Data
public class SysRole implements Serializable {
    @Serial
    private static final long serialVersionUID = -5977395569664699247L;
    private Long roleId;
    private String roleName;
    private String roleKey;
    private Integer roleSort;
    private String dataScope;
    private String status;
    private String delFlag;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String remark;
}
