package knowledge.design.pattern.gof.structural.flyweight;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 享元模式：运用共享技术复用大量细粒度对象
 * <p>使用场景：大量相似的不可变的对象，且占用大量内存
 * <p>使用实例：
 * <pre>
 * 1 {@link Integer#valueOf(int)}，类似还有 Boolean, Byte, Character, Short, Long, BigDecimal
 * 2 {@link String}
 * </pre>
 * 角色:
 * <pre>
 * 享元 Flyweight：可共享状态
 * 情景 Context：不可共享的状态
 * 享元工厂 FlyweightFactory
 * </pre>
 * 缺点：时间换空间
 * <p>扩展：
 * <pre>
 * 1 单纯享元模式：不存在 UnsharedConcreteFlyweight
 * 2 复合享元模式：单纯享元对象使用组合模式，组合成复合享元对象 CompositeConcreteFlyweight
 * </pre>
 * 参考：
 * <pre>
 * <a href="https://refactoringguru.cn/design-patterns/flyweight">Flyweight</a>
 * <a href="http://c.biancheng.net/view">Java设计模式</a>/1371.html
 * 设计模式之美：享元模式（下）：剖析享元模式在JavaInteger、String中的应用
 * </pre>
 *
 * @author ljh
 * @since 2020/9/26 2:51
 */
public class FlyweightDemo extends Demo {

    /**
     * <a href="http://c.biancheng.net/view/8471.html">String 使用了享元模式</a>
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
        p(s1 == s2);    // true
        p(s1 == s3);    // true
        p(s1 == s4);    // false
        p(s1 == s9);    // false
        p(s4 == s5);    // false
        p(s1 == s6);    // true
    }

    private static final int CANVAS_SIZE = 500;
    private static final int TREES_TO_DRAW = MILLION;
    private static final int TREE_TYPES = 2;

    /**
     * <a href="https://refactoringguru.cn/design-patterns/flyweight/java/example">渲染一片森林</a>
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

        p(TREES_TO_DRAW + " trees drawn");
        p("---------------------");
        p("Memory usage:");
        p("Tree size (8 bytes) * " + TREES_TO_DRAW);
        p("+ TreeTypes size (~30 bytes) * " + TREE_TYPES + "");
        p("---------------------");
        p("Total: " + ((TREES_TO_DRAW * 8 + TREE_TYPES * 30) / 1024 / 1024) +
                "MB (instead of " + ((TREES_TO_DRAW * 38) / 1024 / 1024) + "MB)");
    }

    private static int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    /**
     * Context
     */
    private record Tree(int x, int y, TreeType type) {
        public void draw(Graphics g) {
            type.draw(g, x, y);
        }
    }

    /**
     * Flyweight
     */
    private record TreeType(String name, Color color, String otherTreeData) {
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
    private static class TreeFactory {
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

    private static class Forest extends JFrame {
        @Serial
        private static final long serialVersionUID = -5174274255546068383L;
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
