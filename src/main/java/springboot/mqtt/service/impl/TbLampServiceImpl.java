package springboot.mqtt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import springboot.mqtt.domain.TbLamp;
import springboot.mqtt.mapper.TbLampMapper;
import springboot.mqtt.service.TbLampService;

import java.util.Date;
import java.util.Map;

/**
 * @author ljh
 * @since 2026/7/14 3:31
*/
@Service
public class TbLampServiceImpl extends ServiceImpl<TbLampMapper, TbLamp> implements TbLampService{

    @SneakyThrows
    @Override
    public void updateStatus(String payload) {
        Map<String, Object> data = new ObjectMapper().readValue(payload, new TypeReference<>() {});
        String deviceId = data.get("deviceId").toString();
        Integer status = Integer.parseInt(data.get("online").toString());

        LambdaQueryWrapper<TbLamp> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TbLamp::getDeviceId, deviceId);
        TbLamp tbLamp = this.getOne(lambdaQueryWrapper);
        if(tbLamp == null) {
            tbLamp = new TbLamp();
            tbLamp.setDeviceId(deviceId);
            tbLamp.setStatus(status);
            tbLamp.setCreateTime(new Date());
            tbLamp.setUpdateTime(new Date());
            this.save(tbLamp);
        } else {
            tbLamp.setStatus(status);
            tbLamp.setUpdateTime(new Date());
            this.updateById(tbLamp);
        }
    }
}
