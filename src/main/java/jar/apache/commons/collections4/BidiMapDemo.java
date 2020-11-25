package jar.apache.commons.collections4;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.junit.Test;

import static l.demo.Demo.p;

/**
 * BidiMap
 * BidMap 是一个接口，该接口要求值和键都不能重复，并允许键值双向查找
 * http://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/BidiMap.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class BidiMapDemo {

    @Test
    public void bidiMap() {
        BidiMap<String, String> bidi = new TreeBidiMap<>();
        bidi.put("One", "1");
        bidi.put("Tow", "2");
        bidi.put("Three", "3");
        p(bidi);                // {One=1, Three=3, Tow=2}

        // Set<V>	    values()
        // 返回此映射中包含的值的 Set 视图
        p(bidi.values());       // [1, 3, 2]

        p(bidi.get("Three"));   // 3
        // K	        getKey(Object value)
        // 获取当前映射到指定值的键
        p(bidi.getKey("3"));    // Three

        // BidiMap<V,K>	inverseBidiMap()
        // 获取相反 BidiMap
        BidiMap<String, String> inverseBidi = bidi.inverseBidiMap();
        p(inverseBidi);         // {1=One, 2=Tow, 3=Three}

        // K	        removeValue(Object value)
        // 删除当前映射到指定值的键值对
        bidi.removeValue("2");
        p();

    }

}
