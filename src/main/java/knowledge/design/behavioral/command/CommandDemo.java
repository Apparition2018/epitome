package knowledge.design.behavioral.command;

import org.junit.jupiter.api.Test;

/**
 * 命令模式：将请求封装为对象，使请求的发送者和接收者解耦，从而增强请求的能力
 * 使用场景：控制请求的执行，如异步、延迟、排队执行、撤销/重做、记录日志等
 * 使用实例：
 * 1.{@link Runnable} 的所有实现
 * 2.{@link javax.swing.Action} 的所有实现
 * <p>
 * 角色:
 * 调用者 Invoker：接收 Command 的引用，请求时调用 command.execute()
 * 抽象命令 Command：通常仅声明一个 execute()
 * 具体命令 ConcreteCommand：实现 execute()，把工作委派给 Receiver
 * 接收者 Receiver
 * <p>
 * 优点：符合单一职责原则、开闭原则
 * TODO-LJH: 2022/1/26 与组合模式联合使用，构成宏命令
 * <p>
 * Command：https://refactoringguru.cn/design-patterns/command
 * Java设计模式：http://c.biancheng.net/view/1380.html
 * 菜鸟模式：https://www.runoob.com/design-pattern/command-pattern.html
 *
 * @author ljh
 * created on 2020/9/26 2:51
 */
public class CommandDemo {

    /**
     * https://blog.csdn.net/LoveLion/article/details/8806509
     */
    @Test
    public void test() {
    }

}
