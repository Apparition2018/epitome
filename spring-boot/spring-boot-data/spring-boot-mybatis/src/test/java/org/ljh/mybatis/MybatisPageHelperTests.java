package org.ljh.mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.ljh.mybatis.entity.normal.City;
import org.ljh.mybatis.entity.normal.CityExample;
import org.ljh.mybatis.entity.normal.CountryExample;
import org.ljh.mybatis.entity.normal.CountryLanguageExample;
import org.ljh.mybatis.mapper.normal.CityCustomMapper;
import org.ljh.mybatis.mapper.normal.CityMapper;
import org.ljh.mybatis.mapper.normal.CountryLanguageMapper;
import org.ljh.mybatis.mapper.normal.CountryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * PageHelper 分页测试 — normal 模块
 *
 * @author ljh
 * @since 2026/7/24
 */
@SpringBootTest(classes = MybatisApplication.class)
public class MybatisPageHelperTests {

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private CountryLanguageMapper countryLanguageMapper;

    @Autowired
    private CityCustomMapper cityCustomMapper;

    // ====================================================================
    //  四种分页方式 × 返回对象 对比
    // ====================================================================
    //
    //  ┌──────────────────────┬───────────────────┬──────────────────────┐
    //  │    分页方式           │  返回 Page         │  返回 PageInfo       │
    //  ├──────────────────────┼───────────────────┼──────────────────────┤
    //  │ ① startPage + 查询   │     —             │ testTraditional      │
    //  │ ② startPage + Lambda │ testLambdaPage    │ testLambdaPageInfo   │
    //  │ ③ 声明式参数分页       │     —             │ testDeclarative      │
    //  └──────────────────────┴───────────────────┴──────────────────────┘

    /**
     * 【方式一】传统 startPage + 直接调用 Mapper，读取 PageInfo
     */
    @Test
    void testTraditionalStartPage() {
        PageHelper.startPage(1, 5);
        List<City> cities = cityCustomMapper.selectByPage(1, 5, null);
        PageInfo<City> pageInfo = new PageInfo<>(cities);
        assertThat(pageInfo.getTotal()).isPositive();
        assertThat(pageInfo.getList()).hasSizeLessThanOrEqualTo(5);
        assertThat(pageInfo.getPageNum()).isEqualTo(1);
        System.out.printf("[方式一·传统] total: %d, page: %d/%d, size: %d%n",
            pageInfo.getTotal(), pageInfo.getPageNum(), pageInfo.getPages(), pageInfo.getSize());
    }

    /**
     * 【方式二·返回 Page】startPage + doSelectPage Lambda，直接返回 Page 对象
     */
    @Test
    void testLambdaDoSelectPage() {
        Page<Object> page = PageHelper.startPage(1, 5)
            .doSelectPage(() -> cityMapper.selectByExample(new CityExample()));

        assertThat(page.getTotal()).isPositive();
        assertThat(page.getResult()).hasSizeLessThanOrEqualTo(5);
        assertThat(page.getPageNum()).isEqualTo(1);
        assertThat(page.getPageSize()).isEqualTo(5);
        System.out.printf("[方式二·Page] total: %d, pageSize: %d, result: %d%n",
            page.getTotal(), page.getPageSize(), page.getResult().size());
    }

    /**
     * 【方式二·返回 PageInfo】startPage + doSelectPageInfo Lambda，直接返回 PageInfo
     */
    @Test
    void testLambdaDoSelectPageInfo() {
        PageInfo<Object> pageInfo = PageHelper.startPage(2, 3)
            .doSelectPageInfo(() -> countryMapper.selectByExample(new CountryExample()));

        assertThat(pageInfo.getTotal()).isPositive();
        assertThat(pageInfo.getList()).hasSizeLessThanOrEqualTo(3);
        assertThat(pageInfo.getPageNum()).isEqualTo(2);
        assertThat(pageInfo.getPages()).isPositive();
        System.out.printf("[方式二·PageInfo] total: %d, pages: %d, current: %d%n",
            pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getPageNum());
    }

    /**
     * 【方式三】声明式：利用 {@code support-methods-arguments=true}，
     * 通过 Mapper 方法参数 {@code pageNum} / {@code pageSize} / {@code count} 自动分页，
     * 无需手动调用 {@code PageHelper.startPage()}
     */
    @Test
    void testDeclarativeByArgs() {
        List<City> cities = cityCustomMapper.selectByPage(1, 5, null);
        PageInfo<City> pageInfo = new PageInfo<>(cities);
        assertThat(pageInfo.getTotal()).isPositive();
        assertThat(pageInfo.getList()).hasSizeLessThanOrEqualTo(5);
        assertThat(pageInfo.getPageNum()).isEqualTo(1);
        System.out.printf("[方式三·声明式] total: %d, page: %d/%d, size: %d%n",
            pageInfo.getTotal(), pageInfo.getPageNum(), pageInfo.getPages(), pageInfo.getSize());
    }

    // ==================== 其他示例 ====================

    /**
     * count — 仅返回记录总数，不查询数据（{@code PageHelper.count} 与上述分页方式无关）
     */
    @Test
    void testPageHelperCount() {
        long count = PageHelper.count(() -> countryLanguageMapper.selectByExample(new CountryLanguageExample()));

        assertThat(count).isPositive();
        System.out.printf("count — total: %d%n", count);
    }

    /**
     * 演示 {@code count=false} 跳过 {@code COUNT(*)} 查询
     * <p>适用于仅需加载更多、不需要总记录数的场景。</p>
     */
    @Test
    void testDeclarativeSkipCount() {
        List<City> cities = cityCustomMapper.selectByPage(1, 5, false);
        PageInfo<City> pageInfo = new PageInfo<>(cities);
        assertThat(pageInfo.getTotal()).isEqualTo(0);
        assertThat(pageInfo.getList()).hasSizeLessThanOrEqualTo(5);
        System.out.printf("[跳过 count] total=%d, size=%d%n",
            pageInfo.getTotal(), pageInfo.getList().size());
    }

    /**
     * 验证 {@code PageHelper.startPage()} 线程隔离 — 多次分页互不影响
     * <p>每次 {@code startPage()} 在 ThreadLocal 中设置分页参数，
     * 查询执行后自动清除，互不干扰。</p>
     */
    @Test
    void testPageHelperThreadIsolation() {
        Page<Object> page1 = PageHelper.startPage(1, 2)
            .doSelectPage(() -> cityMapper.selectByExample(new CityExample()));

        Page<Object> page2 = PageHelper.startPage(1, 3)
            .doSelectPage(() -> countryMapper.selectByExample(new CountryExample()));

        assertThat(page1.getResult()).hasSizeLessThanOrEqualTo(2);
        assertThat(page2.getResult()).hasSizeLessThanOrEqualTo(3);
        System.out.printf("Page1 size: %d, Page2 size: %d%n",
            page1.getResult().size(), page2.getResult().size());
    }

}
