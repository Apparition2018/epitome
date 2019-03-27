package knowledge.方法;

/**
 * 默认方法 (Default Methods)
 * <p>
 * 默认方式就是接口可以有实现方法，而且不需要实现类去实现其方法，在方法名前面加 default 关键字
 * <p>
 * 为了解决接口的修改与现有的实现不兼容的问题
 */
public class DefaultMethods {

    public static void main(String[] args) {
        Vehicle v = new Car();
        v.print();
    }

}

interface Vehicle {

    default void print() {
        System.out.println("我是一辆车");
    }

    // Java 8 的另一个特性是接口可以声明（并且可以提供实现）静态方法
    static void blowHorn() {
        System.out.println("按喇叭！！！");
    }

}

interface FourWheeler {
    default void print(){
        System.out.println("我是一辆四轮车!");
    }
}

/**
 * 多个同名默认方法
 */
class Car implements Vehicle, FourWheeler  {

    public void print() {
        Vehicle.super.print();
        FourWheeler.super.print();
        Vehicle.blowHorn();
        System.out.println("我是一辆汽车！");
    }
}

