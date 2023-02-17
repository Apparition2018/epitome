package jar.hutool.util;

import cn.hutool.core.util.PageUtil;

import static l.demo.Demo.p;

/**
 * <a href="https://hutool.cn/docs/#/core/工具类/分页工具-PageUtil">PageUtil</a>   分页工具
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/util/PageUtil.html">PageUtil api</a>
 *
 * @author ljh
 * @since 2020/11/19 11:41
 */
public class PageUtilDemo {

    public static void main(String[] args) {
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
