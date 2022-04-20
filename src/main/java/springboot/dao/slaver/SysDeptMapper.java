package springboot.dao.slaver;

import org.apache.ibatis.annotations.Mapper;
import springboot.domain.slaver.SysDept;

import java.util.List;

/**
 * SysDeptMapper
 *
 * @author ljh
 * created on 2022/4/20 10:04
 */
@Mapper
public interface SysDeptMapper {
    List<SysDept> list(SysDept sysDept);
}
