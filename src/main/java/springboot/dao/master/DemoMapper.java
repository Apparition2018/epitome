package springboot.dao.master;

import org.springframework.stereotype.Repository;
import springboot.domain.master.Demo;

/**
 * User1Mapper
 *
 * @author ljh
 * created on 2022/2/18 11:37
 */
@Repository
public interface DemoMapper {
    int insert(Demo record);
}
