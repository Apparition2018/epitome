package org.ljh.druid.controller;

import com.alibaba.druid.pool.DruidDataSource;
import org.ljh.druid.entity.City;
import org.ljh.druid.service.CityService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

import java.util.List;
import java.util.Map;

/**
 * CityController — 业务接口 + 连接池指标查询
 * <p>业务接口用于让连接池产生真实调用；{@code /monitor} 直接读取
 * {@link DruidDataSource} 暴露连接池实时指标，便于脱离监控台也能快速查看。</p>
 *
 * @author ljh
 * @since 2026/8/13
 */
@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;
    private final DruidDataSource dataSource;

    public CityController(CityService cityService, DataSource dataSource) {
        this.cityService = cityService;
        // Druid starter 自动装配的即为 DruidDataSource，此处做强转以读取池指标
        this.dataSource = (DruidDataSource) dataSource;
    }

    @GetMapping
    public List<City> list() {
        return cityService.listAll();
    }

    @GetMapping("/{id}")
    public City get(@PathVariable Integer id) {
        return cityService.findById(id);
    }

    @PostMapping
    public int add(@RequestBody City city) {
        return cityService.insert(city);
    }

    @PutMapping
    public int update(@RequestBody City city) {
        return cityService.update(city);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable Integer id) {
        return cityService.delete(id);
    }

    /**
     * 连接池实时指标（与 Druid 监控台展示的数据同源）
     */
    @GetMapping("/monitor")
    public Map<String, Object> monitor() {
        return Map.of(
                "activeCount", dataSource.getActiveCount(),
                "activePeak", dataSource.getActivePeak(),
                "poolingCount", dataSource.getPoolingCount(),
                "maxActive", dataSource.getMaxActive(),
                "minIdle", dataSource.getMinIdle(),
                "initialSize", dataSource.getInitialSize(),
                "connectCount", dataSource.getConnectCount(),
                "closeCount", dataSource.getCloseCount(),
                "waitThreadCount", dataSource.getWaitThreadCount(),
                // 已执行的业务 SQL 数量（stat filter 统计）
                "sqlExecuteCount", dataSource.getDataSourceStat().getSqlStatMap().size()
        );
    }
}
