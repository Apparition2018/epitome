package org.ljh.mybatis.mapper.normal;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ljh.mybatis.entity.normal.City;

import java.util.List;
import java.util.Map;

@Mapper
public interface CityCustomMapper {

    /**
     * JOIN 查询城市及其所属国家
     * 使用 columnPrefix="country_" 避免同名字段冲突
     */
    City selectCityWithCountry(@Param("id") Integer id);

    /**
     * {@code @MapKey} 将 {@code List<City>} 转为 {@code Map<Integer, City>}，key = city.id
     */
    @MapKey("id")
    Map<Integer, City> selectAllCityMap();

    /**
     * 配合 {@code support-methods-arguments=true}，通过 Mapper 方法参数自动分页
     * <p>参数名 {@code pageNum} / {@code pageSize} / {@code count} 与 {@code pagehelper.params} 配置对应，
     * PageHelper 会自动拦截该查询并拼接分页 SQL，无需手动调用 {@code PageHelper.startPage()}。</p>
     * <ul>
     *   <li>{@code count=null}（或 {@code true}）：执行 {@code COUNT(*)}，返回总记录数</li>
     *   <li>{@code count=false}：跳过 {@code COUNT(*)}，仅做 {@code LIMIT}，适合不需要总记录数的场景</li>
     * </ul>
     */
    List<City> selectByPage(@Param("pageNum") int pageNum,
                            @Param("pageSize") int pageSize,
                            @Param("count") Boolean count);
}
