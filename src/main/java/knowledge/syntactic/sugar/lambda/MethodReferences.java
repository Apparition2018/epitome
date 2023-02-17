package knowledge.syntactic.sugar.lambda;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * 方法引用 (Method References)
 * <p>JDK8 新特性，一种特殊的 lambda 表达式，通过方法的名字来指向一个方法
 * <p>参考：
 * <pre>
 * <a href="https://www.baeldung.com/java-method-references">Method References in Java</a>
 * <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method References</a>
 * </pre>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class MethodReferences {

    public static void main(String[] args) {
        // 构造器引用：Class::new 或 Class<T>::new
        Bus car = Bus.create(Bus::new);
        List<Bus> cars = Collections.singletonList(car);

        // 静态方法引用：Class::static_method
        cars.forEach(Bus::collide);

        // 类的任意对象的实例方法引用：Class::method
        cars.forEach(Bus::repair);

        // 类的特定对象的实例方法引用：instance::method
        Bus police = Bus.create(Bus::new);
        cars.forEach(police::follow);
    }

    private static class Bus {

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
            System.out.println("Repaired " + this);
        }
    }
}
