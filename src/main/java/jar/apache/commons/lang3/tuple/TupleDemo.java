package jar.apache.commons.lang3.tuple;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

/**
 * TupleDemo
 *
 * @author ljh
 * created on 2022/11/9 9:52
 */
public class TupleDemo {

    public static void main(String[] args) {
        /* Pair */
        Pair<String, String> pair = Pair.of("A", "B");
        System.out.println(pair.getLeft());
        System.out.println(pair.getRight());

        /* Triple */
        Triple<String, String, String> triple = Triple.of("A", "B", "C");
        System.out.println(triple.getLeft());
        System.out.println(triple.getMiddle());
        System.out.println(triple.getRight());
    }
}
