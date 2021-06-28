package jar.hutool.map;

import cn.hutool.core.map.BiMap;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

/**
 * BiMap    双向查找 Map
 * BiMap 要求值和键都不能重复
 * https://hutool.cn/docs/#/core/Map/%E5%8F%8C%E5%90%91%E6%9F%A5%E6%89%BEMap-BiMap
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/map/BiMap.html
 *
 * @author ljh
 * created on 2020/11/2 11:40
 */
public class BiMapDemo extends Demo {
    
    @Test
    public void testBiMap() {
        
        BiMap<Integer, String> biMap = new BiMap<>(map);
        
        // Map<V,K>	    getInverse()        获取反向 Map
        p(biMap.getInverse());
    }
}
