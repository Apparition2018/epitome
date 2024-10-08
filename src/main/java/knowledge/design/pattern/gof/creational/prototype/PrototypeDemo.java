package knowledge.design.pattern.gof.creational.prototype;

import l.demo.Person;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static l.demo.Demo.p;

/**
 * 原型模式：用一个已经创建的实例作为原型，通过复制该原型对象来创建一个和原型相同或相似的新对象
 * <p>使用场景：
 * <pre>
 * 1 对象创建成本较大（如需复杂计算，从 RPC、网络、数据库、文件系统等读取）
 * 2 需创建的对象与现有对象差别不大
 * 3 原型注册表
 * </pre>
 * 使用实例：
 * <pre>
 * 1 浅克隆：{@link Cloneable}、{@link Person#clone()}
 *     <a href="https://www.cnblogs.com/stevenshen123/p/9081118.html">clone() 效率分析：轻量级对象直接使用 new</a>
 * 2 深克隆：①递归 clone；②序列化；③SerializationUtils
 * </pre>
 * 角色：
 * <pre>
 * 抽象原型 Prototype：clone()
 * 具体原型 ConcretePrototype：实现 clone()，复制原型数据到克隆体
 * 原型注册表 PrototypeRegistry (可选)：注册表存储预生成对象，以供复制使用
 * </pre>
 * 缺点：违反开闭原则
 *
 * @author ljh
 * @see <a href="https://refactoringguru.cn/design-patterns/prototype">Prototype</a>
 * @see <a href="http://c.biancheng.net/view/1343.html">Java设计模式</a>
 * @see <a href="">设计模式之美：原型模式：如何最快速地 clone 一个 HashMap 散列表？</a>
 * @since 2020/9/26 2:51
 */
public class PrototypeDemo {

    /**
     * <a href="https://refactoringguru.cn/design-patterns/prototype/java/example">复制图形（不使用 Cloneable）</a>
     */
    public static void main(String[] args) {
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
    private static abstract class Shape {
        protected int x;
        protected int y;
        protected String color;

        private Shape(Shape target) {
            if (target != null) {
                this.x = target.x;
                this.y = target.y;
                this.color = target.color;
            }
        }

        public abstract Shape clone();

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Shape shape2)) return false;
            return shape2.x == x && shape2.y == y && Objects.equals(shape2.color, color);
        }
    }

    /**
     * ConcretePrototype
     */
    @NoArgsConstructor
    private static class Rectangle extends Shape {
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
            if (!(obj instanceof Rectangle rectangle2) || !super.equals(obj)) return false;
            return rectangle2.width == width && rectangle2.height == height;
        }
    }

    /**
     * PrototypeRegistry
     */
    private static class BundledShapeCache {
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

        public void remove(String key) {
            CACHE.remove(key);
        }
    }
}
