package springboot.mapper.slaver;

import org.apache.ibatis.annotations.Mapper;
import springboot.domain.slaver.SysDept;

import java.util.List;

@Mapper
public interface SysDeptMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(SysDept row);

    SysDept selectByPrimaryKey(Long roleId);

    List<SysDept> selectAll();

    int updateByPrimaryKey(SysDept row);

    List<SysDept> list(SysDept sysDept);
}
