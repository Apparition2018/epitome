package jar.google.guava.collect;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * BiMap
 * <p>
 * BidMap 是一个接口，该接口要求值和键都不能重复，即双向映射
 * <p>
 * http://www.ibloger.net/article/3307.html
 * https://guava.dev/releases/snapshot-jre/api/docs/index.html?com/google/common/collect/BiMap.html
 */
public class BiMapDemo {

    public static void main(String[] args) {

        BiMap<String, String> map = HashBiMap.create();
        map.put("1", "壹");
        map.put("2", "贰");
        map.put("3", "叁");
        System.out.println(map);            // {3=叁, 2=贰, 1=壹}
        // BiMap<V,K>	inverse()
        // 返回此 map 的逆视图
        System.out.println(map.inverse());  // {叁=3, 贰=2, 壹=1}

    }

}
