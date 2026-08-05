package springboot.mapper.master.exam;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import springboot.domain.master.exam.ExamBook;

/**
 * ExamBookMapper
 *
 * @author ljh
 * @since 2026/4/29 23:07
 */
@Mapper
public interface ExamBookMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO exam_book(session_id, user_id) VALUES(#{session.id}, #{userId})")
    int insert(ExamBook examBook);
}
