package jar.hutool.collection;

import cn.hutool.core.collection.IterUtil;
import l.demo.Demo;
import org.junit.Test;

/**
 * IterUtil
 *
 * @author Arsenal
 * created on 2020/10/29 2:22
 */
public class IterUtilDemo extends Demo {

    @Test
    public void testIterUtil() {
        // join(Iterator, conjunction, prefix, suffix)  StrUtil.split() 的反方法
        p(IterUtil.join(list.iterator(), "-", "[", "]"));   // [1]-[2]-[3]-[4]-[5]-[6]-[7]-[8]-[9]
        
        // filter(T/Iterator, Filter)           过滤
        p(IterUtil.filter(list, i -> i % 2 == 1));          // [1, 3, 5, 7, 9]
        
    }
}
