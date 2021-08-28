package knowledge.design.creational.prototype;

import java.util.HashMap;

/**
 * 原型模式：用原型实例指定创建对象的种类，并且通过拷贝这个原型来创建新的对象
 * https://www.runoob.com/design-pattern/prototype-pattern.html
 *
 * @author ljh
 * created on 2020/9/26 2:51
 */
public class PrototypeDemo {
    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape clonedShape = ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape.getType());
    }

    static abstract class Shape implements Cloneable {

        private String id;
        protected String type;

        abstract void draw();

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object clone() {
            Object clone = null;
            try {
                clone = super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return clone;
        }
    }

    static class Rectangle extends Shape {

        public Rectangle() {
            type = "Rectangle";
        }

        @Override
        public void draw() {
            System.out.println("Inside Rectangle::draw() method.");
        }
    }

    static class Square extends Shape {

        Square() {
            type = "Square";
        }

        @Override
        public void draw() {
            System.out.println("Inside Square::draw() method.");
        }
    }

    static class Circle extends Shape {

        public Circle() {
            type = "Circle";
        }

        @Override
        public void draw() {
            System.out.println("Inside Circle::draw() method.");
        }
    }

    // 创建一个类，从数据库获取实体类，并把它们存储在一个 HashMap 中
    static class ShapeCache {
        private static HashMap<String, Shape> shapeMap = new HashMap<>();

        public static Shape getShape(String shapeId) {
            Shape cachedShape = shapeMap.get(shapeId);
            return (Shape) cachedShape.clone();
        }

        // 对每种形状都运行数据库查询，并创建该形状
        // shapeMap.put(shapeKey, shape);
        // 例如，我们要添加三种形状
        public static void loadCache() {
            Circle circle = new Circle();
            circle.setId("1");
            shapeMap.put(circle.getId(), circle);

            Square square = new Square();
            square.setId("2");
            shapeMap.put(square.getId(), square);

            Rectangle rectangle = new Rectangle();
            rectangle.setId("3");
            shapeMap.put(rectangle.getId(), rectangle);
        }
    }
}