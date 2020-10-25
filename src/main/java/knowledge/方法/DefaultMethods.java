package knowledge.方法;

import l.demo.Demo;
import org.junit.Test;

/**
 * Default Methods
 * 默认方式就是接口可以有实现方法，而且不需要实现类去实现其方法，在方法名前面加 default 关键字
 * 为了解决接口的修改与现有的实现不兼容的问题
 */
public class DefaultMethods extends Demo {

    @Test
    public void test() {
        Vehicle v = new Car();
        v.print();
    }

    interface Vehicle {

        default void print() {
            p("我是一辆车");
        }

        /**
         * Java8 新特性：接口可以声明（并且可以提供实现）静态方法
         * https://zhuanlan.zhihu.com/p/108274393
         */
        static void blowHorn() {
            p("按喇叭！！！");
        }

    }

    interface FourWheeler {
        default void print() {
            p("我是一辆四轮车！");
        }
    }

    /**
     * 多个同名默认方法
     */
    class Car implements Vehicle, FourWheeler {

        public void print() {
            Vehicle.super.print();
            FourWheeler.super.print();
            Vehicle.blowHorn();
            p("我是一辆汽车！");
        }
    }

}

