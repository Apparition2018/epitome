package springboot.dao.eip;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import springboot.domain.eip.KmReviewMain;
import springboot.domain.eip.KmReviewMainExample;
import springboot.domain.eip.KmReviewMainWithBLOBs;

public interface KmReviewMainMapper {
    int countByExample(KmReviewMainExample example);

    int deleteByExample(KmReviewMainExample example);

    int deleteByPrimaryKey(String fdId);

    int insert(KmReviewMainWithBLOBs record);

    int insertSelective(KmReviewMainWithBLOBs record);

    List<KmReviewMainWithBLOBs> selectByExampleWithBLOBs(KmReviewMainExample example);

    List<KmReviewMain> selectByExample(KmReviewMainExample example);

    KmReviewMainWithBLOBs selectByPrimaryKey(String fdId);

    int updateByExampleSelective(@Param("record") KmReviewMainWithBLOBs record, @Param("example") KmReviewMainExample example);

    int updateByExampleWithBLOBs(@Param("record") KmReviewMainWithBLOBs record, @Param("example") KmReviewMainExample example);

    int updateByExample(@Param("record") KmReviewMain record, @Param("example") KmReviewMainExample example);

    int updateByPrimaryKeySelective(KmReviewMainWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(KmReviewMainWithBLOBs record);

    int updateByPrimaryKey(KmReviewMain record);
}