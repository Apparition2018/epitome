package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.domain.Demo;

/**
 * Demo JPA Repository
 *
 * @author ljh
 * @since 2026/8/26 2:45
 */
public interface DemoRepository extends JpaRepository<Demo, Long> {
}
