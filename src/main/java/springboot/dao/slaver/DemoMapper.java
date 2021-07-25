package springboot.dao.slaver;

import org.springframework.stereotype.Repository;
import springboot.domain.slaver.Demo;

import java.util.List;

@Repository
public interface DemoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Demo record);

    int insertBatch(List<Demo> list);

    int insertSelective(Demo record);

    Demo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Demo record);

    int updateByPrimaryKey(Demo record);
}