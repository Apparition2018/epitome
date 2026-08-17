package knowledge.api.lang;

import java.util.stream.Collectors;

import static com.ljh.commons.Demo.HELLO_WORLD;
import static com.ljh.commons.Demo.p;

/**
 * CharSequence
 *
 * @author ljh
 * @since 2021/8/19 15:35
 */
public class CharSequenceDemo {

    public static void main(String[] args) {
        // IntStream        chars()
        p(HELLO_WORLD.chars().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining()));       // Hello World!
        // IntStream        codePoints
        p(HELLO_WORLD.codePoints().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining()));  // Hello World!
    }
}
