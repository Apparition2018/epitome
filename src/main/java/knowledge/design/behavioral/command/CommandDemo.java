package knowledge.design.behavioral.command;

import org.junit.jupiter.api.Test;

/**
 * 命令模式：将一个请求封装成一个对象，从而让我们可用不同的请求对客户进行参数化；对请求排队或记录请求日志，以及支持可撤销的操作
 * 使用场景：
 * 1.撤销、恢复
 * 2.延迟操作
 * 3.
 * 使用实例：
 * 1.{@link Runnable} 的所有实现
 * 2.{@link javax.swing.Action} 的所有实现
 * <p>
 * 角色:
 * 抽象命令 Command：通常仅声明一个 execute()
 * 具体命令 ConcreteCommand
 * 调用者 Invoker
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
    
    @Test
    public void test() {
        
    }
}
