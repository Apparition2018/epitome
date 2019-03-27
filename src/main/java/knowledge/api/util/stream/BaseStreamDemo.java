package knowledge.api.util.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * BaseStream
 */
public class BaseStreamDemo {

    /**
     * S	parallel()
     * Returns an equivalent stream that is parallel
     */
    @Test
    public void parallel() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));
        list.stream().parallel().forEach(System.out::println);
    }

}
