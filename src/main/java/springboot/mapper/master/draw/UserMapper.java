package springboot.mapper.master.draw;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * UserMapper
 *
 * @author ljh
 * @since 2022/7/4 0:24
 */
@Mapper
public interface UserMapper {

    /**
     * 扣减积分
     *
     * @param id    用户ID
     * @param score 积分
     * @return 影响条数
     */
    @Update({
            "update user set score = score - #{score}",
            "where id = #{id} and score >= #{score}"
    })
    int deductScore(@Param("id") Integer id, @Param("score") Integer score);
}
