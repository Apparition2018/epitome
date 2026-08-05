package knowledge.api.util;

import java.util.StringJoiner;

/**
 * StringJoiner
 *
 * @author ljh
 * @since 2021/1/14 14:57
 */
public class StringJoinerDemo {

    public static void main(String[] args) {
        StringJoiner joiner = new StringJoiner(", ", "[", "]").add("a").add("b").add("c");
        System.out.println(joiner); // [a, b, c]
    }
}
