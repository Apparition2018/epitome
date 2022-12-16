package springboot.dao.slaver;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import springboot.domain.slaver.SysUser;

import java.util.List;
import java.util.Map;

/**
 * SysUserMapper
 *
 * @author ljh
 * @since 2022/4/20 10:04
 */
@Mapper
public interface SysUserMapper {
    List<SysUser> list(SysUser sysUser);

    List<SysUser> list2(SysUser sysUser);

    @MapKey("user_id")
    Map<Integer, Map<String, Object>> map(SysUser sysUser);
}
