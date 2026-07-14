package springboot.mqtt.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import springboot.mqtt.domain.TbLampStatus;
import springboot.mqtt.mapper.TbLampStatusMapper;
import springboot.mqtt.service.TbLampStatusService;

/**
 * @author ljh
 * @since 2026/7/14 3:31
*/
@Service
public class TbLampStatusServiceImpl extends ServiceImpl<TbLampStatusMapper, TbLampStatus> implements TbLampStatusService{
}
