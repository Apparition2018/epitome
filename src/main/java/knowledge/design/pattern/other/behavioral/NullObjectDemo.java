package knowledge.design.pattern.other.behavioral;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Test;

/**
 * 空对象模式：空值默认行为代替空值判断
 * <p>
 * 角色：
 * <pre>
 * 抽象对象 AbstractObject：定义默认行为，和 Client 交互的对象
 * 真实对象 RealObject：实现预期行为
 * 空对象 NullObject：实现 null 值下的行为
 * </pre>
 * 参考：
 * <pre>
 * <a href="https://sourcemaking.com/design_patterns/null_object">Null Object</a>
 * <a href="https://java-design-patterns.com/patterns/null-object/">Null Object</a>
 * </pre>
 *
 * @author ljh
 * @since 2022/2/8 16:29
 */
public class NullObjectDemo {

    /**
     * 二叉树
     */
    @Test
    public void testNullObject() {
        INode root =
                new Node(
                        "1",
                        new Node(
                                "11",
                                new Node("111", NullNode.getInstance(), NullNode.getInstance()),
                                NullNode.getInstance()),
                        new Node(
                                "12",
                                NullNode.getInstance(),
                                new Node("122", NullNode.getInstance(), NullNode.getInstance())));

        root.walk();
    }

    /**
     * AbstractObject
     */
    interface INode {
        String getName();

        INode getLeft();

        INode getRight();

        int getTreeSize();

        void walk();
    }

    /**
     * RealObject
     */
    @Getter
    @AllArgsConstructor
    static class Node implements INode {
        private final String name;
        private final INode left;
        private final INode right;

        @Override
        public int getTreeSize() {
            return 1 + left.getTreeSize() + right.getTreeSize();
        }

        @Override
        public void walk() {
            System.out.println(this.name);
            if (left.getTreeSize() > 0) {
                left.walk();
            }
            if (right.getTreeSize() > 0) {
                right.walk();
            }
        }
    }

    /**
     * NullObject
     */
    static final class NullNode implements INode {
        private static final NullNode INSTANCE = new NullNode();

        private NullNode() {
        }

        public static NullNode getInstance() {
            return INSTANCE;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public INode getLeft() {
            return null;
        }

        @Override
        public INode getRight() {
            return null;
        }

        @Override
        public int getTreeSize() {
            return 0;
        }

        @Override
        public void walk() {
        }
    }
}
