package knowledge.design.structural.bridge;

/**
 * 桥接模式：将类拆分为抽象部分和实现部分，从而能在开发时分别使用
 * 角色：
 * 抽象部分 Abstraction
 * 精确抽象 RefinedAbstraction
 * 实现部分 Implementor
 * 具体实现 ConcreteImplementor
 * <p>
 * 优点：符合单一责任原则，开闭原则
 * <p>
 * Bridge：https://refactoringguru.cn/design-patterns/bridge
 * JAVA与模式：https://www.cnblogs.com/java-my-life/archive/2012/05/07/2480938.html
 * 菜鸟教程：https://www.runoob.com/design-pattern/bridge-pattern.html
 *
 * @author ljh
 * created on 2020/11/23 19:38
 */
public class BridgeDemo {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100, 100, 10, new RedCircle());
        Shape greenCircle = new Circle(100, 100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }

    // 实现化角色
    interface DrawAPI {
        void drawCircle(int radius, int x, int y);
    }

    // 具体实现化角色
    static class RedCircle implements DrawAPI {

        @Override
        public void drawCircle(int radius, int x, int y) {
            System.out.println("Drawing Circle[ color: red, radius: " + radius + ", x " + x + ", " + y + "]");
        }
    }

    static class GreenCircle implements DrawAPI {

        @Override
        public void drawCircle(int radius, int x, int y) {
            System.out.println("Drawing Circle[ color: green, radius: " + radius + ", x " + x + ", " + y + "]");
        }
    }

    // 抽象化角色
    static abstract class Shape {
        protected DrawAPI drawAPI;

        protected Shape(DrawAPI drawAPI) {
            this.drawAPI = drawAPI;
        }

        public abstract void draw();
    }

    // 修正抽象化角色
    static class Circle extends Shape {

        private final int x, y, radius;

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
}