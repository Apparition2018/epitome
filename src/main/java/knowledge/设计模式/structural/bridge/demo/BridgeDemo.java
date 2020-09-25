package knowledge.设计模式.structural.bridge.demo;

/**
 * Implementor: 实现化角色
 * ConcreteImplementor: 具体实现化角色
 * Abstraction: 抽象化角色
 * RefinedAbstraction: 修正抽象化角色
 * <p>
 * 桥接模式：将抽象部分与它的实现部分分离，使他们都可以独立地变化
 * <p>
 * https://www.cnblogs.com/java-my-life/archive/2012/05/07/2480938.html
 * https://www.runoob.com/design-pattern/bridge-pattern.html
 */
public class BridgeDemo {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100, 100, 10, new RedCircle());
        Shape greenCircle = new Circle(100, 100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}

// 实现化角色
interface DrawAPI {
    void drawCircle(int radius, int x, int y);
}

// 具体实现化角色
class RedCircle implements DrawAPI {

    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: red, radius: " + radius + ", x " + x + ", " + y + "]");
    }
}

class GreenCircle implements DrawAPI {

    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: green, radius: " + radius + ", x " + x + ", " + y + "]");
    }
}

// 抽象化角色
abstract class Shape {
    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}

// 修正抽象化角色
class Circle extends Shape {

    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void draw() {
        drawAPI.drawCircle(radius, x, y);
    }
}