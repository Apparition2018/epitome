package springboot.mapper.master.exam;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import springboot.domain.master.exam.ExamSession;

/**
 * ExamSessionMapper
 *
 * @author ljh
 * @since 2026/4/29 23:07
 */
@Mapper
public interface ExamSessionMapper {

    @Select("SELECT id, name, max_book_num, cur_book_num FROM exam_session WHERE id = #{id}")
    ExamSession selectById(@Param("id") Long id);

    @Update("UPDATE exam_session SET cur_book_num = cur_book_num + 1 WHERE id = #{id} AND max_book_num > cur_book_num")
    int incrCurBookNum(@Param("id") Long id);

    @Update("UPDATE exam_session SET cur_book_num = cur_book_num - 1 WHERE id = #{id} AND cur_book_num > 0")
    int decrCurBookNum(@Param("id") Long id);
}
