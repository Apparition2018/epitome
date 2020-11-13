package knowledge.oop;

import org.junit.Test;

/**
 * 变量
 */
public class Variable {

    /**
     * 类变量 (Class Variables)，静态变量
     * <p>
     * 以 static 关键字声明，位于类中，方法和语句块之外
     * 无论一个类创建了多少个对象，类只拥有类变量的一份拷贝
     * 储存在静态存储区
     * 经常被声明为常量，很少单独使用 static 声明变量
     * 在类被访问时创建，在程序结束时销毁
     * 为了对类的使用者可见，大多数类变量声明为 public 类型
     * 数值型变量默认值是 0，布尔型默认值是 false，引用型默认值是 null
     * 变量的值可以在声明时指定，也可以在构造方法中指定，可以在静态语句块中初始化
     * 可以通过 ClassName.variableName 访问
     * 被 public static final 时，一般建议使用大写字母
     */
    private static double salary = 10000.0;
    private static final String DEPARTMENT = "软件开发";

    /**
     * 实例变量 (Instance Variables)，非静态变量
     * <p>
     * 位于类中，方法和语句块之外
     * 在对象创建的时候创建，在对象被销毁的时候销毁
     * 可以声明在使用前或者使用后 ???
     * 一般情况下设为 private
     * 数值型变量默认值是 0，布尔型默认值是 false，引用型默认值是 null
     * 变量的值可以在声明时指定，也可以在构造方法中指定
     * 在静态方法以及其他类中，通过 ObjectReference.VariableName 访问
     */
    private String name = "ljh";


    /**
     * 局部变量 (local Variables)
     * <p>
     * 位于方法和语句块之中
     * 在方法和语句块执行时创建，当它们执行完后，变量将被销毁
     * 不能被访问修饰符修饰
     * 在栈上分配空间
     * 没有默认值，所以局部变量被声明后，必须经过初始化，才可以使用
     */
    public void method() {
        int z = 0;
        System.out.println(z);
    }

    public static void main(String[] args) {
        System.out.println(Variable.salary);       // 类变量可通过 ClassName.variableName 访问
        System.out.println(new Variable().name);   // 实例变量通过 ObjectReference.variableName 访问
    }

    /**
     * 常量
     * 1）编译时常量
     * 2）运行时常量
     */
    private static class Constant {

        @Test
        public void test() {
            System.out.println(Constant.A.COMPILE_TIME_CONSTANT);
        }

        @Test
        public void test2() {
            System.out.println(Constant.A.RUN_TIME_CONSTANT);
        }

        private static class A {

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
    }


}
