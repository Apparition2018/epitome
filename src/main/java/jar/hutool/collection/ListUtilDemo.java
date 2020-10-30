package jar.hutool.collection;

import cn.hutool.core.collection.ListUtil;
import l.demo.Demo;
import org.junit.Test;

/**
 * ListUtil
 * https://hutool.cn/docs/#/core/%E9%9B%86%E5%90%88%E7%B1%BB/%E5%88%97%E8%A1%A8%E5%B7%A5%E5%85%B7-ListUtil
 *
 * @author Arsenal
 * created on 2020/10/29 2:06
 */
public class ListUtilDemo extends Demo {

    @Test
    public void testListUtil() {
        //                                          创建
        list = ListUtil.list(false, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        list = ListUtil.toList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        list = ListUtil.toLinkedList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        list = ListUtil.toList(list);
        list = ListUtil.toCopyOnWriteArrayList(list);
        
        // page(pageNo, pageSize, List)             分页
        p(ListUtil.page(2, 3, list)); // [7, 8, 9]

        // sub(List/Collection, start, end, step)   对集合切片，其他类型的集合会转换成 List
        p(ListUtil.sub(list, 0, list.size(), 2));   // [1, 3, 5, 7, 9]

        // filter(List, Editor)                     过滤
        p(ListUtil.filter(list, i -> {
            if (i % 2 == 1) {
                return i;
            }
            return null;
        })); // [1, 3, 5, 7, 9]

        // indexOfAll(List, Matcher)                获取满足指定规则所有的元素的位置
        p(ListUtil.indexOfAll(ListUtil.toList(1, 2, 3, 2, 1), new Integer(2)::equals)); // [1, 3] 
    }
}
