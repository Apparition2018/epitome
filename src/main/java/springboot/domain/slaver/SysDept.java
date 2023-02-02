package springboot.domain.slaver;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * SysDept
 *
 * @author ljh
 * @since 2022/4/20 10:08
 */
@Data
public class SysDept implements Serializable {
    @Serial
    private static final long serialVersionUID = -4287655121190359966L;
    private Long deptId;
    private Long parentId;
    private String ancestors;
    private String deptName;
    private Integer orderNum;
    private String leader;
    private String phone;
    private String email;
    private String status;
    private String delFlag;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String parentName;
}
