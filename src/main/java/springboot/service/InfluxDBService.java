package springboot.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * InfluxDB
 * database             数据库
 * measurement          表
 * points               行
 * -    time            主索引，数据记录时间（会自动生成）
 * -    tags            有索引的属性
 * -    fields          没有索引的属性
 * <p>
 * 时间序列数据的存储和计算 - 概述：https://developer.aliyun.com/article/104243
 * Influxdb 详解：https://www.cnblogs.com/gaoguangjun/p/8513054.html
 * 使用时序数据库 InfluxDB：https://blog.didispace.com/spring-boot-learning-2-6-3/
 *
 * @author ljh
 * created on 2021/9/3 22:44
 */
@Service
@Slf4j
@AllArgsConstructor
public class InfluxDBService {

    private final InfluxDB influxDB;

    // @Scheduled(fixedRate = 2000)
    public void writeQPS() {
        int count = new Random().nextInt(100);

        Point point = Point.measurement("ApiQPS")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("url", "/hello")
                .addField("count", count)
                .build();

        // void         write(final String database, final String retentionPolicy, final Point point)
        // retentionPolicy      保存策略
        influxDB.write("test", "autogen", point);

        log.info("上报统计数据：" + count);
    }

}
