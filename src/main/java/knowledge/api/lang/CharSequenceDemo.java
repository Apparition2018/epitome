package knowledge.api.lang;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

/**
 * CharSequence
 *
 * @author ljh
 * @since 2021/8/19 15:35
 */
public class CharSequenceDemo extends Demo {

    @Test
    public void testCharSequence() {
        // IntStream        chars()
        p(HELLO_WORLD.chars().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining()));       // Hello World!
        // IntStream        codePoints
        p(HELLO_WORLD.codePoints().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining()));  // Hello World!
    }
}
