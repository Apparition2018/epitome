package knowledge.api.util;

import l.demo.Demo;
import org.junit.Test;

import java.util.StringJoiner;

/**
 * StringJoiner
 *
 * @author Administrator
 * created on 2021/1/14 14:57
 */
public class StringJoinerDemo extends Demo {

    @Test
    public void testStringJoiner() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        p(joiner); // []
        
        joiner.add("1");
        joiner.add("2");
        joiner.add("3");
        joiner.add("4");
        joiner.add("5");
        p(joiner); // [1, 2, 3, 4, 5]
    }
}
