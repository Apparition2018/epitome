package org.ljh.jpa.service;

import org.ljh.jpa.dao.CountryDao;
import org.ljh.jpa.dao.CountryLanguageDao;
import org.ljh.jpa.dao.PersonDao;
import org.ljh.jpa.dao.RelationshipDao;
import org.ljh.jpa.entity.Address;
import org.ljh.jpa.entity.Author;
import org.ljh.jpa.entity.Continent;
import org.ljh.jpa.entity.Country;
import org.ljh.jpa.entity.Manager;
import org.ljh.jpa.entity.Passport;
import org.ljh.jpa.entity.Person;
import org.ljh.jpa.entity.Staff;
import org.ljh.jpa.entity.Student;
import org.ljh.jpa.entity.Teacher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * JpaDemoService — 面向业务的演示服务。
 * <p>在 DAO（仅用 {@code EntityManager}）之上再包一层 {@code @Transactional} 服务，
 * 演示 Spring 声明式事务与 JPA 的关系：事务边界由 {@code JpaTransactionManager} 管理，
 * 同一事务内共享同一个持久化上下文（一级缓存），提交时才统一 flush。</p>
 * <p>数据大多复用 commons 的 world 三表（country / city / countrylanguage），
 * 仅继承 + 可嵌入 + 乐观锁（person）与多对多 / 一对一（teacher/student、author/passport）
 * 使用最小独立表。</p>
 *
 * @author ljh
 * @since 2026/8/11
 */
@Service
public class JpaDemoService {

    private final CountryDao countryDao;
    private final CountryLanguageDao countryLanguageDao;
    private final PersonDao personDao;
    private final RelationshipDao relationshipDao;

    public JpaDemoService(CountryDao countryDao,
                          CountryLanguageDao countryLanguageDao,
                          PersonDao personDao,
                          RelationshipDao relationshipDao) {
        this.countryDao = countryDao;
        this.countryLanguageDao = countryLanguageDao;
        this.personDao = personDao;
        this.relationshipDao = relationshipDao;
    }

    // ==================== 组合流程：国家 + 城市（world 表） ====================

    /**
     * 级联保存国家及其城市：在同一个 {@code @Transactional} 事务里，
     * 依赖 {@code Country.cities} 上的 {@code CascadeType.ALL} 一并持久化。
     */
    @Transactional
    public Country saveCountryWithCities(Country country) {
        countryDao.save(country);
        return country;
    }

    /** 按大洲查询（JPQL） */
    @Transactional(readOnly = true)
    public List<Country> listByContinent(Continent continent) {
        return countryDao.findByContinent(continent);
    }

    /** 国家 + 城市 JOIN FETCH 预抓取（事务内访问集合） */
    @Transactional(readOnly = true)
    public Country findCountryWithCities(String code) {
        return relationshipDao.findCountryWithCities(code);
    }

    // ==================== 组合流程：作者 + 护照（一对一，独立表） ====================

    /** 保存作者并级联保存护照 */
    @Transactional
    public Author saveAuthorWithPassport(Author author) {
        relationshipDao.saveAuthor(author);
        return author;
    }

    // ==================== 组合流程：教师 + 学生（多对多，独立表） ====================

    /** 保存教师及其学生关联 */
    @Transactional
    public Teacher saveTeacherWithStudents(Teacher teacher) {
        relationshipDao.saveTeacher(teacher);
        return teacher;
    }

    // ==================== 组合流程：继承 + 可嵌入 + 乐观锁（独立表 person） ====================

    /** 新增员工（含可嵌入地址） */
    @Transactional
    public Long addStaff(String name, String department, Address address) {
        Staff staff = new Staff();
        staff.setName(name);
        staff.setDepartment(department);
        staff.setAddress(address);
        personDao.saveStaff(staff);
        return staff.getId();
    }

    /** 新增经理 */
    @Transactional
    public Long addManager(String name, int teamSize) {
        Manager manager = new Manager();
        manager.setName(name);
        manager.setTeamSize(teamSize);
        personDao.saveManager(manager);
        return manager.getId();
    }

    /** 多态查询 */
    @Transactional(readOnly = true)
    public List<Person> listPersons() {
        return personDao.findAll();
    }

    /** 乐观锁更新姓名：并发下版本冲突会抛 {@code OptimisticLockException} */
    @Transactional
    public void updatePersonName(Long id, String name) {
        personDao.updateName(id, name);
    }
}
