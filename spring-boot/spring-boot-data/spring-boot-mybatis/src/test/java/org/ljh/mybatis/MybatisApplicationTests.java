package org.ljh.mybatis;

import org.junit.jupiter.api.Test;
import org.ljh.mybatis.entity.normal.CityExample;
import org.ljh.mybatis.entity.normal.CountryExample;
import org.ljh.mybatis.entity.normal.CountryLanguageExample;
import org.ljh.mybatis.mapper.normal.CityMapper;
import org.ljh.mybatis.mapper.normal.CountryLanguageMapper;
import org.ljh.mybatis.mapper.normal.CountryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * MybatisApplicationTests
 *
 * @author ljh
 * @since 2026/7/20 15:23
 */
@SpringBootTest
public class MybatisApplicationTests {

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private CountryLanguageMapper countryLanguageMapper;

    @Test
    void contextLoads() {
        assertThat(cityMapper).isNotNull();
        assertThat(countryMapper).isNotNull();
        assertThat(countryLanguageMapper).isNotNull();
    }

    @Test
    void testSelectAllCities() {
        assertThat(cityMapper.selectByExample(new CityExample())).isNotEmpty();
    }

    @Test
    void testSelectAllCountries() {
        assertThat(countryMapper.selectByExample(new CountryExample())).isNotEmpty();
    }

    @Test
    void testSelectAllLanguages() {
        assertThat(countryLanguageMapper.selectByExample(new CountryLanguageExample())).isNotEmpty();
    }
}
