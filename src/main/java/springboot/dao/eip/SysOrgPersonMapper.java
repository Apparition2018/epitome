package springboot.dao.eip;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import springboot.domain.eip.SysOrgPerson;
import springboot.domain.eip.SysOrgPersonExample;

public interface SysOrgPersonMapper {
    int countByExample(SysOrgPersonExample example);

    int deleteByExample(SysOrgPersonExample example);

    int deleteByPrimaryKey(String fdId);

    int insert(SysOrgPerson record);

    int insertSelective(SysOrgPerson record);

    List<SysOrgPerson> selectByExample(SysOrgPersonExample example);

    SysOrgPerson selectByPrimaryKey(String fdId);

    int updateByExampleSelective(@Param("record") SysOrgPerson record, @Param("example") SysOrgPersonExample example);

    int updateByExample(@Param("record") SysOrgPerson record, @Param("example") SysOrgPersonExample example);

    int updateByPrimaryKeySelective(SysOrgPerson record);

    int updateByPrimaryKey(SysOrgPerson record);
}