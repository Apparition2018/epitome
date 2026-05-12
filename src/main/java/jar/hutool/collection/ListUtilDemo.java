package jar.hutool.collection;

import cn.hutool.core.collection.ListUtil;
import l.demo.Demo;

import java.util.List;

/**
 * <a href="https://doc.hutool.cn/pages/ListUtil/">ListUtil</a>
 * <a href="https://plus.hutool.cn/apidocs/cn/hutool/core/collection/ListUtil.html">ListUtil api</a>
 *
 * @author ljh
 * @since 2020/10/29 2:06
 */
public class ListUtilDemo extends Demo {

    public static void main(String[] args) {
        //                                                      创建
        list = ListUtil.list(false, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        list = ListUtil.of(arr);
        list = ListUtil.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        list = ListUtil.toList(list);
        list = ListUtil.toList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        list = ListUtil.toLinkedList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        list = ListUtil.unmodifiable(list);
        list = ListUtil.toCopyOnWriteArrayList(list);
        List<Integer> empty = ListUtil.empty();

        // page(pageNo, pageSize, List)                         分页
        p(ListUtil.page(2, 3, list));                           // [7, 8, 9]

        // sub(List/Collection, start, end, step)               截取集合的部分
        p(ListUtil.sub(list, 0, 7, 2));                         // [1, 3, 5, 7]

        // split(List, size)                                    分组，按照指定长度
        p(ListUtil.split(list, 2));                             // [[1, 2], [3, 4], [5, 6], [7, 8], [9]]

        // lastIndexOf(List, Matcher)                           获取匹配规则定义中匹配到元素的最后位置
        p(ListUtil.lastIndexOf(ListUtil.of(1, 2, 3, 2, 1), Integer.valueOf(2)::equals));// 3

        // indexOfAll(List, Matcher)                            获取匹配规则定义中匹配到元素的所有位置
        p(ListUtil.indexOfAll(ListUtil.of(1, 2, 3, 2, 1), Integer.valueOf(2)::equals)); // [1, 3]

        // setOrAppend(List, index, element)                    设置或增加元素。当 index 小于 List 的长度时，替换指定位置的值，否则在尾部追加
        p(ListUtil.setOrAppend(ListUtil.toList(list), 5, 0));   // [1, 2, 3, 4, 5, 0, 7, 8, 9]
        p(ListUtil.setOrAppend(ListUtil.toList(list), 10, 0));  // [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
    }
}
