package springboot.mqtt.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName(value = "tb_lamp")
@Data
public class TbLamp {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String deviceId;
    /** 1: 上线 0: 下线 */
    private Integer status;
    private Date createTime;
    private Date updateTime;
}
