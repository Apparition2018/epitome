package org.ljh.mybatis;

import org.junit.jupiter.api.Test;
import org.ljh.mybatis.mapper.dynamic.CityMapper;
import org.ljh.mybatis.mapper.dynamic.CountryLanguageMapper;
import org.ljh.mybatis.mapper.dynamic.CountryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * dynamic 模块测试 — MyBatis3DynamicSql 方式
 *
 * @author ljh
 * @since 2026/7/24
 */
@SpringBootTest(classes = MybatisDynamicApplication.class)
public class MybatisDynamicApplicationTests {

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private CountryLanguageMapper countryLanguageMapper;

    // ==================== basic CRUD ====================

    @Test
    void contextLoads() {
        assertThat(cityMapper).isNotNull();
        assertThat(countryMapper).isNotNull();
        assertThat(countryLanguageMapper).isNotNull();
    }

    @Test
    void testSelectAllCities() {
        assertThat(cityMapper.select(c -> c)).isNotEmpty();
    }

    @Test
    void testSelectAllCountries() {
        assertThat(countryMapper.select(c -> c)).isNotEmpty();
    }

    @Test
    void testSelectAllLanguages() {
        assertThat(countryLanguageMapper.select(c -> c)).isNotEmpty();
    }

    @Test
    void testSelectCityByPrimaryKey() {
        assertThat(cityMapper.selectByPrimaryKey(1)).isPresent();
    }

    @Test
    void testSelectCountryByPrimaryKey() {
        assertThat(countryMapper.selectByPrimaryKey("CHN")).isPresent();
    }

    @Test
    void testCount() {
        assertThat(cityMapper.count(c -> c)).isPositive();
    }
}
