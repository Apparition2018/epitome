package jar.apache.commons.text;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.junit.jupiter.api.Test;

import static l.demo.Demo.p;

/**
 * <a href="https://commons.apache.org/proper/commons-text/javadocs/api-release/org/apache/commons/text/WordUtils.html">WordUtils</a>
 * <p>对包含单词的字符串进行操作</p>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class WordUtilsDemo {

    /**
     * static String	abbreviate(String str, int lower, int upper, String appendToEnd)
     * <p>缩写
     */
    @Test
    public void abbreviate() {
        p(WordUtils.abbreviate("Now is the time for all good men", 0, 40, null));               // Now
        p(WordUtils.abbreviate("Now is the time for all good men", 10, 40, StringUtils.EMPTY)); // Now is the
        p(WordUtils.abbreviate("Now is the time for all good men", 20, 40, " ..."));            // Now is the time for all ...
        p(WordUtils.abbreviate("Now is the time for all good men", 50, -1, " ..."));            // Now is the time for all good men
        p(WordUtils.abbreviate("Now is the time for all good men", 9, -10, null));              // IllegalArgumentException: upper value cannot be less than -1
    }

    /**
     * static String	capitalize|capitalizeFully(String str[, char... delimiters])
     * <p>首字母大写
     * <p>static String	uncapitalize(String str, char... delimiters)
     * <p>取消首字母大写
     */
    @Test
    public void capitalize() {
        p(WordUtils.capitalize("i am fine"));               // I Am Fine
        p(WordUtils.capitalize("i aM.fine", '.'));          // I aM.Fine
        p(WordUtils.capitalize("i am fine", new char[]{})); // I am fine

        p(WordUtils.capitalizeFully("i aM.fine", '.'));     // I am.Fine

        p(WordUtils.uncapitalize("I am fine"));             // i am fine
    }

    /**
     * static String	swapCase(String str)
     * <p>转换大小写
     */
    @Test
    public void swapCase() {
        p(WordUtils.swapCase("The dog has a BONE")); // tHE DOG HAS A bone
    }

    /**
     * static boolean	containsAllWords(CharSequence word, CharSequence... words)
     * <p>判断是否包含单词
     */
    @Test
    public void containsAllWords() {
        p(WordUtils.containsAllWords("i am fine", "i", "am"));  // true
        p(WordUtils.containsAllWords("i am fine", "a", "f"));   // false
    }

    /**
     * static String	initials(String str[, char... delimiters])
     * <p>提取首字母
     */
    @Test
    public void initials() {
        p(WordUtils.initials("People's Republic of China")); // PRoC
    }

    /**
     * static String	wrap(String str, int wrapLength[, String newLineStr, boolean wrapLongWords, String wrapOn])
     * <p>换行
     */
    @Test
    public void wrap() {
        String str = "Click here to jump to the commons website - http://commons.apache.org";

        p(WordUtils.wrap(str, 20, " <br /> ", false));
        // Click here to jump <br /> to the commons <br /> website - <br /> http://commons.apache.org
        p(WordUtils.wrap(str, 20, " <br /> ", true));
        // Click here to jump <br /> to the commons <br /> website - <br /> http://commons.apach <br /> e.org
    }
}
