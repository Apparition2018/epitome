package jar.hutool.collection;

import cn.hutool.core.collection.IterUtil;
import l.demo.Demo;
import org.junit.Test;

/**
 * IterUtil
 * https://hutool.cn/docs/#/core/%E9%9B%86%E5%90%88%E7%B1%BB/Iterator%E5%B7%A5%E5%85%B7-IterUtil
 *
 * @author Arsenal
 * created on 2020/10/29 2:22
 */
public class IterUtilDemo extends Demo {

    @Test
    public void testIterUtil() {
        
        // countMap(Iterator)                               // List → Map
        p(IterUtil.countMap(list.iterator()));              // {1=1, 2=1, 3=1, 4=1, 5=1, 6=1, 7=1, 8=1, 9=1}
        
        // join(Iterator, conj, prefix, suffix)             StrUtil.split() 的反方法
        p(IterUtil.join(list.iterator(), "-", "[", "]"));   // [1]-[2]-[3]-[4]-[5]-[6]-[7]-[8]-[9]
        
        // filter(T/Iterator, Filter)                       过滤
        p(IterUtil.filter(list, i -> i % 2 == 1));          // [1, 3, 5, 7, 9]
        
    }
}
