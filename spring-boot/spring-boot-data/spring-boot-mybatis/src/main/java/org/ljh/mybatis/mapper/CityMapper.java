package org.ljh.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ljh.mybatis.entity.City;

import java.util.List;

/**
 * CityMapper
 *
 * @author ljh
 * @since 2026/7/20
 */
@Mapper
public interface CityMapper {

    List<City> findAll();

    City findById(@Param("id") Integer id);

    List<City> findByCountryCode(@Param("countryCode") String countryCode);
}
