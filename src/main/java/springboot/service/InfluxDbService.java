package springboot.service;

import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * InfluxDbService
 *
 * @author ljh
 * created on 2021/9/3 22:44
 */
@Slf4j
@Service
public class InfluxDbService {

    private final InfluxDB influxDB;

    public InfluxDbService(InfluxDB influxDB) {
        this.influxDB = influxDB;
    }

    // @Scheduled(fixedRate = 2000)
    public void writeQPS() {
        // 模拟要上报的统计数据
        int count = (int) (Math.random() * 100);

        Point point = Point.measurement("ApiQPS")   // ApiQPS表
                .tag("url", "/hello")               // url字段
                .addField("count", count)           // 统计数据
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)  // 时间
                .build();

        // 往test库写数据
        influxDB.write("test", "autogen", point);

        log.info("上报统计数据：" + count);
    }

}
