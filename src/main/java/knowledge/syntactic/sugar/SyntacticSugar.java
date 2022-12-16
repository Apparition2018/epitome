package knowledge.syntactic.sugar;

import org.junit.jupiter.api.Test;

import static l.demo.Demo.p;

/**
 * 语法糖
 * 1.泛型
 * 2.自动拆装箱
 * 3.方法变长参数
 * 4.枚举
 * 5.内部类
 * 6.条件编译
 * 7.断言
 * 8.数值字面量
 * 9.增强 for 循环
 * 10.try-with-resources
 * 11.lambda 表达式
 * 12.switch-case 对 String 和 枚举的支持
 * 13.String 对 + 号的支持
 * Java 语法糖详解：https://www.cnblogs.com/helloworld2048/p/10916453.html
 *
 * @author ljh
 * @since 2020/9/24 11:57
 */
public class SyntacticSugar {

    /**
     * 可变参数 (varargs)
     * <p>
     * JDK1.5 开始，Java 支持传递同类型的可变参数给一个方法
     * 一个方法中只能指定一个可变参数，它必须是方法的最后一个参数
     * 可变参数的各个参数用 "," 隔开，也可以直接传入一个数组
     */
    static class VarargsDemo {

        @Test
        public void testVarargs() {
            class Base {
                void func(int price, int... discounts) {
                    p("Base ... func");
                }
            }
            class Sub extends Base {
                @Override
                void func(int price, int[] discounts) {
                    p("Sub ... func");
                }
            }

            Base base = new Sub();
            base.func(100, 50);     // Sub ... func
            Sub sub = new Sub();
            // sub.func(100, 50);   // 编译失败
        }
    }

}
