package springboot.mapper.master;

import org.springframework.stereotype.Repository;
import springboot.domain.master.Demo;

/**
 * DemoMapper
 *
 * @author ljh
 * @since 2022/2/18 11:37
 */
@Repository
public interface DemoMapper {
    int insert(Demo record);
}
