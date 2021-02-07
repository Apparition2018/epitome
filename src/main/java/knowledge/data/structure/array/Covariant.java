package knowledge.data.structure.array;

import org.junit.jupiter.api.Test;

/**
 * Java 数组的协变性指：如果 Base 是 Sub 的基类，name Base[] 就是 Sub[] 的基类
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class Covariant {

    @Test
    public void covariant() {

        class Base {
        }
        class Sub extends Base {
        }

        Base[] arr = new Sub[10];
        arr[0] = new Sub();

        // 因为数组的协变性，所以以上赋值成功
        // 但数组的协变性存下如下隐患

        Object[] oArr = new Integer[10];
        oArr[0] = "String"; // 编译正常，但报异常：ArrayStoreException

    }

}
