package knowledge.api.util;

import l.demo.Demo;

import java.util.StringJoiner;

/**
 * StringJoiner
 *
 * @author ljh
 * @since 2021/1/14 14:57
 */
public class StringJoinerDemo extends Demo {

    public static void main(String[] args) {
        StringJoiner joiner = new StringJoiner(", ", "[", "]").add("a").add("b").add("c");
        p(joiner); // [a, b, c]
    }
}
