package knowledge.api.util.stream;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * StreamSupport
 *
 * @author ljh
 * created on 2021/8/31 14:09
 */
public class StreamSupportDemo extends Demo {

    @Test
    public void testStreamSupport() {
        Stream<Integer> stream = StreamSupport.stream(Arrays.spliterator(arr), false);
    }
}
