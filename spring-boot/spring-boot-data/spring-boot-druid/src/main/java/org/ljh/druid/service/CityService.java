package org.ljh.druid.service;

import org.ljh.druid.entity.City;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CityService — 基于 JdbcTemplate 的城市业务
 * <p>所有 SQL 都通过 Druid 连接池获取连接执行，天然被 Druid 的
 * {@code stat} 监控统计，可前往 /druid 监控台查看 SQL 与连接池指标。</p>
 *
 * @author ljh
 * @since 2026/8/13
 */
@Service
public class CityService {

    private final JdbcTemplate jdbcTemplate;

    public CityService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<City> CITY_ROW_MAPPER = (rs, i) -> new City(
            rs.getInt("ID"),
            rs.getString("Name"),
            rs.getString("CountryCode"),
            rs.getString("District"),
            rs.getInt("Population")
    );

    public List<City> listAll() {
        return jdbcTemplate.query("SELECT ID, Name, CountryCode, District, Population FROM city", CITY_ROW_MAPPER);
    }

    public City findById(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT ID, Name, CountryCode, District, Population FROM city WHERE ID = ?",
                CITY_ROW_MAPPER, id);
    }

    public int insert(City city) {
        return jdbcTemplate.update(
                "INSERT INTO city(Name, CountryCode, District, Population) VALUES (?, ?, ?, ?)",
                city.getName(), city.getCountryCode(), city.getDistrict(), city.getPopulation());
    }

    public int update(City city) {
        return jdbcTemplate.update(
                "UPDATE city SET Name = ?, CountryCode = ?, District = ?, Population = ? WHERE ID = ?",
                city.getName(), city.getCountryCode(), city.getDistrict(), city.getPopulation(), city.getId());
    }

    public int delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM city WHERE ID = ?", id);
    }
}
