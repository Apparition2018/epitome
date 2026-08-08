package org.ljh.jpa.repository;

import org.ljh.jpa.entity.City;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.Optional;

/**
 * CityDefinitionRepository — 用 {@code @RepositoryDefinition} 声明仓储
 * <p>演示：不继承任何 {@code Repository} 子接口，仅通过 {@code @RepositoryDefinition}
 * 声明领域类与主键类型，即可让 Spring Data 为其生成代理实现。
 * 方法名依旧支持派生查询。</p>
 *
 * @author ljh
 * @since 2026/8/8
 */
@RepositoryDefinition(domainClass = City.class, idClass = Integer.class)
public interface CityDefinitionRepository {

    /** 按城市名精确查询 */
    Optional<City> findByName(String name);

    /** 统计城市总数 */
    long count();
}
