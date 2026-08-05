package knowledge.api.util.stream;

import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static l.demo.Demo.arr;

/**
 * StreamSupport
 *
 * @author ljh
 * @since 2021/8/31 14:09
 */
public class StreamSupportDemo {

    public static void main(String[] args) {
        Stream<Integer> stream = StreamSupport.stream(Arrays.spliterator(arr), false);
    }
}
