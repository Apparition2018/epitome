package other.设计模式.factory;

public class FactoryPatternDemo {
    public static void main(String[] args) {
        ShapeFactory sf = new ShapeFactory();

        Shape circle = sf.getShape("circle");
        circle.draw();

        Shape rectangle = sf.getShape("rectangle");
        rectangle.draw();

        Shape square = sf.getShape("square");
        square.draw();

        System.out.println("--------华丽的分界线--------");

        Rectangle rectangle2 = (Rectangle) ShapeReflectFactory.getClass(Rectangle.class);
        rectangle2.draw();

        Square square2 = (Square) ShapeReflectFactory.getClass(Square.class);
        square2.draw();
    }
}