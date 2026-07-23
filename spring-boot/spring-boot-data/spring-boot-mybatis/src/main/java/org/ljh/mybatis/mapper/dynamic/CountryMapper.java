package org.ljh.mybatis.mapper.dynamic;

import static org.ljh.mybatis.mapper.dynamic.CountryDynamicSqlSupport.*;
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
import org.ljh.mybatis.entity.dynamic.Country;
import org.ljh.mybatis.entity.handler.ContinentTypeHandler;
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
public interface CountryMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Country>, CommonUpdateMapper {
    BasicColumn[] selectList = BasicColumn.columnList(code, name, continent, region, surfaceArea, indepYear, population, lifeExpectancy, GNP, GNPOld, localName, governmentForm, headOfState, capital, code2);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CountryResult", value = {
        @Result(column="Code", property="code", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="Name", property="name", jdbcType=JdbcType.CHAR),
        @Result(column="Continent", property="continent", typeHandler=ContinentTypeHandler.class, jdbcType=JdbcType.VARCHAR),
        @Result(column="Region", property="region", jdbcType=JdbcType.CHAR),
        @Result(column="SurfaceArea", property="surfaceArea", jdbcType=JdbcType.DECIMAL),
        @Result(column="IndepYear", property="indepYear", jdbcType=JdbcType.SMALLINT),
        @Result(column="Population", property="population", jdbcType=JdbcType.INTEGER),
        @Result(column="LifeExpectancy", property="lifeExpectancy", jdbcType=JdbcType.DECIMAL),
        @Result(column="GNP", property="GNP", jdbcType=JdbcType.DECIMAL),
        @Result(column="GNPOld", property="GNPOld", jdbcType=JdbcType.DECIMAL),
        @Result(column="LocalName", property="localName", jdbcType=JdbcType.CHAR),
        @Result(column="GovernmentForm", property="governmentForm", jdbcType=JdbcType.CHAR),
        @Result(column="HeadOfState", property="headOfState", jdbcType=JdbcType.CHAR),
        @Result(column="Capital", property="capital", jdbcType=JdbcType.INTEGER),
        @Result(column="Code2", property="code2", jdbcType=JdbcType.CHAR)
    })
    List<Country> selectMany(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CountryResult")
    Optional<Country> selectOne(SelectStatementProvider selectStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, country, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, country, completer);
    }

    default int deleteByPrimaryKey(String code_) {
        return delete(c -> 
            c.where(code, isEqualTo(code_))
        );
    }

    default int insert(Country row) {
        return MyBatis3Utils.insert(this::insert, row, country, c ->
            c.withMappedColumn(code)
            .withMappedColumn(name)
            .withMappedColumn(continent)
            .withMappedColumn(region)
            .withMappedColumn(surfaceArea)
            .withMappedColumn(indepYear)
            .withMappedColumn(population)
            .withMappedColumn(lifeExpectancy)
            .withMappedColumn(GNP)
            .withMappedColumn(GNPOld)
            .withMappedColumn(localName)
            .withMappedColumn(governmentForm)
            .withMappedColumn(headOfState)
            .withMappedColumn(capital)
            .withMappedColumn(code2)
        );
    }

    default int insertMultiple(Collection<Country> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, country, c ->
            c.withMappedColumn(code)
            .withMappedColumn(name)
            .withMappedColumn(continent)
            .withMappedColumn(region)
            .withMappedColumn(surfaceArea)
            .withMappedColumn(indepYear)
            .withMappedColumn(population)
            .withMappedColumn(lifeExpectancy)
            .withMappedColumn(GNP)
            .withMappedColumn(GNPOld)
            .withMappedColumn(localName)
            .withMappedColumn(governmentForm)
            .withMappedColumn(headOfState)
            .withMappedColumn(capital)
            .withMappedColumn(code2)
        );
    }

    default int insertSelective(Country row) {
        return MyBatis3Utils.insert(this::insert, row, country, c ->
            c.withMappedColumnWhenPresent(code, row::getCode)
            .withMappedColumnWhenPresent(name, row::getName)
            .withMappedColumnWhenPresent(continent, row::getContinent)
            .withMappedColumnWhenPresent(region, row::getRegion)
            .withMappedColumnWhenPresent(surfaceArea, row::getSurfaceArea)
            .withMappedColumnWhenPresent(indepYear, row::getIndepYear)
            .withMappedColumnWhenPresent(population, row::getPopulation)
            .withMappedColumnWhenPresent(lifeExpectancy, row::getLifeExpectancy)
            .withMappedColumnWhenPresent(GNP, row::getGNP)
            .withMappedColumnWhenPresent(GNPOld, row::getGNPOld)
            .withMappedColumnWhenPresent(localName, row::getLocalName)
            .withMappedColumnWhenPresent(governmentForm, row::getGovernmentForm)
            .withMappedColumnWhenPresent(headOfState, row::getHeadOfState)
            .withMappedColumnWhenPresent(capital, row::getCapital)
            .withMappedColumnWhenPresent(code2, row::getCode2)
        );
    }

    default Optional<Country> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, country, completer);
    }

    default List<Country> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, country, completer);
    }

    default List<Country> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, country, completer);
    }

    default Optional<Country> selectByPrimaryKey(String code_) {
        return selectOne(c ->
            c.where(code, isEqualTo(code_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, country, completer);
    }

    static UpdateDSL updateAllColumns(Country row, UpdateDSL dsl) {
        return dsl.set(code).equalTo(row::getCode)
                .set(name).equalTo(row::getName)
                .set(continent).equalTo(row::getContinent)
                .set(region).equalTo(row::getRegion)
                .set(surfaceArea).equalTo(row::getSurfaceArea)
                .set(indepYear).equalTo(row::getIndepYear)
                .set(population).equalTo(row::getPopulation)
                .set(lifeExpectancy).equalTo(row::getLifeExpectancy)
                .set(GNP).equalTo(row::getGNP)
                .set(GNPOld).equalTo(row::getGNPOld)
                .set(localName).equalTo(row::getLocalName)
                .set(governmentForm).equalTo(row::getGovernmentForm)
                .set(headOfState).equalTo(row::getHeadOfState)
                .set(capital).equalTo(row::getCapital)
                .set(code2).equalTo(row::getCode2);
    }

    static UpdateDSL updateSelectiveColumns(Country row, UpdateDSL dsl) {
        return dsl.set(code).equalToWhenPresent(row::getCode)
                .set(name).equalToWhenPresent(row::getName)
                .set(continent).equalToWhenPresent(row::getContinent)
                .set(region).equalToWhenPresent(row::getRegion)
                .set(surfaceArea).equalToWhenPresent(row::getSurfaceArea)
                .set(indepYear).equalToWhenPresent(row::getIndepYear)
                .set(population).equalToWhenPresent(row::getPopulation)
                .set(lifeExpectancy).equalToWhenPresent(row::getLifeExpectancy)
                .set(GNP).equalToWhenPresent(row::getGNP)
                .set(GNPOld).equalToWhenPresent(row::getGNPOld)
                .set(localName).equalToWhenPresent(row::getLocalName)
                .set(governmentForm).equalToWhenPresent(row::getGovernmentForm)
                .set(headOfState).equalToWhenPresent(row::getHeadOfState)
                .set(capital).equalToWhenPresent(row::getCapital)
                .set(code2).equalToWhenPresent(row::getCode2);
    }

    default int updateByPrimaryKey(Country row) {
        return update(c ->
            c.set(name).equalTo(row::getName)
            .set(continent).equalTo(row::getContinent)
            .set(region).equalTo(row::getRegion)
            .set(surfaceArea).equalTo(row::getSurfaceArea)
            .set(indepYear).equalTo(row::getIndepYear)
            .set(population).equalTo(row::getPopulation)
            .set(lifeExpectancy).equalTo(row::getLifeExpectancy)
            .set(GNP).equalTo(row::getGNP)
            .set(GNPOld).equalTo(row::getGNPOld)
            .set(localName).equalTo(row::getLocalName)
            .set(governmentForm).equalTo(row::getGovernmentForm)
            .set(headOfState).equalTo(row::getHeadOfState)
            .set(capital).equalTo(row::getCapital)
            .set(code2).equalTo(row::getCode2)
            .where(code, isEqualTo(row::getCode))
        );
    }

    default int updateByPrimaryKeySelective(Country row) {
        return update(c ->
            c.set(name).equalToWhenPresent(row::getName)
            .set(continent).equalToWhenPresent(row::getContinent)
            .set(region).equalToWhenPresent(row::getRegion)
            .set(surfaceArea).equalToWhenPresent(row::getSurfaceArea)
            .set(indepYear).equalToWhenPresent(row::getIndepYear)
            .set(population).equalToWhenPresent(row::getPopulation)
            .set(lifeExpectancy).equalToWhenPresent(row::getLifeExpectancy)
            .set(GNP).equalToWhenPresent(row::getGNP)
            .set(GNPOld).equalToWhenPresent(row::getGNPOld)
            .set(localName).equalToWhenPresent(row::getLocalName)
            .set(governmentForm).equalToWhenPresent(row::getGovernmentForm)
            .set(headOfState).equalToWhenPresent(row::getHeadOfState)
            .set(capital).equalToWhenPresent(row::getCapital)
            .set(code2).equalToWhenPresent(row::getCode2)
            .where(code, isEqualTo(row::getCode))
        );
    }
}