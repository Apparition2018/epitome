package jar.apache.commons.lang3.tuple;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import static l.demo.Demo.p;

/**
 * Tuple
 *
 * @author ljh
 * @since 2022/11/9 9:52
 */
public class TupleDemo {

    public static void main(String[] args) {
        /* Pair */
        Pair<String, String> pair = Pair.of("A", "B");
        p(pair.getLeft());      // A
        p(pair.getRight());     // B

        /* Triple */
        Triple<String, String, String> triple = Triple.of("A", "B", "C");
        p(triple.getLeft());    // A
        p(triple.getMiddle());  // B
        p(triple.getRight());   // C
    }
}
