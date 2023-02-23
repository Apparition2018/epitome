package knowledge.api.lang.string;

import org.junit.jupiter.api.Test;

/**
 * String Exercise
 *
 * @author ljh
 * @since 2020/9/7 1:28
 */
public class StringExercise {

    /**
     * 假如有字符串"46sal452d4a5"，用最快速的方法去掉字符"sal"，不能用java内置字符串方法。
     */
    @Test
    public void test1() {
        String str = "46sal452d4a5";
        StringBuilder newStr = new StringBuilder();

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
     * String s1 = new String("abc"); String s2 = "a" + "b"; 分别创建了几个对象？
     */
    @Test
    public void test2() {
        // String s1 = new String("abc");
        // 答：1个或2个
        // jvm 读到 "abc" 时，会检查常量池是否已有 "abc"
        // jvm 读到 "new" 时，会在堆中创建一个对象，内容为 "abc" 的引用地址

        // String s2 = "a" + "b";
        // 答：1个
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        String s4 = "a" + "b";
        System.out.println(s3 == s4); // true
        s4 = s1 + "b";
        System.out.println(s3 == s4); // false
    }
}
