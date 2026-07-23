package org.ljh.mybatis.mapper.normal;

import org.apache.ibatis.annotations.Param;
import org.ljh.mybatis.entity.normal.CountryLanguage;
import org.ljh.mybatis.entity.normal.CountryLanguageExample;
import org.ljh.mybatis.entity.normal.CountryLanguageKey;

import java.util.List;

public interface CountryLanguageMapper {
    long countByExample(CountryLanguageExample example);

    int deleteByExample(CountryLanguageExample example);

    int deleteByPrimaryKey(CountryLanguageKey key);

    int insert(CountryLanguage row);

    int insertSelective(CountryLanguage row);

    List<CountryLanguage> selectByExample(CountryLanguageExample example);

    CountryLanguage selectByPrimaryKey(CountryLanguageKey key);

    int updateByExampleSelective(@Param("row") CountryLanguage row, @Param("example") CountryLanguageExample example);

    int updateByExample(@Param("row") CountryLanguage row, @Param("example") CountryLanguageExample example);

    int updateByPrimaryKeySelective(CountryLanguage row);

    int updateByPrimaryKey(CountryLanguage row);
}
