package org.ljh.jpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.ljh.jpa.entity.Author;
import org.ljh.jpa.entity.Country;
import org.ljh.jpa.entity.Student;
import org.ljh.jpa.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RelationshipDao — 关联映射访问。
 * <p>演示多对多、一对一的维护端操作，以及延迟加载与 {@code JOIN FETCH} 预抓取：
 * 默认 {@code LAZY} 关联在持久化上下文关闭后访问会抛 {@code LazyInitializationException}，
 * 因此要么在事务内访问，要么用 {@code join fetch} 一次性取出。</p>
 *
 * @author ljh
 * @since 2026/8/11
 */
@Repository
public class RelationshipDao {

    @PersistenceContext
    private EntityManager em;

    // ==================== 多对多：维护端 ====================

    /** 保存教师及其学生关联（中间表 teacher_student 写入） */
    public void saveTeacher(Teacher teacher) {
        em.persist(teacher);
    }

    /** 用 JOIN FETCH 预抓取多对多集合，避免事务外懒加载异常 */
    public Teacher findTeacherWithStudents(Long id) {
        return em.createQuery(
                        "select t from Teacher t left join fetch t.students where t.id = :id", Teacher.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    /** 学生反向读取其教师 */
    public Student findStudentWithTeachers(Long id) {
        return em.createQuery(
                        "select s from Student s left join fetch s.teachers where s.id = :id", Student.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    // ==================== 一对一：维护端 ====================

    /** 保存作者并级联保存其护照（author.passport_id 落库） */
    public void saveAuthor(Author author) {
        if (author.getPassport() != null) {
            author.getPassport().setAuthor(author);   // 维护双向一致性
        }
        em.persist(author);
    }

    /** 用 JOIN FETCH 预抓取护照，避免懒加载异常 */
    public Author findAuthorWithPassport(Long id) {
        return em.createQuery(
                        "select a from Author a left join fetch a.passport where a.id = :id", Author.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    /** 删除作者（级联删除其护照） */
    public void removeAuthor(Author author) {
        em.remove(author);
    }

    // ==================== 一对多 + 懒加载 ====================

    /** 国家及其城市（JOIN FETCH 一次查出集合，避免 N+1 与懒加载异常） */
    public Country findCountryWithCities(String code) {
        return em.createQuery(
                        "select c from Country c left join fetch c.cities where c.code = :code", Country.class)
                .setParameter("code", code)
                .getSingleResult();
    }

    /** 仅查国家，不取城市（集合仍是懒代理） */
    public Country findCountryLazy(String code) {
        return em.find(Country.class, code);
    }

    /** 查所有城市 */
    public List<Object[]> findCityJoinCountry() {
        return em.createQuery(
                        "select c.name, c.population, co.name from City c join c.country co order by c.population desc",
                        Object[].class)
                .setMaxResults(10)
                .getResultList();
    }
}
