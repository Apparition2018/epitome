package jar.apache.commons.collections4;

import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.HashedMap;

import java.util.Map;

/**
 * <a href="http://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/MapIterator.html">MapIterator</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class MapIteratorDemo {

    public static void main(String[] args) {
        IterableMap<String, String> map = new HashedMap<>();
        map.put("1", "One");
        map.put("2", "Two");
        map.put("3", "Three");
        map.put("4", "Four");
        map.put("5", "Five");

        MapIterator<String, String> mIt = map.mapIterator();
        while (mIt.hasNext()) {
            String next = mIt.next();
            System.out.println(next);

            String value = mIt.getValue();
            System.out.println(value);
            mIt.setValue(value + "_");

            if ("4".equals(next)) {
                mIt.remove();
            }
        }

        System.out.println(map); // {3=Three_, 5=Five_, 2=Two_, 1=One_}
    }
}
