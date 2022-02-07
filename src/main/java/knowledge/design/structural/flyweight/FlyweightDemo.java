package knowledge.design.structural.flyweight;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 享元模式：运用共享技术复用大量细粒度对象
 * 使用场景：大量相似的不可变的对象，且占用大量内存
 * 使用实例：
 * 1.线程池、连接池、常量池等
 * 2.{@link java.lang.Integer#valueOf(int)}，类似还有 Boolean, Byte, Character, Short, Long, BigDecimal
 * 3.{@link String}
 * <p>
 * 角色:
 * 享元 Flyweight：可共享状态
 * 情景 Context：不可共享的状态
 * 享元工厂 FlyweightFactory
 * <p>
 * 缺点：时间换空间
 * 扩展：
 * 1.单纯享元模式：不存在 UnsharedConcreteFlyweight
 * 2.复合享元模式：单纯享元对象使用组合模式，组合成复合享元对象 CompositeConcreteFlyweight
 * <p>
 * Flyweight：https://refactoringguru.cn/design-patterns/flyweight
 * Java设计模式：http://c.biancheng.net/view/1371.html
 * 菜鸟教程：https://www.runoob.com/design-pattern/flyweight-pattern.html
 * 设计模式之美：享元模式（下）：剖析享元模式在JavaInteger、String中的应用
 *
 * @author Arsenal
 * created on 2020/9/26 2:51
 */
public class FlyweightDemo {

    /**
     * String 使用了享元模式：http://c.biancheng.net/view/8471.html
     */
    @Test
    public void testString() {
        String s1 = "hello";
        String s2 = "hello";
        String s3 = "he" + "llo";
        String s4 = "hel" + new String("lo");
        String s5 = new String("hello");
        String s6 = s5.intern();
        String s7 = "h";
        String s8 = "ello";
        String s9 = s7 + s8;
        System.out.println(s1 == s2);   // true
        System.out.println(s1 == s3);   // true
        System.out.println(s1 == s4);   // false
        System.out.println(s1 == s9);   // false
        System.out.println(s4 == s5);   // false
        System.out.println(s1 == s6);   // true
    }

    private static final int CANVAS_SIZE = 500;
    private static final int TREES_TO_DRAW = 1000000;
    private static final int TREE_TYPES = 2;

    /**
     * 渲染一片森林
     * https://refactoringguru.cn/design-patterns/flyweight/java/example
     */
    public static void main(String[] args) {
        Forest forest = new Forest();
        for (int i = 0; i < TREES_TO_DRAW / TREE_TYPES; i++) {
            forest.plantTree(random(0, CANVAS_SIZE), random(0, CANVAS_SIZE),
                    "Summer Oak", Color.GREEN, "Oak texture stub");
            forest.plantTree(random(0, CANVAS_SIZE), random(0, CANVAS_SIZE),
                    "Autumn Oak", Color.ORANGE, "Autumn Oak texture stub");
        }
        forest.setSize(CANVAS_SIZE, CANVAS_SIZE);
        forest.setVisible(true);

        System.out.println(TREES_TO_DRAW + " trees drawn");
        System.out.println("---------------------");
        System.out.println("Memory usage:");
        System.out.println("Tree size (8 bytes) * " + TREES_TO_DRAW);
        System.out.println("+ TreeTypes size (~30 bytes) * " + TREE_TYPES + "");
        System.out.println("---------------------");
        System.out.println("Total: " + ((TREES_TO_DRAW * 8 + TREE_TYPES * 30) / 1024 / 1024) +
                "MB (instead of " + ((TREES_TO_DRAW * 38) / 1024 / 1024) + "MB)");
    }

    private static int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    /**
     * Context
     */
    @AllArgsConstructor
    static class Tree {
        private final int x;
        private final int y;
        private final TreeType type;

        public void draw(Graphics g) {
            type.draw(g, x, y);
        }
    }

    /**
     * Flyweight
     */
    @AllArgsConstructor
    static class TreeType {
        private final String name;
        private final Color color;
        private final String otherTreeData;

        public void draw(Graphics g, int x, int y) {
            g.setColor(Color.BLACK);
            g.fillRect(x - 1, y, 3, 5);
            g.setColor(color);
            g.fillOval(x - 5, y - 10, 10, 10);
        }
    }

    /**
     * FlyweightFactory
     */
    static class TreeFactory {
        static Map<String, TreeType> treeTypes = new HashMap<>();

        public static TreeType getTreeType(String name, Color color, String otherTreeData) {
            TreeType result = treeTypes.get(name);
            if (result == null) {
                result = new TreeType(name, color, otherTreeData);
                treeTypes.put(name, result);
            }
            return result;
        }
    }

    static class Forest extends JFrame {
        private final List<Tree> trees = new ArrayList<>();

        public void plantTree(int x, int y, String name, Color color, String otherTreeData) {
            TreeType type = TreeFactory.getTreeType(name, color, otherTreeData);
            Tree tree = new Tree(x, y, type);
            trees.add(tree);
        }

        @Override
        public void paint(Graphics graphics) {
            for (Tree tree : trees) {
                tree.draw(graphics);
            }
        }
    }

}