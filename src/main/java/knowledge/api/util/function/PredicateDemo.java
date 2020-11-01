package knowledge.api.util.function;

import l.demo.Demo;

import java.util.List;
import java.util.function.Predicate;

/**
 * Predicate<T>
 * 接受一个参数 T，返回一个 Boolean，用来进行判断是否符合条件
 * 该接口包含多种默认方法来将 Predicate 组合成其他复杂的逻辑（比如：与，或，非）。
 * https://blog.csdn.net/w605283073/article/details/89410918
 *
 * @author ljh
 * created on 2020/9/9 11:36
 */
public class PredicateDemo extends Demo {

    public static void main(String[] args) {
        p("\n输出所有数据:");
        eval(list, n -> true);          // 1 2 3 4 5 6 7 8 9 

        p("\n输出所有偶数:");
        eval(list, n -> n % 2 == 0);    // 2 4 6 8 

        p("\n输出大于 3 的数字:");
        eval(list, n -> n > 3);         // 4 5 6 7 8 9 

        p("\n输出大于 3 且为偶数的数字:");
        Predicate<Integer> predicate1 = n -> n % 2 == 0;
        Predicate<Integer> predicate2 = n -> n > 3;
        eval(list, predicate1.and(predicate2)); // 4 6 8 
    }

    private static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.test(n)) {
                System.out.print(n + " ");
            }
        }
    }
}
