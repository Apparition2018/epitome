package springboot.dao.slaver;

import org.apache.ibatis.annotations.Mapper;
import springboot.domain.slaver.SysUser;

import java.util.List;

/**
 * SysUserMapper
 *
 * @author ljh
 * created on 2022/4/20 10:04
 */
@Mapper
public interface SysUserMapper {
    List<SysUser> list(SysUser sysUser);
}