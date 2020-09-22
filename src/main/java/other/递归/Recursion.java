package other.递归;

/**
 * 递归：
 * 程序调用自身的编程技巧叫做递归。
 * 是一个过程或函数在其定义或说明中直接或间接调用自身的一种方法，
 * 通常把一个大型复杂的问题层层转化为一个与原问题相似的规模较小的问题来求解。
 * 递归策略只需少量的程序就可描述出解题过程所需要的多次重复计算，大大地减少了程序的代码量。
 * 递归的能力在于用有限的语句来定义对象的无限集合。
 * <p>
 * 注意：
 * 1.递归就是在过程或函数里调用自身;
 * 2.在使用递增归策略时，必须有一个明确的递归结束条件，称为递归出口。
 * <p>
 * 缺点：
 * 1.递归算法解题的运行效率较低。
 * 2.在递归调用的过程当中系统为每一层的返回点、局部量等开辟栈来存储。
 * 3.递归次数过多容易造成栈溢出等。
 * <p>
 * 一般用于解决三类问题：
 * 1.数据的定义是按递归定义的。(Fibonacci函数)
 * 2.问题解法按递归算法实现。(回溯)
 * 3.数据的结构形式是按递归定义的。(树的遍历，图的搜索)
 * <p>
 * StackOverflowError: 由于当前线程的栈满了，也就是函数调用层级过多导致。
 */
public class Recursion {
    public static void main(String[] args) {
        System.out.println(rec1(100));
        System.out.println(rec2(100));
        for1(100);

        System.out.println(rec3(20, 20, 20));
        System.out.println(rec4(20));
    }

    /**
     * 1)编译一段代码，要求实现1+2+3+...+100,并输出结果，
     * 在该段代码中不得出现for，while关键字
     */
    private static int rec1(int n) {
        if (n == 1) { // 递归出口
            return n;
        }
        return n + rec1(n - 1);
    }

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

    private static void for1(int n) {
        int sum2 = 0;
        for (int i = 1; i <= n; i++) {
            sum2 += i;
        }
        System.out.println(sum2);
    }

    /**
     * 2)一个人去买汽水，1块钱一瓶汽水，3个瓶盖可以换一瓶汽水，2个空瓶可以换一瓶汽水。
     * 编写一段代码计算20块钱总共可以得到多少瓶汽水？
     */
    private static int rec3(int qs, int pg, int kp) {
        if (pg < 3 && kp < 2) { // 递归出口
            return qs;
        }
        int qs2 = pg / 3 + kp / 2;
        qs += qs2;
        pg = pg % 3 + qs2;
        kp = kp % 2 + qs2;
        return rec3(qs, pg, kp);
    }

    private static int qs = 0;
    private static int pg = 0;
    private static int kp = 0;

    private static int rec4(int money) {
        ++qs;
        ++pg;
        ++kp;
        if (money > 1) {
            --money;
            rec4(money);
        }
        if (pg >= 3) {
            pg -= 3;
            rec4(money);
        }
        if (kp >= 2) {
            kp -= 2;
            rec4(money);
        }
        return qs;
    }
}


