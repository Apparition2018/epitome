package org.ljh.dependencies.group.hutool.map;

import cn.hutool.core.map.BiMap;
import org.junit.jupiter.api.Test;

import static com.ljh.commons.Demo.map;
import static com.ljh.commons.Demo.p;

/**
 * <a href="https://doc.hutool.cn/pages/BiMap/">BiMap</a>   双向查找 Map
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/map/BiMap.html">BiMap api</a>
 * <p>BiMap 要求值和键都不能重复
 *
 * @author ljh
 * @since 2020/11/2 11:40
 */
public class BiMapTests {

    @Test
    public void test() {
        BiMap<Integer, String> biMap = new BiMap<>(map);

        // Map<V,K>	    getInverse()        获取反向 Map
        p(biMap.getInverse());
    }
}
