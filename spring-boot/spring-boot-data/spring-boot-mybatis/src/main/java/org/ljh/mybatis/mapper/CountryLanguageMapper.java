package org.ljh.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.ljh.mybatis.entity.CountryLanguage;
import org.ljh.mybatis.entity.CountryLanguageExample;
import org.ljh.mybatis.entity.CountryLanguageKey;

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