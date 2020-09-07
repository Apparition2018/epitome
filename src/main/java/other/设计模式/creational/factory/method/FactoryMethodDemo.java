package other.设计模式.creational.factory.method;

import knowledge.数据结构.集合框架.map.properties.PropertiesUtil;

/**
 * 工厂模式：定义一个创建对象的接口，让子类来决定哪些类需要被实例化，使一个类的实例化推迟到子类。工厂模式是抽象工厂的一种常见情况。
 * <p>
 * 应用场合：计划不同条件下创建不同实例
 * <p>
 * 使用场景：
 * 1.数据存储
 * <p>
 * 关键代码：抽象产品
 * <p>
 * 工厂模式 | 菜鸟教程：https://www.runoob.com/design-pattern/factory-pattern.html
 * 工厂模式-场景以及优缺点：https://www.cnblogs.com/aspirant/p/8980573.html
 * 简单工厂模式及在实际项目中的应用：https://www.jianshu.com/p/72567cc1d63f
 * 工厂方法：https://blog.csdn.net/wangwei129549/article/details/50623809
 * <p>
 * https://www.imooc.com/learn/261
 *
 * @author ljh
 * created on 2019/11/1 9:36
 */
public class FactoryMethodDemo {

    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();

        Shape rectangle = factory.getShape("Rectangle");
        rectangle.draw();

        Shape square = factory.getShape("Square");
        square.draw();

        square = factory.getShapeByClassKey("Square");
        square.draw();

        Shape circle = factory.getShapeByClassKey("Circle");
        circle.draw();
    }

}

/**
 * 抽象产品
 */
interface Shape {
    void draw();
}

class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Rectangle.draw");
    }
}

class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Square.draw");
    }
}

class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Circle.draw");
    }
}

class ShapeFactory {

    Shape getShape(String shapeType) {
        if ("Rectangle".equalsIgnoreCase(shapeType)) {
            return new Rectangle();
        } else if ("Square".equalsIgnoreCase(shapeType)) {
            return new Square();
        } else if ("Circle".equalsIgnoreCase(shapeType)) {
            return new Circle();
        }
        return null;
    }

    Shape getShapeByClassKey(String key) {
        try {
            String className = PropertiesUtil.getProperty("src/main/java/other/设计模式/creational/factory/method/shape.properties", key);
            return (Shape) Class.forName(className).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}