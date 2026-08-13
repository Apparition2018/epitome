package org.ljh.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.ljh.druid.entity.City;
import org.ljh.druid.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Druid 连接池演示测试
 *
 * @author ljh
 * @since 2026/8/13
 */
@SpringBootTest(classes = DruidApplication.class)
public class DruidApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CityService cityService;

    @Test
    void contextLoads() {
        // 验证数据源确实被替换成了 Druid
        assertThat(dataSource).isInstanceOf(DruidDataSource.class);
    }

    @Test
    void testCrudThroughDruidPool() {
        // 记录插入前的总数
        int before = cityService.listAll().size();

        // insert
        City city = new City(null, "DemoCity", "CHN", "Demo", 100000);
        assertThat(cityService.insert(city)).isEqualTo(1);
        assertThat(cityService.listAll()).hasSize(before + 1);

        // select by id
        City inserted = cityService.listAll().stream()
                .filter(c -> "DemoCity".equals(c.getName()))
                .findFirst().orElseThrow();
        assertThat(cityService.findById(inserted.getId()).getPopulation()).isEqualTo(100000);

        // update
        inserted.setPopulation(200000);
        assertThat(cityService.update(inserted)).isEqualTo(1);
        assertThat(cityService.findById(inserted.getId()).getPopulation()).isEqualTo(200000);

        // delete
        assertThat(cityService.delete(inserted.getId())).isEqualTo(1);
        assertThat(cityService.listAll()).hasSize(before);
    }

    @Test
    void testPoolMetrics() {
        DruidDataSource ds = (DruidDataSource) dataSource;
        // 触发一次业务查询，让连接池产生真实连接
        cityService.listAll();
        // 已建立过连接
        assertThat(ds.getConnectCount()).isGreaterThanOrEqualTo(1);
        // 连接数不超过池上限
        assertThat(ds.getActiveCount()).isLessThanOrEqualTo(ds.getMaxActive());
        // 初始连接数与配置一致
        assertThat(ds.getInitialSize()).isEqualTo(5);
        // 监控统计已生效（stat filter 开启，连接管理有统计）
        assertThat(ds.getDataSourceStat().getConnectionStat().getConnectCount()).isGreaterThanOrEqualTo(1);
        System.out.printf("Druid 连接池指标: active=%d, pooling=%d, connectCount=%d%n",
                ds.getActiveCount(), ds.getPoolingCount(), ds.getConnectCount());
    }
}
