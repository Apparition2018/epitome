package springboot.dao.master;

import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;
import springboot.domain.master.Score;

import java.util.List;
import java.util.Map;

@Repository
public interface ScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Score record);

    Score selectByPrimaryKey(Integer id);

    List<Score> selectAll();

    int updateByPrimaryKey(Score record);

    @MapKey("id")
    Map<Integer, Map<Integer, Object>> selectAllMap();
}