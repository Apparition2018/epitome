package org.ljh.mqtt.service;

import com.baomidou.mybatisplus.spring.service.IService;
import org.ljh.mqtt.domain.TbLamp;

/**
 * @author ljh
 * @since 2026/7/14 3:31
 */
public interface TbLampService extends IService<TbLamp> {

    void updateStatus(String payload);
}
