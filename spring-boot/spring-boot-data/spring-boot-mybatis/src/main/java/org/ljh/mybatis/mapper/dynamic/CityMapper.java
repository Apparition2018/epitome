package org.ljh.mybatis.mapper.dynamic;

import static org.ljh.mybatis.mapper.dynamic.CityDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.ljh.mybatis.entity.dynamic.City;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.dsl.CountDSLCompleter;
import org.mybatis.dynamic.sql.dsl.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.dsl.SelectDSLCompleter;
import org.mybatis.dynamic.sql.dsl.UpdateDSL;
import org.mybatis.dynamic.sql.dsl.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface CityMapper extends CommonCountMapper, CommonDeleteMapper, CommonUpdateMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, name, countryCode, district, population);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true, keyProperty="row.id", keyColumn="ID")
    int insert(InsertStatementProvider<City> insertStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultipleWithGeneratedKeys")
    @Options(useGeneratedKeys=true, keyProperty="records.id", keyColumn="ID")
    int insertMultiple(@Param("insertStatement") String insertStatement, @Param("records") List<City> records);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CityResult", value = {
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="Name", property="name", jdbcType=JdbcType.CHAR),
        @Result(column="CountryCode", property="countryCode", jdbcType=JdbcType.CHAR),
        @Result(column="District", property="district", jdbcType=JdbcType.CHAR),
        @Result(column="Population", property="population", jdbcType=JdbcType.INTEGER)
    })
    List<City> selectMany(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CityResult")
    Optional<City> selectOne(SelectStatementProvider selectStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, city, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, city, completer);
    }

    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(City row) {
        return MyBatis3Utils.insert(this::insert, row, city, c ->
            c.withMappedColumn(name)
            .withMappedColumn(countryCode)
            .withMappedColumn(district)
            .withMappedColumn(population)
        );
    }

    default int insertMultiple(Collection<City> records) {
        return MyBatis3Utils.insertMultipleWithGeneratedKeys(this::insertMultiple, records, city, c ->
            c.withMappedColumn(name)
            .withMappedColumn(countryCode)
            .withMappedColumn(district)
            .withMappedColumn(population)
        );
    }

    default int insertSelective(City row) {
        return MyBatis3Utils.insert(this::insert, row, city, c ->
            c.withMappedColumnWhenPresent(name, row::getName)
            .withMappedColumnWhenPresent(countryCode, row::getCountryCode)
            .withMappedColumnWhenPresent(district, row::getDistrict)
            .withMappedColumnWhenPresent(population, row::getPopulation)
        );
    }

    default Optional<City> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, city, completer);
    }

    default List<City> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, city, completer);
    }

    default List<City> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, city, completer);
    }

    default Optional<City> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, city, completer);
    }

    static UpdateDSL updateAllColumns(City row, UpdateDSL dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(name).equalTo(row::getName)
                .set(countryCode).equalTo(row::getCountryCode)
                .set(district).equalTo(row::getDistrict)
                .set(population).equalTo(row::getPopulation);
    }

    static UpdateDSL updateSelectiveColumns(City row, UpdateDSL dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(name).equalToWhenPresent(row::getName)
                .set(countryCode).equalToWhenPresent(row::getCountryCode)
                .set(district).equalToWhenPresent(row::getDistrict)
                .set(population).equalToWhenPresent(row::getPopulation);
    }

    default int updateByPrimaryKey(City row) {
        return update(c ->
            c.set(name).equalTo(row::getName)
            .set(countryCode).equalTo(row::getCountryCode)
            .set(district).equalTo(row::getDistrict)
            .set(population).equalTo(row::getPopulation)
            .where(id, isEqualTo(row::getId))
        );
    }

    default int updateByPrimaryKeySelective(City row) {
        return update(c ->
            c.set(name).equalToWhenPresent(row::getName)
            .set(countryCode).equalToWhenPresent(row::getCountryCode)
            .set(district).equalToWhenPresent(row::getDistrict)
            .set(population).equalToWhenPresent(row::getPopulation)
            .where(id, isEqualTo(row::getId))
        );
    }
}