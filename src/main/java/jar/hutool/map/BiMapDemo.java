package jar.hutool.map;

import cn.hutool.core.map.BiMap;
import l.demo.Demo;

/**
 * <a href="https://hutool.cn/docs/#/core/Map/双向查找Map-BiMap">BiMap</a>  双向查找 Map
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/map/BiMap.html">BiMap api</a>
 * <p>BiMap 要求值和键都不能重复
 *
 * @author ljh
 * @since 2020/11/2 11:40
 */
public class BiMapDemo extends Demo {

    public static void main(String[] args) {
        BiMap<Integer, String> biMap = new BiMap<>(map);

        // Map<V,K>	    getInverse()        获取反向 Map
        p(biMap.getInverse());
    }
}
