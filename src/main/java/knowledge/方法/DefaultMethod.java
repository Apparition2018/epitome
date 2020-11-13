package knowledge.方法;

import l.demo.Demo;
import org.junit.Test;

/**
 * Default Method  默认方法
 * Default Method 是 JDK8 新特性，就是接口可以有实现方法，而且不需要实现类去实现其方法，在方法名前面加 default 关键字
 * 为了解决接口的修改与现有的实现不兼容的问题
 * 
 * default method 是一个好的设计吗？ - 知乎：https://www.zhihu.com/question/54479642/answer/139588886
 */
public class DefaultMethod extends Demo {

    @Test
    public void test() {
        Vehicle v = new Car();
        v.print();
        // 我是一辆车
        // 我是一辆四轮车！
        // 按喇叭！！！
        // 我是一辆汽车！
    }

    interface Vehicle {

        default void print() {
            p("我是一辆车");
        }

        /**
         * JDK8 新特性：接口可以声明（并且可以提供实现）静态方法
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

