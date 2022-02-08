package knowledge.design.pattern.gof.behavioral.interpreter;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * 解释器模式：给分析对象定义一个语言，并定义该语言的文法表示，再设计一个解析器来解释语言中的句子
 * 使用场景：解析特定文法句子
 * 使用实例：
 * 1.{@link java.util.regex.Pattern}
 * 2.expression4J、MESP (Math Expression String Parser)、Jep
 * 3.SpEL：{@link org.springframework.expression.ExpressionParser}
 * <p>
 * 角色：
 * 抽象表达式 Expression：定义解释操作 interpret()
 * 终结符表达式 TerminalExpression
 * 非终结符表达式 NonTerminalExpression
 * 环境 Context：存储全局信息，公共功能
 * <p>
 * 优点：符合开闭原则
 * <p>
 * Java设计模式：http://c.biancheng.net/view/1402.html
 * 菜鸟模式：https://www.runoob.com/design-pattern/interpreter-pattern.html
 *
 * @author ljh
 * created on 2020/9/26 2:51
 */
public class InterpreterDemo {

    /**
     * 机器人控制
     * https://blog.csdn.net/LoveLion/article/details/7713593
     * https://blog.csdn.net/LoveLion/article/details/7713602
     */
    @Test
    public void testInterpreter() {
        InstructionHandler instructionHandler = new InstructionHandler();
        instructionHandler.handle("up move 5 and down run 10 and left move 5");
        System.out.println(instructionHandler.output());
    }

    /**
     * Expression
     */
    static abstract class Node {
        abstract String interpret();
    }

    /**
     * NonTerminalExpression
     * AND
     */
    @AllArgsConstructor
    static class AndNode extends Node {

        private final Node left;
        private final Node right;

        @Override
        public String interpret() {
            return left.interpret() + "再" + right.interpret();
        }
    }

    /**
     * NonTerminalExpression
     * 简单句子
     */
    @AllArgsConstructor
    static class SentenceNode extends Node {
        private final Node direction;
        private final Node action;
        private final Node distance;

        @Override
        public String interpret() {
            return direction.interpret() + action.interpret() + distance.interpret();
        }
    }

    /**
     * TerminalExpression
     * 方向
     */
    @AllArgsConstructor
    static class DirectionNode extends Node {
        private final String direction;

        @Override
        public String interpret() {
            if (("up").equalsIgnoreCase(direction)) {
                return "向上";
            } else if ("down".equalsIgnoreCase(direction)) {
                return "向下";
            } else if ("left".equalsIgnoreCase(direction)) {
                return "向左";
            } else if ("right".equalsIgnoreCase(direction)) {
                return "向右";
            } else {
                return "无效指令";
            }
        }
    }

    /**
     * TerminalExpression
     * 动作
     */
    @AllArgsConstructor
    static class ActionNode extends Node {
        private final String action;

        @Override
        public String interpret() {
            if (("move").equalsIgnoreCase(action)) {
                return "移动";
            } else if ("run".equalsIgnoreCase(action)) {
                return "快速移动";
            } else {
                return "无效指令";
            }
        }
    }

    /**
     * TerminalExpression
     * 距离
     */
    @AllArgsConstructor
    static class DistanceNode extends Node {
        private final String distance;

        @Override
        public String interpret() {
            return this.distance;
        }
    }

    static class InstructionHandler {
        private Node node;

        public void handle(String instruction) {
            Node left, right, direction, action, distance;
            // 存储抽象语法树
            Stack<Node> stack = new Stack<>();
            // 空格分隔指令
            String[] words = instruction.split(" ");
            for (int i = 0; i < words.length; i++) {
                if (words[i].equalsIgnoreCase("and")) {
                    left = stack.pop();
                    String word1 = words[++i];
                    direction = new DirectionNode(word1);
                    String word2 = words[++i];
                    action = new ActionNode(word2);
                    String word3 = words[++i];
                    distance = new DistanceNode(word3);
                    right = new SentenceNode(direction, action, distance);
                    stack.push(new AndNode(left, right));
                } else {
                    String word1 = words[i];
                    direction = new DirectionNode(word1);
                    String word2 = words[++i];
                    action = new ActionNode(word2);
                    String word3 = words[++i];
                    distance = new DistanceNode(word3);
                    left = new SentenceNode(direction, action, distance);
                    stack.push(left);
                }
            }
            this.node = stack.pop();
        }

        public String output() {
            return node.interpret();
        }
    }
}
