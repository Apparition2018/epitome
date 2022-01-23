package knowledge.design.creational.prototype;

import l.demo.Demo;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 原型模式：用一个已经创建的实例作为原型，通过复制该原型对象来创建一个和原型相同或相似的新对象
 * 使用场景：
 * 1。对象创建成本较大（如需复杂计算，从 RPC、网络、数据库、文件系统等读取），且需创建的对象与现有对象差别不大
 * 使用实例：
 * 1.浅拷贝：{@link Cloneable}
 * 2.深拷贝：①递归 clone；②序列化
 * <p>
 * 角色：
 * 抽象原型 Prototype：clone()
 * 具体原型 ConcretePrototype：实现 clone()
 * 原型注册表 PrototypeRegistry (可选)：注册表存储对象，以供复制使用
 * <p>
 * 缺点：违反开闭原则
 * <p>
 * Prototype：https://refactoringguru.cn/design-patterns/prototype
 * Java设计模式：http://c.biancheng.net/view/1343.html
 * 菜鸟教程：https://www.runoob.com/design-pattern/prototype-pattern.html
 * 设计模式之美：原型模式：如何最快速地 clone 一个 HashMap 散列表？
 *
 * @author ljh
 * created on 2020/9/26 2:51
 */
public class PrototypeDemo extends Demo {

    /**
     * 复制图形（不使用 Cloneable）
     * https://refactoringguru.cn/design-patterns/prototype/java/example
     */
    @Test
    public void testPrototype() {
        Rectangle rectangle = new Rectangle();
        rectangle.width = 10;
        rectangle.height = 20;
        rectangle.color = "blue";
        Shape clone = rectangle.clone();
        p(rectangle == clone);                                          // false
        p(Objects.equals(rectangle, clone));                            // true

        BundledShapeCache cache = new BundledShapeCache();
        Shape mediumBlueRectangle = cache.get("Medium blue rectangle");
        Shape mediumBlueRectangle2 = cache.get("Medium blue rectangle");
        p(mediumBlueRectangle == mediumBlueRectangle2);                 // false
        p(Objects.equals(mediumBlueRectangle, mediumBlueRectangle2));   // true
    }

    /**
     * Prototype
     */
    @NoArgsConstructor
    abstract static class Shape {
        public int x;
        public int y;
        public String color;

        public Shape(Shape target) {
            if (target != null) {
                this.x = target.x;
                this.y = target.y;
                this.color = target.color;
            }
        }

        public abstract Shape clone();

        @Override
        public boolean equals(Object obj) {
            if ((!(obj instanceof Shape))) return false;
            Shape shape2 = (Shape) obj;
            return shape2.x == x && shape2.y == y && Objects.equals(shape2.color, color);
        }
    }

    /**
     * ConcretePrototype
     */
    @NoArgsConstructor
    static class Rectangle extends Shape {
        public int width;
        public int height;

        public Rectangle(Rectangle target) {
            super(target);
            if (target != null) {
                this.width = target.width;
                this.height = target.height;
            }
        }

        @Override
        public Shape clone() {
            return new Rectangle(this);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Rectangle) || !Objects.equals(this, obj)) return false;
            Rectangle rectangle2 = (Rectangle) obj;
            return rectangle2.width == width && rectangle2.height == height;
        }
    }

    /**
     * PrototypeRegistry
     */
    static class BundledShapeCache {
        private final Map<String, Shape> CACHE = new HashMap<>();

        public BundledShapeCache() {
            Rectangle rectangle = new Rectangle();
            rectangle.x = 6;
            rectangle.y = 9;
            rectangle.width = 8;
            rectangle.height = 10;
            rectangle.color = "Blue";
            CACHE.put("Medium blue rectangle", rectangle);
        }

        public Shape put(String key, Shape shape) {
            CACHE.put(key, shape);
            return shape;
        }

        public Shape get(String key) {
            return CACHE.get(key).clone();
        }
    }
}