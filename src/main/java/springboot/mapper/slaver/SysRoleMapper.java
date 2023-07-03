package springboot.mapper.slaver;

import org.apache.ibatis.annotations.Mapper;
import springboot.domain.slaver.SysRole;

import java.util.List;

@Mapper
public interface SysRoleMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(SysRole row);

    SysRole selectByPrimaryKey(Long roleId);

    List<SysRole> selectAll();

    int updateByPrimaryKey(SysRole row);
}