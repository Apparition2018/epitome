package org.ljh.mqtt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.ljh.mqtt.domain.TbLamp;
import org.ljh.mqtt.mapper.TbLampMapper;
import org.ljh.mqtt.service.TbLampService;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.Map;

/**
 * @author ljh
 * @since 2026/7/14 3:31
*/
@Service
public class TbLampServiceImpl extends ServiceImpl<TbLampMapper, TbLamp> implements TbLampService {

    @SneakyThrows
    @Override
    public void updateStatus(String payload) {
        Map<String, Object> data = new ObjectMapper().readValue(payload, new TypeReference<>() {});
        String deviceId = data.get("deviceId").toString();
        Integer status = Integer.parseInt(data.get("status").toString());

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
