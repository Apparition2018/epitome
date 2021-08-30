package springboot.dao.master;

import org.springframework.stereotype.Repository;
import springboot.domain.master.Score;

import java.util.List;

@Repository
public interface ScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Score record);

    Score selectByPrimaryKey(Integer id);

    List<Score> selectAll();

    int updateByPrimaryKey(Score record);
}