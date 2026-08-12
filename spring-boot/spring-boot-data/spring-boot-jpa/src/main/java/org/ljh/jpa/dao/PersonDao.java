package org.ljh.jpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import org.ljh.jpa.entity.Manager;
import org.ljh.jpa.entity.Person;
import org.ljh.jpa.entity.Staff;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PersonDao — 继承映射实体访问。
 * <p>演示单表继承（{@code SINGLE_TABLE}）下的多态查询：用父类查询可同时命中
 * {@code Staff} 与 {@code Manager} 子类，也可用 {@code TYPE(p)} 限定具体子类。</p>
 *
 * @author ljh
 * @since 2026/8/11
 */
@Repository
public class PersonDao {

    @PersistenceContext
    private EntityManager em;

    /** 新增员工（其可嵌入地址一并落入 person 表） */
    public void saveStaff(Staff staff) {
        em.persist(staff);
    }

    /** 新增经理 */
    public void saveManager(Manager manager) {
        em.persist(manager);
    }

    /** 多态查询：返回所有 Person 及其子类实例 */
    public List<Person> findAll() {
        return em.createQuery("select p from Person p order by p.id", Person.class).getResultList();
    }

    /** 只查某个具体子类（JPA 标准 TYPE 表达式） */
    public List<Manager> findManagers() {
        return em.createQuery("select m from Manager m", Manager.class).getResultList();
    }

    /** 更新员工部门 */
    public void updateDepartment(Long id, String department) {
        Staff staff = em.find(Staff.class, id);
        if (staff != null) {
            staff.setDepartment(department);
        }
    }

    /** 乐观锁更新：修改后依赖 {@code @Version} 在提交时自动校验版本 */
    public void updateName(Long id, String name) {
        Person person = em.find(Person.class, id);
        if (person != null) {
            person.setName(name);
        }
    }

    /** 悲观锁查询：对行加 {@code PESSIMISTIC_WRITE} 写锁，直至事务提交才释放 */
    public Person findByIdForUpdate(Long id) {
        return em.find(Person.class, id, jakarta.persistence.LockModeType.PESSIMISTIC_WRITE);
    }

    /**
     * 合并一个脱管（detached）实体并立即 flush，用于演示乐观锁冲突：
     * 若该实体的 {@code @Version} 版本已在别处被更新，flush 时生成的
     * {@code update ... where version = 旧值} 将匹配不到任何行，从而抛出
     * {@code OptimisticLockException}（{@link PersistenceException} 子类）。
     * 调用方应捕获后按需重试。
     */
    public void updateStale(Staff detached) {
        em.merge(detached);
        em.flush();
    }
}
