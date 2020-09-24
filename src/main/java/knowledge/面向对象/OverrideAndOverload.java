package knowledge.面向对象;

/**
 * 重载 和 重写
 * <p>
 * 重载：同一个类中，方法名相同，参数列表不同，返回值类型无关，访问权限无关
 * 重写：父子类之间，方法名相同，参数列表相同，返回值类型相同，访问权限>=父
 * <p>
 * 重载："编译期绑定"，看引用类型
 * 重写："运行时绑定"，看对象类型
 * <p>
 * 重载重和写是 Java 多态性的不同表现，
 * 重载是一个类的多态性表现，
 * 重写是子类与父类的一种多态性表现。
 */
public class OverrideAndOverload {

    public void say() {
        System.out.println("我是父亲");
    }

    /**
     * 重载
     * <p>
     * 编译时多态
     * <p>
     * 可以声明新的或更广的检查异常
     */
    public void say(String name) {
        System.out.println("我是" + name);
    }


    private class Override extends OverrideAndOverload {

        /**
         * 重写
         * <p>
         * 运行时多态
         * <p>
         * 1.参数列表和重写方法的相同
         * 2.返回值类型和重写方法的相同
         * 3.访问权限 >= 重写方法的访问权限
         * 4.父类的成员方法只能被它的子类重写 ???
         * 5.final 方法不能被重写
         * 6.static 方法不能被重写，但可以再次声明
         * 7.不能抛出新的检测性异常，或比重写方法更广泛的检测性异常
         * 8.构造方法不能被重写
         */
        public void say() {
            System.out.println("我是儿子");
        }

    }

}
