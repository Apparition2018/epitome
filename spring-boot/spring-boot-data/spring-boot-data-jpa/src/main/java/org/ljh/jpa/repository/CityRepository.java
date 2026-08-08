package org.ljh.jpa.repository;

import org.ljh.jpa.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * CityRepository — 城市仓储
 * <p>演示多对一方向的派生查询、分页、以及 {@code JpaSpecificationExecutor} 动态查询。</p>
 *
 * @author ljh
 * @since 2026/8/8
 */
public interface CityRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {

    // ==================== 1. 派生查询（方法名即条件） ====================

    /** 按国家码查询其全部城市（通过关联字段 country.code 导航） */
    List<City> findByCountryCode(String code);

    /** 按国家码查询，按人口倒序 */
    List<City> findByCountryCodeOrderByPopulationDesc(String code);

    /** 按城市名精确查询 */
    List<City> findByName(String name);

    /** 按人口大于等于查询 */
    List<City> findByPopulationGreaterThanEqual(Integer population);

    // ==================== 2. Page 分页 ====================
    // 返回 Page：内容 + 总记录数 + 总页数（额外执行 count 查询）

    /** 按国家码分页查询城市 */
    Page<City> findByCountryCode(String code, Pageable pageable);

    // ==================== 3. 统计 ====================

    /** 统计某国家城市数量 */
    long countByCountryCode(String code);

    // ==================== 4. Slice 分页（不查总记录数） ====================
    // Slice 只返回当前页 + 是否有下一页，不执行 count 查询，适合"无限滚动"场景

    /** 按人口大于等于查询，返回 Slice（无总记录数） */
    Slice<City> findByPopulationGreaterThanEqual(Integer population, Pageable pageable);

    // ==================== 5. 独立 Sort（无分页，仅排序） ====================
    // Sort 作为独立参数传入，Spring Data 自动追加 ORDER BY

    /** 按人口大于等于查询，独立 Sort 排序 */
    List<City> findByPopulationGreaterThanEqual(Integer population, Sort sort);
}
