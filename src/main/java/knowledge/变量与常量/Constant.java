package knowledge.变量与常量;

import org.junit.Test;

/**
 * 常量：
 * 1）编译时常量
 * 2）运行时常量
 */
public class Constant {

    @Test
    public void test() {
        System.out.println(A.COMPILE_TIME_CONSTANT);
    }

    @Test
    public void test2() {
        System.out.println(A.RUN_TIME_CONSTANT);
    }
}

class A {

    static {
        System.out.println("Class A Was Loaded !");
    }

    /**
     * 编译时常量：
     * 编译后的符号表中将找不到 COMPILE_TIME_CONSTANT
     * 不依赖于类，所以访问它不会引发类的初始化
     */
    public static final int COMPILE_TIME_CONSTANT = 10;
    /**
     * 运行时常量：
     * 依赖于类
     */
    public static final int RUN_TIME_CONSTANT = "test".length();

}