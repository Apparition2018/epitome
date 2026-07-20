package org.ljh.mybatis.entity.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.ljh.mybatis.entity.Continent;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MyBatis TypeHandler：将 Continent 枚举与数据库 VARCHAR 列互转
 *
 * @author ljh
 * @since 2026/7/20
 */
@MappedTypes(Continent.class)
public class ContinentTypeHandler extends BaseTypeHandler<Continent> {

    /** Java → 数据库 */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Continent parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getValue());
    }

    /** 数据库 → Java */
    @Override
    public Continent getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value == null ? null : Continent.fromValue(value);
    }

    /** 数据库 → Java */
    @Override
    public Continent getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value == null ? null : Continent.fromValue(value);
    }

    /** 数据库 → Java */
    @Override
    public Continent getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value == null ? null : Continent.fromValue(value);
    }
}
