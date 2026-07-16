package org.ljh.mqtt.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import org.ljh.mqtt.domain.TbLampStatus;
import org.ljh.mqtt.mapper.TbLampStatusMapper;
import org.ljh.mqtt.service.TbLampStatusService;
import org.springframework.stereotype.Service;

/**
 * @author ljh
 * @since 2026/7/14 3:31
*/
@Service
public class TbLampStatusServiceImpl extends ServiceImpl<TbLampStatusMapper, TbLampStatus> implements TbLampStatusService {
}
