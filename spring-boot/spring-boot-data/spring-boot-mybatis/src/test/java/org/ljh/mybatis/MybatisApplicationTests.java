package org.ljh.mybatis;

import org.junit.jupiter.api.Test;
import org.ljh.mybatis.entity.normal.*;
import org.ljh.mybatis.mapper.normal.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * normal 模块测试 — MyBatis3 + XML 方式
 *
 * @author ljh
 * @since 2026/7/20 15:23
 */
@SpringBootTest(classes = MybatisApplication.class)
public class MybatisApplicationTests {

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private CountryLanguageMapper countryLanguageMapper;

    @Autowired
    private CityCustomMapper cityCustomMapper;

    @Autowired
    private CountryCustomMapper countryCustomMapper;

    // ==================== basic CRUD ====================

    @Test
    void contextLoads() {
        assertThat(cityMapper).isNotNull();
        assertThat(countryMapper).isNotNull();
        assertThat(countryLanguageMapper).isNotNull();
        assertThat(cityCustomMapper).isNotNull();
        assertThat(countryCustomMapper).isNotNull();
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

    // ==================== custom JOIN 关联查询 ====================

    /**
     * City → Country: association 嵌套查询
     */
    @Test
    void testSelectCityWithCountry() {
        City city = cityCustomMapper.selectCityWithCountry(1);
        assertThat(city).isNotNull();
        assertThat(city.getName()).isNotNull();
        assertThat(city.getCountry()).isNotNull();
        assertThat(city.getCountry().getName()).isNotNull();
        System.out.printf("City: %s → Country: %s%n", city.getName(), city.getCountry().getName());
    }

    /**
     * Country → Cities: collection 嵌套查询
     */
    @Test
    void testSelectCountryWithCities() {
        Country country = countryCustomMapper.selectCountryWithCities("CHN");
        assertThat(country).isNotNull();
        assertThat(country.getName()).isEqualTo("China");
        assertThat(country.getCities()).isNotEmpty();
        country.getCities().forEach(city ->
            System.out.printf("  City: %s (Population: %d)%n", city.getName(), city.getPopulation()));
    }

    /**
     * Country → Cities + Languages: 两个 collection 同时填充
     */
    @Test
    void testSelectCountryWithAll() {
        Country country = countryCustomMapper.selectCountryWithAll("CHN");
        assertThat(country).isNotNull();
        assertThat(country.getCities()).isNotEmpty();
        assertThat(country.getCountryLanguages()).isNotEmpty();
        System.out.printf("Country: %s, cities: %d, languages: %d%n",
            country.getName(), country.getCities().size(), country.getCountryLanguages().size());
    }
}
