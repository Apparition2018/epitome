package knowledge.algorithm;

import java.util.stream.IntStream;

import static l.demo.Demo.p;

/**
 * Recursion
 * <p>递归：
 * <pre>
 * 程序调用自身的编程技巧叫做递归。
 * 是一个过程或函数在其定义或说明中直接或间接调用自身的一种方法，
 * 通常把一个大型复杂的问题层层转化为一个与原问题相似的规模较小的问题来求解。
 * 递归策略只需少量的程序就可描述出解题过程所需要的多次重复计算，大大地减少了程序的代码量。
 * 递归的能力在于用有限的语句来定义对象的无限集合。
 * </pre>
 * 注意：
 * <pre>
 * 1 递归就是在过程或函数里调用自身;
 * 2 在使用递增归策略时，必须有一个明确的递归结束条件，称为递归出口。
 * </pre>
 * 缺点：
 * <pre>
 * 1 递归算法解题的运行效率较低。
 * 2 在递归调用的过程当中系统为每一层的返回点、局部量等开辟栈来存储。
 * 3 递归次数过多容易造成栈溢出等。
 * </pre>
 * 一般用于解决三类问题：
 * <pre>
 * 1 数据的定义是按递归定义的。(Fibonacci函数)
 * 2 问题解法按递归算法实现。(回溯)
 * 3 数据的结构形式是按递归定义的。(树的遍历，图的搜索)
 * </pre>
 * 参考：<a href="https://blog.csdn.net/u012426327/article/details/77418042">浅析线性递归和尾递归</a>
 *
 * @author ljh
 * @since 2020/9/29 11:13
 */
public class Recursion {

    /**
     * 阶乘
     */
    private static class Factorial {

        public static void main(String[] args) {
            p(factorial(10));
        }

        private static long factorial(long num) {
            return num <= 1 ? 1 : num * factorial(num - 1);
        }
    }

    /**
     * 斐波那契数列
     * <pre>
     * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, ...
     * 第0项是0，第1项是1，从第3项开始，每一项等于前两项之和
     * </pre>
     */
    private static class FibonacciSequence {

        public static void main(String[] args) {
            IntStream.range(0, 100).forEach(i -> System.out.print(fibonacci(i) + " "));
        }

        private static long fibonacci(long num) {
            return (num == 0 || num == 1) ? num : fibonacci(num - 1) + fibonacci(num - 2);
        }

    }

    /**
     * 1+2+3+...+100 并输出结果，在该段代码中不得出现 for，while 关键字
     */
    private static class Recursion01 {

        public static void main(String[] args) {
            p(rec1(100));
            p(rec2(100));
        }

        //********** 方法一 **********//
        private static int rec1(int n) {
            if (n == 1) { // 递归出口
                return n;
            }
            return n + rec1(n - 1);
        }

        //********** 方法二 **********//
        private static int sum = 0;
        private static int i = 1;

        private static int rec2(int n) {
            if (n > 0) {
                sum += i;
                ++i;
                rec2(--n);
            }
            return sum;
        }
    }

    /**
     * <pre>
     * 一个人去买汽水，1块钱一瓶汽水，3个瓶盖可以换一瓶汽水，2个空瓶可以换一瓶汽水。
     * 编写一段代码计算20块钱总共可以得到多少瓶汽水？
     * </pre>
     */
    private static class Recursion02 {

        public static void main(String[] args) {
            p(rec1(20, 20, 20));
            p(rec2(20));
        }

        //********** 方法一 **********//
        private static int rec1(int qs, int pg, int kp) {
            if (pg < 3 && kp < 2) { // 递归出口
                return qs;
            }
            int qs2 = pg / 3 + kp / 2;
            qs += qs2;
            pg = pg % 3 + qs2;
            kp = kp % 2 + qs2;
            return rec1(qs, pg, kp);
        }

        //********** 方法二 **********//
        private static int qs = 0;
        private static int pg = 0;
        private static int kp = 0;

        private static int rec2(int money) {
            ++qs;
            ++pg;
            ++kp;
            if (money > 1) {
                --money;
                rec2(money);
            }
            if (pg >= 3) {
                pg -= 3;
                rec2(money);
            }
            if (kp >= 2) {
                kp -= 2;
                rec2(money);
            }
            return qs;
        }

    }

    /**
     * 计算二进制中1的个数
     */
    private static class Recursion03 {

        public static void main(String[] args) {
            p(calNumOfOneInBinary(10));
        }

        private static int calNumOfOneInBinary(int num) {
            if (num == 1) {
                return 1;
            } else if (num % 2 == 0) {
                return calNumOfOneInBinary(num / 2);
            } else {
                return 1 + calNumOfOneInBinary(num / 2);
            }
        }
    }
}
