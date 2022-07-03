package springboot.dao.master;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * UserMapper
 *
 * @author ljh
 * created on 2022/7/4 0:24
 */
@Mapper
public interface UserMapper {

    @Update({
            "update user set score = score - #{score}",
            "where id = #{id} and score >= #{score}"
    })
    int deductScore(@Param("id") Integer id, @Param("score") Integer score);
}
