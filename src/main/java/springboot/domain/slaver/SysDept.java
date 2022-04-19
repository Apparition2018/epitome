package springboot.domain.slaver;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysDept implements Serializable {
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

    private static final long serialVersionUID = 1L;
}