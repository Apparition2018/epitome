package org.ljh.mybatis;

import org.junit.jupiter.api.Test;
import org.ljh.mybatis.mapper.CityMapper;
import org.ljh.mybatis.mapper.CountryLanguageMapper;
import org.ljh.mybatis.mapper.CountryMapper;
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
    void testFindAllCities() {
        assertThat(cityMapper.findAll()).isNotEmpty();
    }

    @Test
    void testFindAllCountries() {
        assertThat(countryMapper.findAll()).isNotEmpty();
    }

    @Test
    void testFindAllLanguages() {
        assertThat(countryLanguageMapper.findAll()).isNotEmpty();
    }
}
