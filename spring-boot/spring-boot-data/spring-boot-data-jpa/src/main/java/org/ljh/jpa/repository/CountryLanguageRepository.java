package org.ljh.jpa.repository;

import org.ljh.jpa.entity.CountryLanguage;
import org.ljh.jpa.entity.CountryLanguageId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * CountryLanguageRepository — 国家语言仓储
 * <p>演示 {@code @IdClass} 原生复合主键的查询：以 {@code CountryLanguageId} 作为主键类型。</p>
 *
 * @author ljh
 * @since 2026/8/8
 */
public interface CountryLanguageRepository extends JpaRepository<CountryLanguage, CountryLanguageId> {

    /** 按国家码查询其全部语言 */
    List<CountryLanguage> findByCountryCode(String code);

    /** 查询官方语言 */
    List<CountryLanguage> findByIsOfficial(String isOfficial);

    /** JPQL 关联查询：通过关联的国家名称过滤 */
    @Query("select cl from CountryLanguage cl where cl.country.name = :countryName")
    List<CountryLanguage> findByCountryName(@Param("countryName") String countryName);
}
