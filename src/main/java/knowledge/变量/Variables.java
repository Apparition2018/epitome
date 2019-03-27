package knowledge.变量;

/**
 * 变量
 */
public class Variables {

    /**
     * 类变量 (Class Variables)，静态变量
     * <p>
     * 以 static 关键字声明，位于类中，方法和语句块之外
     * 无论一个类创建了多少个对象，类只拥有类变量的一份拷贝
     * 储存在静态存储区
     * 经常被声明为常量，很少单独使用 static 声明变量
     * 在类被访问时创建 ?，在程序结束时销毁
     * 为了对类的使用者可见 ?，大多数类变量声明为 public 类型
     * 数值型变量默认值是 0，布尔型默认值是 false，引用型默认值是 null
     * 变量的值可以在声明时指定，也可以在构造方法中指定，可以在静态语句块中初始化
     * 可以通过 ClassName.VariableName 访问
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
        System.out.println(Variables.salary);       // 类变量可通过 ClassName.VariableName 访问
        System.out.println(new Variables().name);   // 实例变量通过 ObjectReference.VariableName 访问
    }


}
