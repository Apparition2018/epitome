package knowledge.design.pattern.other.structural;

import java.awt.*;

/**
 * 私有类数据模式：通过将类属性封装在一个私有类中，来减少类属性的暴露
 * <p>角色：
 * <pre>
 * 主类 MainClass
 * 数据类 DataClass
 * </pre>
 * 参考：
 * <pre>
 * <a href="https://sourcemaking.com/design_patterns/private_class_data">Private Class Data</a>
 * <a href="https://java-design-patterns.com/patterns/private-class-data/">Private Class Data</a>
 * </pre>
 *
 * @author ljh
 * @since 2022/2/8 15:59
 */
public class PrivateClassDataDemo {

    public static void main(String[] args) {
        Circle circle = new Circle(2.0, Color.RED, new Point());
    }

    /**
     * MainCLass
     */
    private static class Circle {
        private final CircleData circleData;

        public Circle(double radius, Color color, Point origin) {
            this.circleData = new CircleData(radius, color, origin);
        }

        public double getCircumference() {
            return this.circleData.radius * Math.PI;
        }

        public double getDiameter() {
            return this.circleData.radius * 2;
        }

        public void draw(Graphics graphics) {
            //...
        }

        /**
         * DataClass
         */
        private record CircleData(double radius, Color color, Point origin) {
        }
    }
}
