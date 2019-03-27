package knowledge.api.lang.string;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class StringExercises {

    /**
     * 假如有字符串"46dsal452d4a5"，用最快速的方法去掉字符"sal"，
     * 不能用java内置字符串方法。
     */
    @Test
    public void test1() {
        String str = "46dsal452d4a5";
        StringBuffer newStr = new StringBuffer();

        char c1 = 's';
        char c2 = 'a';
        char c3 = 'l';

        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (c != c1 && c != c2 && c != c3) {
                newStr.append(c);
            }
        }
        System.out.println(newStr);

    }

    /**
     * 统计在字符串"abcdabftfalhhkk"中每个字符出现的次数。
     */
    @Test
    public void test2() {
        String str = "abcdabftfalhhkk";

        char[] chars = str.toCharArray();

        Map<Character, Integer> map = new HashMap<>();

        for (char c : chars) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }

        Set<Entry<Character, Integer>> entrySet = map.entrySet();
        for (Entry<Character, Integer> entry : entrySet) {
            System.out.println(entry);
        }

    }
}
