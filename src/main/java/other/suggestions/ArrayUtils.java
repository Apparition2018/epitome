package other.suggestions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 建议95：强制声明泛型的实际类型
 */
class ArrayUtils {

    // 生成一个长度可变的 List
    @SafeVarargs
    public static <T> List<T> asList(T... t) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, t);
        return list;
    }
}