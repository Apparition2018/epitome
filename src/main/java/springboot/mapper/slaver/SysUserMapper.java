package springboot.mapper.slaver;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import springboot.domain.slaver.SysUser;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysUserMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(SysUser row);

    SysUser selectByPrimaryKey(Long roleId);

    List<SysUser> selectAll();

    int updateByPrimaryKey(SysUser row);

    List<SysUser> list(SysUser sysUser);

    List<SysUser> list2(SysUser sysUser);

    @MapKey("user_id")
    Map<Integer, Map<String, Object>> map(SysUser sysUser);
}
