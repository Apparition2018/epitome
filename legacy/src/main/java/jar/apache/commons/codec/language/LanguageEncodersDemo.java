package jar.apache.commons.codec.language;

import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.codec.language.RefinedSoundex;
import org.apache.commons.codec.language.Soundex;

import static l.demo.Demo.p;

/**
 * <a href="https://commons.apache.org/proper/commons-codec/userguide.html#Language_Encoders">Language Encoders</a>
 *
 * @author ljh
 * @since 2020/11/15 0:28
 */
public class LanguageEncodersDemo {

    public static void main(String[] args) {
        Metaphone metaphone = new Metaphone();
        RefinedSoundex refinedSoundex = new RefinedSoundex();
        Soundex soundex = new Soundex();

        String[] fruits = {"apple", "banana", "cherry"};
        p(String.format("Original: %s %s %s", fruits[0], fruits[1], fruits[2]));
        p(String.format("Metaphone: %s %s %s", metaphone.encode(fruits[0]), metaphone.encode(fruits[1]), metaphone.encode(fruits[2])));
        p(String.format("RefinedSoundex: %s %s %s", refinedSoundex.encode(fruits[0]), refinedSoundex.encode(fruits[1]), refinedSoundex.encode(fruits[2])));
        p(String.format("Soundex: %s %s %s\n", soundex.encode(fruits[0]), soundex.encode(fruits[1]), soundex.encode(fruits[2])));

        // Original:        apple       banana      cherry       
        // Metaphone:       APL         BNN         KR
        // RefinedSoundex:  A0170       B108080     C3090
        // Soundex:         A140        B550        C600
    }
}
