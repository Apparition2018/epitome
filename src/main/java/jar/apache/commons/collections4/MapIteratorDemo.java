package jar.apache.commons.collections4;

import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.HashedMap;
import org.junit.jupiter.api.Test;

/**
 * MapIterator
 * http://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/MapIterator.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class MapIteratorDemo {

    @Test
    public void mapIterator() {
        IterableMap<String, String> map = new HashedMap<>();

        map.put("1", "One");
        map.put("2", "Two");
        map.put("3", "Three");
        map.put("4", "Four");
        map.put("5", "Five");

        MapIterator<String, String> mIt = map.mapIterator();
        while (mIt.hasNext()) {
            System.out.println(mIt.next());
            // V	getValue()
            String value = mIt.getValue();
            System.out.println(value);

            // V	setValue(V value)
            mIt.setValue(value + "_");

            if ("Four".equals(value)) {
                // void	remove()
                mIt.remove();
            }

        }

        System.out.println(map); // {3=Three_, 5=Five_, 2=Two_, 1=One_}
    }

}
