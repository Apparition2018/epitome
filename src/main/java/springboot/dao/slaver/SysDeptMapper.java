package springboot.dao.slaver;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static springboot.dao.slaver.SysDeptDynamicSqlSupport.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;
import springboot.domain.slaver.SysDept;

@Mapper
public interface SysDeptMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(deptId, parentId, ancestors, deptName, orderNum, leader, phone, email, status, delFlag, createBy, createTime, updateBy, updateTime);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<SysDept> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<SysDept> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SysDeptResult")
    Optional<SysDept> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SysDeptResult", value = {
        @Result(column="dept_id", property="deptId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="ancestors", property="ancestors", jdbcType=JdbcType.VARCHAR),
        @Result(column="dept_name", property="deptName", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER),
        @Result(column="leader", property="leader", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.CHAR),
        @Result(column="del_flag", property="delFlag", jdbcType=JdbcType.CHAR),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysDept> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, sysDept, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, sysDept, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long deptId_) {
        return delete(c -> 
            c.where(deptId, isEqualTo(deptId_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(SysDept record) {
        return MyBatis3Utils.insert(this::insert, record, sysDept, c ->
            c.map(deptId).toProperty("deptId")
            .map(parentId).toProperty("parentId")
            .map(ancestors).toProperty("ancestors")
            .map(deptName).toProperty("deptName")
            .map(orderNum).toProperty("orderNum")
            .map(leader).toProperty("leader")
            .map(phone).toProperty("phone")
            .map(email).toProperty("email")
            .map(status).toProperty("status")
            .map(delFlag).toProperty("delFlag")
            .map(createBy).toProperty("createBy")
            .map(createTime).toProperty("createTime")
            .map(updateBy).toProperty("updateBy")
            .map(updateTime).toProperty("updateTime")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<SysDept> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, sysDept, c ->
            c.map(deptId).toProperty("deptId")
            .map(parentId).toProperty("parentId")
            .map(ancestors).toProperty("ancestors")
            .map(deptName).toProperty("deptName")
            .map(orderNum).toProperty("orderNum")
            .map(leader).toProperty("leader")
            .map(phone).toProperty("phone")
            .map(email).toProperty("email")
            .map(status).toProperty("status")
            .map(delFlag).toProperty("delFlag")
            .map(createBy).toProperty("createBy")
            .map(createTime).toProperty("createTime")
            .map(updateBy).toProperty("updateBy")
            .map(updateTime).toProperty("updateTime")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(SysDept record) {
        return MyBatis3Utils.insert(this::insert, record, sysDept, c ->
            c.map(deptId).toPropertyWhenPresent("deptId", record::getDeptId)
            .map(parentId).toPropertyWhenPresent("parentId", record::getParentId)
            .map(ancestors).toPropertyWhenPresent("ancestors", record::getAncestors)
            .map(deptName).toPropertyWhenPresent("deptName", record::getDeptName)
            .map(orderNum).toPropertyWhenPresent("orderNum", record::getOrderNum)
            .map(leader).toPropertyWhenPresent("leader", record::getLeader)
            .map(phone).toPropertyWhenPresent("phone", record::getPhone)
            .map(email).toPropertyWhenPresent("email", record::getEmail)
            .map(status).toPropertyWhenPresent("status", record::getStatus)
            .map(delFlag).toPropertyWhenPresent("delFlag", record::getDelFlag)
            .map(createBy).toPropertyWhenPresent("createBy", record::getCreateBy)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(updateBy).toPropertyWhenPresent("updateBy", record::getUpdateBy)
            .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<SysDept> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, sysDept, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<SysDept> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, sysDept, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<SysDept> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, sysDept, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<SysDept> selectByPrimaryKey(Long deptId_) {
        return selectOne(c ->
            c.where(deptId, isEqualTo(deptId_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, sysDept, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(SysDept record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(deptId).equalTo(record::getDeptId)
                .set(parentId).equalTo(record::getParentId)
                .set(ancestors).equalTo(record::getAncestors)
                .set(deptName).equalTo(record::getDeptName)
                .set(orderNum).equalTo(record::getOrderNum)
                .set(leader).equalTo(record::getLeader)
                .set(phone).equalTo(record::getPhone)
                .set(email).equalTo(record::getEmail)
                .set(status).equalTo(record::getStatus)
                .set(delFlag).equalTo(record::getDelFlag)
                .set(createBy).equalTo(record::getCreateBy)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateBy).equalTo(record::getUpdateBy)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(SysDept record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(deptId).equalToWhenPresent(record::getDeptId)
                .set(parentId).equalToWhenPresent(record::getParentId)
                .set(ancestors).equalToWhenPresent(record::getAncestors)
                .set(deptName).equalToWhenPresent(record::getDeptName)
                .set(orderNum).equalToWhenPresent(record::getOrderNum)
                .set(leader).equalToWhenPresent(record::getLeader)
                .set(phone).equalToWhenPresent(record::getPhone)
                .set(email).equalToWhenPresent(record::getEmail)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(delFlag).equalToWhenPresent(record::getDelFlag)
                .set(createBy).equalToWhenPresent(record::getCreateBy)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateBy).equalToWhenPresent(record::getUpdateBy)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(SysDept record) {
        return update(c ->
            c.set(parentId).equalTo(record::getParentId)
            .set(ancestors).equalTo(record::getAncestors)
            .set(deptName).equalTo(record::getDeptName)
            .set(orderNum).equalTo(record::getOrderNum)
            .set(leader).equalTo(record::getLeader)
            .set(phone).equalTo(record::getPhone)
            .set(email).equalTo(record::getEmail)
            .set(status).equalTo(record::getStatus)
            .set(delFlag).equalTo(record::getDelFlag)
            .set(createBy).equalTo(record::getCreateBy)
            .set(createTime).equalTo(record::getCreateTime)
            .set(updateBy).equalTo(record::getUpdateBy)
            .set(updateTime).equalTo(record::getUpdateTime)
            .where(deptId, isEqualTo(record::getDeptId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(SysDept record) {
        return update(c ->
            c.set(parentId).equalToWhenPresent(record::getParentId)
            .set(ancestors).equalToWhenPresent(record::getAncestors)
            .set(deptName).equalToWhenPresent(record::getDeptName)
            .set(orderNum).equalToWhenPresent(record::getOrderNum)
            .set(leader).equalToWhenPresent(record::getLeader)
            .set(phone).equalToWhenPresent(record::getPhone)
            .set(email).equalToWhenPresent(record::getEmail)
            .set(status).equalToWhenPresent(record::getStatus)
            .set(delFlag).equalToWhenPresent(record::getDelFlag)
            .set(createBy).equalToWhenPresent(record::getCreateBy)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .set(updateBy).equalToWhenPresent(record::getUpdateBy)
            .set(updateTime).equalToWhenPresent(record::getUpdateTime)
            .where(deptId, isEqualTo(record::getDeptId))
        );
    }
}