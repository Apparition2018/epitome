package springboot.mapper.master;

import org.apache.ibatis.annotations.Param;
import springboot.domain.master.Generator;
import springboot.domain.master.GeneratorExample;
import springboot.domain.master.GeneratorKey;
import springboot.domain.master.GeneratorWithBLOBs;

import java.util.List;

public interface GeneratorMapper {
    long countByExample(GeneratorExample example);

    int deleteByExample(GeneratorExample example);

    int deleteByPrimaryKey(GeneratorKey key);

    int insert(GeneratorWithBLOBs row);

    int insertSelective(GeneratorWithBLOBs row);

    List<GeneratorWithBLOBs> selectByExampleWithBLOBs(GeneratorExample example);

    List<Generator> selectByExample(GeneratorExample example);

    GeneratorWithBLOBs selectByPrimaryKey(GeneratorKey key);

    int updateByExampleSelective(@Param("row") GeneratorWithBLOBs row, @Param("example") GeneratorExample example);

    int updateByExampleWithBLOBs(@Param("row") GeneratorWithBLOBs row, @Param("example") GeneratorExample example);

    int updateByExample(@Param("row") Generator row, @Param("example") GeneratorExample example);

    int updateByPrimaryKeySelective(GeneratorWithBLOBs row);

    int updateByPrimaryKeyWithBLOBs(GeneratorWithBLOBs row);

    int updateByPrimaryKey(Generator row);
}