package springboot.dao.slaver;

import org.apache.ibatis.annotations.Mapper;
import springboot.domain.slaver.SysUser;

import java.util.List;

@Mapper
public interface SysUserMapper {
    List<SysUser> list(SysUser sysUser);
}