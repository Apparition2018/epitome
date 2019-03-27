package springboot.dao.eip;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import springboot.domain.eip.SysOrgElement;
import springboot.domain.eip.SysOrgElementExample;

public interface SysOrgElementMapper {
    int countByExample(SysOrgElementExample example);

    int deleteByExample(SysOrgElementExample example);

    int deleteByPrimaryKey(String fdId);

    int insert(SysOrgElement record);

    int insertSelective(SysOrgElement record);

    List<SysOrgElement> selectByExample(SysOrgElementExample example);

    SysOrgElement selectByPrimaryKey(String fdId);

    int updateByExampleSelective(@Param("record") SysOrgElement record, @Param("example") SysOrgElementExample example);

    int updateByExample(@Param("record") SysOrgElement record, @Param("example") SysOrgElementExample example);

    int updateByPrimaryKeySelective(SysOrgElement record);

    int updateByPrimaryKey(SysOrgElement record);
}