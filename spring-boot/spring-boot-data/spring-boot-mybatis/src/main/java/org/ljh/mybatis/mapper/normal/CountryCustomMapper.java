package org.ljh.mybatis.mapper.normal;

import org.ljh.mybatis.entity.normal.Country;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CountryCustomMapper {

    /**
     * JOIN 查询国家及其下所有城市
     * 使用 columnPrefix="city_" 避免同名字段冲突
     */
    Country selectCountryWithCities(@Param("code") String code);

    /**
     * JOIN 查询国家及其下所有城市和语言（两个 collection 同时填充）
     * 使用 columnPrefix="city_" 和 "lang_" 避免同名字段冲突
     */
    Country selectCountryWithAll(@Param("code") String code);
}
