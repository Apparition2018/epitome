package org.ljh.mybatis.mapper.normal;

import org.apache.ibatis.annotations.Param;
import org.ljh.mybatis.entity.normal.Country;
import org.ljh.mybatis.entity.normal.CountryExample;

import java.util.List;

public interface CountryMapper {
    long countByExample(CountryExample example);

    int deleteByExample(CountryExample example);

    int deleteByPrimaryKey(String code);

    int insert(Country row);

    int insertSelective(Country row);

    List<Country> selectByExample(CountryExample example);

    Country selectByPrimaryKey(String code);

    int updateByExampleSelective(@Param("row") Country row, @Param("example") CountryExample example);

    int updateByExample(@Param("row") Country row, @Param("example") CountryExample example);

    int updateByPrimaryKeySelective(Country row);

    int updateByPrimaryKey(Country row);
}
