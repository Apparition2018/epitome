package org.ljh.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ljh.mybatis.entity.Country;

import java.util.List;

/**
 * CountryMapper
 *
 * @author ljh
 * @since 2026/7/20
 */
@Mapper
public interface CountryMapper {

    List<Country> findAll();

    Country findByCode(@Param("code") String code);
}
