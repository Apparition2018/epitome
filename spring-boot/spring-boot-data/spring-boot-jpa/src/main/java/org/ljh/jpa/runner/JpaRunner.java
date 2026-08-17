package org.ljh.jpa.runner;

import com.ljh.commons.Continent;
import org.ljh.jpa.dao.CountryDao;
import org.ljh.jpa.dao.PersonDao;
import org.ljh.jpa.entity.Address;
import org.ljh.jpa.entity.Author;
import org.ljh.jpa.entity.Country;
import org.ljh.jpa.entity.Passport;
import org.ljh.jpa.service.JpaService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * JpaRunner — 启动时自动执行的演示器。
 * <p>在应用启动后跑一组典型的 JPA 操作，把结果打印到控制台，便于直接观察效果
 * （表数据已在 SQL 初始化时导入 world 库真实数据）。演示内容包括：JPQL 查询、
 * 命名查询、原生 SQL、Criteria、级联持久化、继承 + 可嵌入、乐观锁、一对一等。</p>
 *
 * @author ljh
 * @since 2026/8/11
 */
@Component
public class JpaRunner implements ApplicationRunner {

    private final JpaService demoService;
    private final CountryDao countryDao;
    private final PersonDao personDao;

    public JpaRunner(JpaService demoService,
                     CountryDao countryDao,
                     PersonDao personDao) {
        this.demoService = demoService;
        this.countryDao = countryDao;
        this.personDao = personDao;
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("==================== JPA 演示开始 ====================");

        // 1. JPQL + 命名查询 + 聚合（world 表）
        List<Country> asia = demoService.listByContinent(Continent.ASIA);
        System.out.println("[JPQL] Asia 国家数 = " + asia.size() + "，总数 = " + countryDao.countAll());

        // 2. Criteria 动态查询（world 表）
        List<Country> criteria = countryDao.searchByCriteria("C", Continent.ASIA, 5);
        System.out.println("[Criteria] code 以 C 开头且属 Asia 的前5个 = "
                + criteria.stream().map(Country::getCode).toList());

        // 3. 原生 SQL（world 表）
        System.out.println("[NativeSQL] Asia 国家（原生SQL） = "
                + countryDao.nativeFindAll().size() + " 条");

        // 4. 一对多 + JOIN FETCH（world 表 country ↔ city）
        Country cn = countryDao.findById("CHN");
        if (cn != null) {
            System.out.println("[JoinFetch] CHN 的城市数 = "
                    + demoService.findCountryWithCities("CHN").getCities().size());
        }

        // 5. 继承 + 可嵌入 + 乐观锁（独立表 person）
        demoService.addStaff("Alice", "R&D", new Address("Rd.1", "SH", "200000"));
        demoService.addStaff("Bob", "Ops", new Address("Rd.2", "BJ", "100000"));
        Long managerId = demoService.addManager("Carol", 8);
        System.out.println("[Inheritance] Person 总数 = " + personDao.findAll().size());

        // 6. 乐观锁更新（person.version）：更新后版本号应自增
        demoService.updatePersonName(managerId, "Carol v2");
        personDao.findAll().stream()
                .filter(p -> p.getId().equals(managerId))
                .findFirst()
                .ifPresent(p -> System.out.println("[OptimisticLock] 经理改名后 version = " + p.getVersion()));

        // 7. 一对一（独立表 author/passport）
        Author author = new Author();
        author.setName("Zhang");
        Passport passport = new Passport();
        passport.setNumber("E123456");
        author.setPassport(passport);
        demoService.saveAuthorWithPassport(author);
        System.out.println("[OneToOne] 作者已保存，护照 id = " + author.getPassport().getId());

        System.out.println("==================== JPA 演示结束 ====================");
    }
}
