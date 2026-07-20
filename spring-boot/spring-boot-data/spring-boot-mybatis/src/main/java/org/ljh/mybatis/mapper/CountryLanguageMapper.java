package org.ljh.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ljh.mybatis.entity.CountryLanguage;

import java.util.List;

/**
 * CountryLanguageMapper
 *
 * @author ljh
 * @since 2026/7/20
 */
@Mapper
public interface CountryLanguageMapper {

    List<CountryLanguage> findAll();

    List<CountryLanguage> findByCountryCode(@Param("countryCode") String countryCode);
}
