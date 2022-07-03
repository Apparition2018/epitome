package springboot.dao.master;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import springboot.domain.master.Prize;

import java.util.List;

/**
 * PrizeMapper
 *
 * @author ljh
 * created on 2022/7/4 0:45
 */
@Mapper
public interface PrizeMapper {

    @Select({
            "select * from prize",
            "where draw_id = #{drawId} and total_qty - win_qty > 0"
    })
    List<Prize> listByDrawId(@Param("drawId") Integer drawId);

    @Update({
            "update prize set win_qty = win_qty + 1",
            "where id = #{id} and total_qty > win_qty"
    })
    int incrWinQty(@Param("id") Integer id);
}
