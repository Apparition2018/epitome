package springboot.mapper.master.draw;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import springboot.domain.master.draw.Prize;

import java.util.List;

/**
 * PrizeMapper
 *
 * @author ljh
 * @since 2022/7/4 0:45
 */
@Mapper
public interface PrizeMapper {

    /**
     * 根据 drawId 查找奖品概率列表
     *
     * @param drawId 抽奖活动ID
     * @return 奖品概率列表
     */
    @Select({"select id, probability from prize where draw_id = #{drawId}"})
    List<Prize> listIdAndPrByDrawId(@Param("drawId") Integer drawId);

    /**
     * 中将数+1
     *
     * @param id 奖品ID
     * @return 影响条数
     */
    @Update({
            "update prize set win_qty = win_qty + 1",
            "where id = #{id} and total_qty > win_qty"
    })
    int incrWinQty(@Param("id") Integer id);
}
