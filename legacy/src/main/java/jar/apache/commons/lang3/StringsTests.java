package jar.apache.commons.lang3;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.junit.jupiter.api.Test;

import static com.ljh.commons.Demo.p;

/**
 * <a href="http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/Strings.html">Strings</a>
 *
 * @author ljh
 * @since 2026/8/22 3:00
 */
public class StringsTests {

    /**
     * appendIfMissing / prependIfMissing
     * <p>如果开头/结尾不是指定的 suffix / prefix，则拼接到开头/结果
     */
    @Test
    public void xxxIfMissing() {
        p(Strings.CS.appendIfMissing(null, null));                  //
        p(Strings.CS.appendIfMissing("abc", null));                 // abc
        p(Strings.CS.appendIfMissing(StringUtils.EMPTY, "xyz"));    // xyz
        p(Strings.CS.appendIfMissing("abc", "xyz"));                // abcxyz
        p(Strings.CS.appendIfMissing("abcxyz", "xyz"));             // abcxyz
        p(Strings.CS.appendIfMissing("abcXYZ", "xyz"));             // abcXYZxyz
        p(Strings.CS.appendIfMissing("abc", "xyz", "mno"));         // abcxyz
        p(Strings.CS.appendIfMissing("abcmno", "xyz", "mno"));      // abcmno
        p(Strings.CS.appendIfMissing("abcXYZ", "xyz", "mno"));      // abcXYZxyz
    }

    @Test
    public void equals() {
        p(Strings.CS.equalsAny("aaa", "aaa", "bbb", "ccc"));                // true
        p(Strings.CS.equalsAny("aaa", new String[]{"aaa", "bbb", "ccc"}));  // true
        p(Strings.CS.equalsAny("abc", "aaa", "bbb", "ccc"));                // false
        p(Strings.CI.equalsAny("AAA", "aaa", "bbb", "ccc"));                // true
    }

    @Test
    public void compare() {
        p(Strings.CS.compare("aaa", "AAA"));    // 32
        p(Strings.CI.compare("aaa", "AAA"));    // 0
    }

    @Test
    public void indexOf() {
        p(Strings.CI.indexOf("abcdabcd", "BC"));    // 1
    }

    @Test
    public void contains() {
        p(Strings.CS.contains("abcdabcd", "bc"));       // true
        p(Strings.CI.contains("abcdabcd", "BC"));       // true
        p(Strings.CS.containsAny("abcdabcd", "b", "e"));// true
    }

    @Test
    public void remove() {
        p(Strings.CS.remove("abcdabcd", "a"));      // bcdbcd
        p(Strings.CS.removeStart("abcdabcd", "a")); // bcdabcd
        p(Strings.CI.removeStart("abcdabcd", "A")); // bcdabcd
        p(Strings.CS.removeEnd("abcdabcd", "d"));   // abcdabc
        p(Strings.CI.removeEnd("abcdabcd", "D"));   // abcdabc
    }

    @Test
    public void replace() {
        p(Strings.CS.replace("AbcdAbcd", "A", "a"));        // abcdabcd
        p(Strings.CS.replaceOnce("AbcdAbcd", "A", "a"));    // abcdAbcd
    }

    @Test
    public void xxxWith() {
        p(Strings.CS.startsWith("abc", "A"));           // false
        p(Strings.CS.startsWithAny("abc", "a", "A"));   // true
        p(Strings.CI.startsWith("abc", "A"));           // true

        p(Strings.CS.endsWith("abc", "C"));             // false
        p(Strings.CS.endsWithAny("abc", "c", "C"));     // true
        p(Strings.CI.endsWith("abc", "C"));             // true
    }
}
