package org.ljh.jpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.ljh.jpa.entity.CountryLanguage;
import org.ljh.jpa.entity.CountryLanguageId;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CountryLanguageDao — 复合主键（@IdClass）实体访问。
 * <p>演示如何用 {@code @IdClass} 主键类 {@link CountryLanguageId} 完成
 * 增删改查——主键由 (CountryCode, Language) 两列组合而成。</p>
 *
 * @author ljh
 * @since 2026/8/11
 */
@Repository
public class CountryLanguageDao {

    @PersistenceContext
    private EntityManager em;

    /** 新增 */
    public void save(CountryLanguage cl) {
        em.persist(cl);
    }

    /** 按复合主键查询 */
    public CountryLanguage findById(String countryCode, String language) {
        return em.find(CountryLanguage.class, new CountryLanguageId(countryCode, language));
    }

    /** 删除 */
    public void remove(CountryLanguage cl) {
        em.remove(cl);
    }

    /** 某国家的所有语言 */
    public List<CountryLanguage> findByCountryCode(String countryCode) {
        return em.createQuery(
                        "select cl from CountryLanguage cl where cl.countryCode = :cc", CountryLanguage.class)
                .setParameter("cc", countryCode)
                .getResultList();
    }
}
