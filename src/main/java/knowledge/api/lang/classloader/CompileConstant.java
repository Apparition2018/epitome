package knowledge.api.lang.classloader;

/**
 * 常量分为编译时常量和运行时常量
 */
public class CompileConstant {

    public static void main(String[] args) {
        System.out.println(A.a);
        // 10
        System.out.println(A.b);
        // Class A Was Loaded !
        // 4
    }
}

class A {

    static {
        System.out.println("Class A Was Loaded !");
    }

    public static final int a = 10; // 编译时常量，编译后的符号表中将找不到a；不依赖于类，所以访问它不会引发类的初始化
    public static final int b = "test".length(); // 运行时常量；依赖于类
}

