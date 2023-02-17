package knowledge.api.text;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;

/**
 * Collator
 *
 * @author ljh
 * @since 2021/6/23 16:42
 */
public class CollatorDemo {

    public static void main(String[] args) {
        // 中文排序
        String[] persons = {"张三", "李四", "王五"};
        Collator collator = Collator.getInstance(Locale.CHINA);
        Arrays.sort(persons, collator);
        System.out.println(Arrays.toString(persons)); // [李四, 王五, 张三]
    }
}
