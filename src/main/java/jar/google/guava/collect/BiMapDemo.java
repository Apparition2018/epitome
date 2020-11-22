package jar.google.guava.collect;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import l.demo.Demo;

/**
 * BiMap
 * BidMap 要求值和键都不能重复，即双向映射
 * http://www.ibloger.net/article/3307.html
 * https://guava.dev/releases/snapshot-jre/api/docs/index.html?com/google/common/collect/BiMap.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class BiMapDemo extends Demo {

    public static void main(String[] args) {

        BiMap<Integer, String> biMap = HashBiMap.create(map);

        // BiMap<V,K>	inverse()       返回此 map 的逆视图
        p(biMap.inverse()); // {A=1, B=2, C=3}
        
        // Set<V>       values()        返回 value 列表
        p(biMap.values());  // [A, B, C]
    }

}
