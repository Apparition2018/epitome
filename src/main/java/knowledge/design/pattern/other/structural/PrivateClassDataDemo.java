package knowledge.design.pattern.other.structural;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.awt.*;

/**
 * 私有类数据模式：通过将类属性封装在一个私有类中，来减少类属性的暴露
 * <p>
 * 角色：
 * 主类 MainClass
 * 数据类 DataClass
 * <p>
 * Private Class Data：https://sourcemaking.com/design_patterns/private_class_data
 * Private Class Data：https://java-design-patterns.com/patterns/private-class-data/
 *
 * @author ljh
 * @since 2022/2/8 15:59
 */
public class PrivateClassDataDemo {

    @Test
    public void testPrivateClassData() {
        Circle circle = new Circle(2.0, Color.RED, new Point());
    }

    /**
     * MainCLass
     */
    static class Circle {
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
        @Getter
        @AllArgsConstructor
        static class CircleData {
            private final double radius;
            private final Color color;
            private final Point origin;
        }
    }
}
