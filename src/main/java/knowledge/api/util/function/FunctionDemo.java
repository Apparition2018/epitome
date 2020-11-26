package knowledge.api.util.function;

import java.util.function.Function;

import static l.demo.Demo.p;

/**
 * Function<T, R>
 * 接收一个参数 T，返回一个值 R
 *
 * @author ljh
 * created on 2020/9/9 15:13
 */
public class FunctionDemo {

    public static void main(String[] args) {
        Function<Integer, Integer> plusSelf = i -> i + i;
        Function<Integer, Integer> mulSelf = i -> i * i;
        p(plusSelf.apply(3));                   // 3 + 3 = 6
        p(mulSelf.compose(plusSelf).apply(3));  // (3 + 3) * (3 + 3) = 36，先执行 plusSelf，再执行 mulSelf
        p(mulSelf.apply(plusSelf.apply(3)));    // (3 + 3) * (3 + 3) = 36
        p(mulSelf.andThen(plusSelf).apply(3));  // (3 * 3） + (3 * 3) = 18
        p(plusSelf.apply(mulSelf.apply(3)));    // (3 * 3） + (3 * 3) = 18，先执行 mulSelf，再执行 plusSelf
    }
}
