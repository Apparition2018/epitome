package springboot.dao.master;

import java.util.List;

import org.springframework.stereotype.Repository;
import springboot.domain.master.Sales;

@Repository
public interface SalesMapper {
    int insert(Sales record);

    List<Sales> selectAll();
}