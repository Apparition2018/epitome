package jar.hutool.util;

import cn.hutool.core.util.PageUtil;
import org.junit.jupiter.api.Test;

import static l.demo.Demo.p;

/**
 * PageUtil     分页工具
 * https://hutool.cn/docs/#/core/%E5%B7%A5%E5%85%B7%E7%B1%BB/%E5%88%86%E9%A1%B5%E5%B7%A5%E5%85%B7-PageUtil
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/util/PageUtil.html
 *
 * @author ljh
 * created on 2020/11/19 11:41
 */
public class PageUtilDemo {

    @Test
    public void testPageUtil() {
        int totalCount = 1255;  // 总条数
        int pageSize = 10;      // 每页条数
        int totalPage;          // 总页数
        int pageNo = 33;        // 当前页

        // 设置首页页码，0 或 1
        PageUtil.setFirstPageNo(1);
        // 设置首页页码为 1
        PageUtil.setOneAsFirstPageNo();

        // 获取指定页的开始位置
        p(PageUtil.getStart(pageNo, pageSize));         // 320
        // 获取指定页的结束位置
        p(PageUtil.getEnd(pageNo, pageSize));           // 330
        // 获取指定页的开始位置和结束位置
        p(PageUtil.transToStartEnd(pageNo, pageSize));  // [320, 330]

        // 计算页数
        totalPage = PageUtil.totalPage(totalCount, pageSize);

        // 显示前 n 页和后 n 页的页码（这里是前5页和后5页，所以 5*2+1=11）
        p(PageUtil.rainbow(pageNo, totalPage, 11));     // [28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38]
    }
}
