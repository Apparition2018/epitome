package knowledge.面向对象;

/**
 * 接口
 * <p>
 * 接口与类的区别：
 * 1.接口不能用于实例化对象
 * 2.接口没有构造方法
 * 3.接口中所有的方法必须是抽象方法
 * 4.接口不能包含成员变量，除了 static 和 final 变量
 * 5.接口不是被类继承了，而是要被类实现
 * 6.接口支持多继承
 * <p>
 * 接口与抽象类的区别：
 * 1.接口中所有的方法必须是抽象方法
 * 2.接口中的成员变量只能是 public static final 类型的，抽象类可以是各种类型的
 * 3.接口中不能含有静态代码块和静态方法，抽象类可以有
 * 4.一个类可以实现多个接口，一个类只能继承一个抽象类
 * <p>
 * 抽象类和接口的区别，使用场景：https://www.iteye.com/blog/yinny-1152430
 */
public class Interface {

    /**
     * 接口特性：
     * 1.接口隐式抽象，abstract
     * 2.接口中的变量隐式指定为 public static final
     * 3.接口中的每一个方法隐式抽象，并且是公开的，public abstract
     * <p>
     * 接口可以继承接口
     */
    interface Sports {
        void setHomeTeam(String name);

        void setVisitingTeam(String name);
    }

    interface Football extends Sports {
        String Classification = "BALL";

        void homeTeamScored(int points);

        void visitingTeamScored(int points);

        void endOfQuarter(int quarter);
    }

/**
 * 接口标记 (Mark Interface)
 * Serializable Cloneable RandomAccess
 * https://blog.csdn.net/x_iya/article/details/79071839
 */
}
