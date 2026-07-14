package springboot.mqtt.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName(value = "tb_lamp_status")
@Data
public class TbLampStatus {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String deviceId;
    /** 0: 关灯 1: 开灯 */
    private Integer status;
    private Date createTime;
}
