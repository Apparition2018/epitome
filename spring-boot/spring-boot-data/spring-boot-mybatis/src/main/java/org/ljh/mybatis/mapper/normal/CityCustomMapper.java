package org.ljh.mybatis.mapper.normal;

import org.ljh.mybatis.entity.normal.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CityCustomMapper {

    /**
     * JOIN 查询城市及其所属国家
     * 使用 columnPrefix="country_" 避免同名字段冲突
     */
    City selectCityWithCountry(@Param("id") Integer id);
}
