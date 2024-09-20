package knowledge.oop.class_;

/**
 * 档案类
 * <pre>
 * 充当不可变数据的透明载体的类
 *
 * 1 使用 record 关键字
 * 2 类标识符后有用小括号括起来的参数，一起组成档案类形式的构造方法
 * 3 内置缺省的 equals()、hashCode()、toString()和参数的获取方法
 * </pre>
 * 为了强化“不可变”原则而作出的限制：
 * <pre>
 * 1 不支持扩展子句，用户不能定制它的弗雷。隐含的，它的父类是 java.lang.Record。
 *   父类不能定制，意味着不能通过修改父类来影响档案类的行为
 * 2 是终极(final)类，不支持子类，也不能是抽象的。
 *   没有子类，意味着不能通过修改子类来改变档案类的行为
 * 3 声明的变量是不可变的变量
 * 4 不支持实例初始化的方法，只能使用档案类形式的构造方法
 * 5 不能声明本地(native)方法
 * </pre>
 *
 * @author ljh
 * @see <a href="https://achang.blog.csdn.net/article/details/126394638">档案类</a>
 * @see <a href="https://openjdk.org/jeps/395">JDK16 JEP 395: Records</a>
 * @since 2023/2/16 11:03
 */
public class RecordClass {

    public static void main(String[] args) {
        Circle c1 = new Circle(10.0D);
        Circle c2 = new Circle(10.0D);
        System.out.println(c1.equals(c2));
    }

    private interface Shape {
        double area();
    }

    private record Circle(double radius) implements Shape {
        /**
         * 重写构造方法：注意构造方法没有参数，也有给变量赋值。实际上是 Java 在编译时加上去了
         */
        public Circle {
            if (radius < 0) {
                throw new IllegalArgumentException("The radius of a circle cannot be negative [" + radius + "]");
            }
        }

        @Override
        public double area() {
            return Math.PI * radius * radius;
        }
    }
}
