package knowledge.方法;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * 方法引用 (Method References)
 * <p>
 * 方法引用是 Java8 的新特性，通过方法的名字来指向一个方法
 */
public class MethodReferences {

    public static void main(String[] args) {

        // 构造器引用：Class::new，或 Class< T >::new
        final Bus car = Bus.create(Bus::new);
        final List<Bus> cars = Arrays.asList(car);

        // 静态方法引用：Class::static_method
        cars.forEach(Bus::collide);

        // 特定类的任意对象的方法引用：Class::method
        cars.forEach(Bus::repair);

        // 特定对象的方法引用：instance::method
        final Bus police = Bus.create(Bus::new);
        cars.forEach(police::follow);
    }

}

class Bus {

    public static Bus create(final Supplier<Bus> supplier) {
        return supplier.get();
    }

    public static void collide(final Bus car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final Bus another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }


}
