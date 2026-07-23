package org.ljh.mybatis.mapper.dynamic;

import static org.ljh.mybatis.mapper.dynamic.CountryLanguageDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.ljh.mybatis.entity.dynamic.CountryLanguage;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.dsl.CountDSLCompleter;
import org.mybatis.dynamic.sql.dsl.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.dsl.SelectDSLCompleter;
import org.mybatis.dynamic.sql.dsl.UpdateDSL;
import org.mybatis.dynamic.sql.dsl.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface CountryLanguageMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<CountryLanguage>, CommonUpdateMapper {
    BasicColumn[] selectList = BasicColumn.columnList(countryCode, language, isOfficial, percentage);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CountryLanguageResult", value = {
        @Result(column="CountryCode", property="countryCode", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="Language", property="language", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="IsOfficial", property="isOfficial", jdbcType=JdbcType.CHAR),
        @Result(column="Percentage", property="percentage", jdbcType=JdbcType.DECIMAL)
    })
    List<CountryLanguage> selectMany(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CountryLanguageResult")
    Optional<CountryLanguage> selectOne(SelectStatementProvider selectStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, countryLanguage, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, countryLanguage, completer);
    }

    default int deleteByPrimaryKey(String countryCode_, String language_) {
        return delete(c -> 
            c.where(countryCode, isEqualTo(countryCode_))
            .and(language, isEqualTo(language_))
        );
    }

    default int insert(CountryLanguage row) {
        return MyBatis3Utils.insert(this::insert, row, countryLanguage, c ->
            c.withMappedColumn(countryCode)
            .withMappedColumn(language)
            .withMappedColumn(isOfficial)
            .withMappedColumn(percentage)
        );
    }

    default int insertMultiple(Collection<CountryLanguage> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, countryLanguage, c ->
            c.withMappedColumn(countryCode)
            .withMappedColumn(language)
            .withMappedColumn(isOfficial)
            .withMappedColumn(percentage)
        );
    }

    default int insertSelective(CountryLanguage row) {
        return MyBatis3Utils.insert(this::insert, row, countryLanguage, c ->
            c.withMappedColumnWhenPresent(countryCode, row::getCountryCode)
            .withMappedColumnWhenPresent(language, row::getLanguage)
            .withMappedColumnWhenPresent(isOfficial, row::getIsOfficial)
            .withMappedColumnWhenPresent(percentage, row::getPercentage)
        );
    }

    default Optional<CountryLanguage> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, countryLanguage, completer);
    }

    default List<CountryLanguage> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, countryLanguage, completer);
    }

    default List<CountryLanguage> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, countryLanguage, completer);
    }

    default Optional<CountryLanguage> selectByPrimaryKey(String countryCode_, String language_) {
        return selectOne(c ->
            c.where(countryCode, isEqualTo(countryCode_))
            .and(language, isEqualTo(language_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, countryLanguage, completer);
    }

    static UpdateDSL updateAllColumns(CountryLanguage row, UpdateDSL dsl) {
        return dsl.set(countryCode).equalTo(row::getCountryCode)
                .set(language).equalTo(row::getLanguage)
                .set(isOfficial).equalTo(row::getIsOfficial)
                .set(percentage).equalTo(row::getPercentage);
    }

    static UpdateDSL updateSelectiveColumns(CountryLanguage row, UpdateDSL dsl) {
        return dsl.set(countryCode).equalToWhenPresent(row::getCountryCode)
                .set(language).equalToWhenPresent(row::getLanguage)
                .set(isOfficial).equalToWhenPresent(row::getIsOfficial)
                .set(percentage).equalToWhenPresent(row::getPercentage);
    }

    default int updateByPrimaryKey(CountryLanguage row) {
        return update(c ->
            c.set(isOfficial).equalTo(row::getIsOfficial)
            .set(percentage).equalTo(row::getPercentage)
            .where(countryCode, isEqualTo(row::getCountryCode))
            .and(language, isEqualTo(row::getLanguage))
        );
    }

    default int updateByPrimaryKeySelective(CountryLanguage row) {
        return update(c ->
            c.set(isOfficial).equalToWhenPresent(row::getIsOfficial)
            .set(percentage).equalToWhenPresent(row::getPercentage)
            .where(countryCode, isEqualTo(row::getCountryCode))
            .and(language, isEqualTo(row::getLanguage))
        );
    }
}