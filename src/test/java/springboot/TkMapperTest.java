package springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.domain.master.Sales;
import springboot.mapper.master.SalesTkMapper;
import tk.mybatis.mapper.entity.Example;

/**
 * TkMapperTest
 *
 * @author HP
 * created on 2022/11/1 17:17
 */
@SpringBootTest
public class TkMapperTest {

    @Autowired
    private SalesTkMapper salesTkMapper;

    @Test
    public void test() {
        Sales sales = new Sales();
        sales.setYearId(2013);
        System.err.println(salesTkMapper.select(sales));
    }

    @Test
    public void testExample() {
        Example example = new Example(Sales.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("yearId", "2013");
        criteria.andEqualTo("monthId", "12");
        System.err.println(salesTkMapper.selectByExample(example));
    }
}