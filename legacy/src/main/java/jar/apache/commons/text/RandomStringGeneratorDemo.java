package jar.apache.commons.text;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

/**
 * RandomStringGenerator
 *
 * @author ljh
 * @since 2025/4/11 8:43
 */
public class RandomStringGeneratorDemo {

    public static void main(String[] args) {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
            .withinRange('0', 'z')
            .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
            .get();
        System.out.println("随机字符串: " + generator.generate(6));
    }
}
