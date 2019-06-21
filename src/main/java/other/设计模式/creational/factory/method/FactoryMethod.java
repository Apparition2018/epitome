package other.设计模式.creational.factory.method;

/**
 * 工厂方法模式：定义一个用于创建对象的接口，让子类决定将哪一个类实例化。使一个类的实例化延迟到其子类
 *
 * https://www.runoob.com/design-pattern/factory-pattern.html
 */
public class FactoryMethod {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        Shape rectangle = shapeFactory.getShape("rectangle");
        rectangle.draw();

        Shape square = shapeFactory.getShape("square");
        square.draw();

        Shape circle = shapeFactory.getShape("circle");
        circle.draw();
    }
}

interface Shape {
    void draw();
}

class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Rectangle: draw() method.");
    }
}

class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Square: draw() method.");
    }
}

class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Circle: draw() method.");
    }
}

class ShapeFactory {

    // 使用 getShape() 获取形状类型的对象
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("rectangle")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("square")) {
            return new Square();
        } else if (shapeType.equalsIgnoreCase("circle")) {
            return new Circle();
        }
        return null;
    }
}