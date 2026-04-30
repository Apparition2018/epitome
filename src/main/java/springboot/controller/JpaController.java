package springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.domain.master.exam.ExamBook;
import springboot.domain.master.exam.ExamSession;
import springboot.mapper.master.exam.ExamBookMapper;
import springboot.mapper.master.exam.ExamSessionMapper;
import springboot.repository.master.exam.ExamBookRepository;
import springboot.repository.master.exam.ExamSessionRepository;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * JPA
 * <p>Jakarta Persistence API</p>
 * <pre>
 * JPA              EntityManager   定义接口无实现
 * Spring Data JPA  Repository      内部持有 EntityManager 并调用其 API     委托关系
 * Hibernate        Session         对 EntityManager 的实现               继承关系
 * </pre>
 * 注：最终是 Hibernate 干活：因为项目一般都会引入 Hibernate，且 hibernate Session 是 JPA 的实现
 *
 * @author ljh
 * @since 2026/4/29 18:03
 */
@RestController
@RequestMapping("exam")
@Tag(name = "Exam")
public class JpaController {

    /**
     * {@code @PersistenceContext} 作用：
     * <ol>
     *   <li>注入一个代理对象，不是真正的 EntityManager 实现</li>
     *   <li>方法进入 @Transactional 时，代理从 EntityManagerFactory 创建一个新的 EntityManager</li>
     *   <li>该 EntityManager 绑定到当前事务，事务结束后自动关闭</li>
     *   <li>线程安全：不同请求各有一个 EntityManager，互不干扰</li>
     *   <li>entityManager.unwrap(Session.class) 拿到底层 Hibernate Session，且已处于活跃事务中</li>
     * </ol>
     * 对比：注入 EntityManagerFactory，需手动需手动管理创建(createEntityManager())和关闭
     */
    @PersistenceContext
    private EntityManager entityManager;
    private final ExamSessionRepository sessionRepository;
    private final ExamBookRepository bookRepository;
    private final ExamSessionMapper sessionMapper;
    private final ExamBookMapper bookMapper;

    public JpaController(ExamSessionRepository sessionRepository, ExamBookRepository bookRepository,
                         ExamSessionMapper sessionMapper, ExamBookMapper bookMapper) {
        this.sessionRepository = sessionRepository;
        this.bookRepository = bookRepository;
        this.sessionMapper = sessionMapper;
        this.bookMapper = bookMapper;
    }

    /** <a href="http://localhost:3333/exam/book">exam.jmx JMeter 测试</a> */
    @GetMapping("book")
    @Operation(summary = "预约(JPA)")
    @Transactional(transactionManager = "jpaTransactionManager", rollbackFor = Exception.class)
    public ResponseEntity<String> booByJPA() throws InterruptedException {
        return book(
            () -> entityManager.find(ExamSession.class, 1L),
            id -> entityManager
                .createQuery("UPDATE ExamSession e SET e.curBookNum = e.curBookNum + 1 WHERE e.id = :id AND e.maxBookNum > e.curBookNum")
                .setParameter("id", id)
                .executeUpdate(),
            book -> entityManager.persist(book)
        );
    }

    @GetMapping("book2")
    @Operation(summary = "预约(Hibernate)")
    @Transactional(transactionManager = "jpaTransactionManager", rollbackFor = Exception.class)
    public ResponseEntity<String> booByHibernate() throws InterruptedException {
        // Session 是 Hibernate 对 EntityManager 的实现
        Session session = entityManager.unwrap(Session.class);
        return book(
            () -> session.find(ExamSession.class, 1L),
            id -> session
                .createMutationQuery("UPDATE ExamSession e SET e.curBookNum = e.curBookNum + 1 WHERE e.id = :id AND e.maxBookNum > e.curBookNum")
                .setParameter("id", id)
                .executeUpdate(),
            session::persist
        );
    }

    @GetMapping("book3")
    @Operation(summary = "预约(Spring Data JPA)")
    @Transactional(transactionManager = "jpaTransactionManager", rollbackFor = Exception.class)
    public ResponseEntity<String> bookBySpringDataJPA() throws InterruptedException {
        return book(
            () -> sessionRepository.findById(1L).orElseThrow(),
            sessionRepository::incrCurBookNum,
            bookRepository::save
        );
    }

    @GetMapping("book4")
    @Operation(summary = "预约(Mybatis)")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<String> booByMybatis() throws InterruptedException {
        return book(
            () -> sessionMapper.selectById(1L),
            sessionMapper::incrCurBookNum,
            bookMapper::insert
        );
    }

    private ResponseEntity<String> book(
        Supplier<ExamSession> examSessionLoader,
        Function<Long, Integer> bookIncrementer,
        Consumer<ExamBook> bookSaver) throws InterruptedException {
        ExamSession examSession = examSessionLoader.get();
        Thread.sleep(ThreadLocalRandom.current().nextInt(500, 2000));
        int flag = bookIncrementer.apply(examSession.getId());
        if (flag == 0) return ResponseEntity.badRequest().body("fail");
        long userId = ThreadLocalRandom.current().nextLong(1_000_000);
        ExamBook examBook = new ExamBook().setSession(examSession).setUserId(userId);
        bookSaver.accept(examBook);
        return ResponseEntity.ok("success");
    }
}
