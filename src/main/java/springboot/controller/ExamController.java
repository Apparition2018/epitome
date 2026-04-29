package springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

/**
 * ExamController
 *
 * @author ljh
 * @since 2026/4/29 18:03
 */
@RestController
@RequestMapping("exam")
@Tag(name = "Exam")
public class ExamController {

    private final ExamSessionRepository sessionRepository;
    private final ExamBookRepository bookRepository;
    private final ExamSessionMapper sessionMapper;
    private final ExamBookMapper bookMapper;

    public ExamController(ExamSessionRepository sessionRepository, ExamBookRepository bookRepository, ExamSessionMapper sessionMapper, ExamBookMapper bookMapper) {
        this.sessionRepository = sessionRepository;
        this.bookRepository = bookRepository;
        this.sessionMapper = sessionMapper;
        this.bookMapper = bookMapper;
    }

    @GetMapping("book")
    @Operation(summary = "预约")
    @Transactional(transactionManager = "jpaTransactionManager", rollbackFor = Exception.class)
    public ResponseEntity<String> bookByJpa() throws InterruptedException {
        ExamSession examSession = sessionRepository.findById(1L).orElseThrow();
        Thread.sleep(ThreadLocalRandom.current().nextInt(500, 2000));
        int flag = sessionRepository.incrCurBookNum(examSession.getId());
        if(flag == 0) return ResponseEntity.badRequest().body("预约失败");
        long userId = ThreadLocalRandom.current().nextLong(1_000_000);
        ExamBook examBook = new ExamBook().setSessionId(examSession.getId()).setUserId(userId);
        bookRepository.save(examBook);
        return ResponseEntity.ok("success");
    }

    @GetMapping("book2")
    @Operation(summary = "预约")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<String> booByMybatis() throws InterruptedException {
        ExamSession examSession = sessionMapper.selectById(1L);
        Thread.sleep(ThreadLocalRandom.current().nextInt(500, 2000));
        int flag = sessionMapper.incrCurBookNum(examSession.getId());
        if(flag == 0) return ResponseEntity.badRequest().body("预约失败");
        long userId = ThreadLocalRandom.current().nextLong(1_000_000);
        ExamBook examBook = new ExamBook().setSessionId(examSession.getId()).setUserId(userId);
        bookMapper.insert(examBook);
        return ResponseEntity.ok("success");
    }
}
