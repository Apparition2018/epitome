package knowledge.design.pattern.gof.behavioral.template.method;

import jakarta.servlet.http.HttpServlet;
import knowledge.design.pattern.gof.behavioral.strategy.SpringStrategyFactoryDemo;
import knowledge.design.pattern.other.Idiom.CallbackDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSet;

/**
 * 模板方法模式：定义一个操作中的算法骨架，而将算法的一些步骤延迟到子类中，使得子类可以不改变该算法结构的情况下重定义该算法的某些特定步骤
 * <p>使用场景：多个类可抽象出相同的算法步骤，算法步骤的实现可以相同（复用），也可以有各自不同的实现（扩展)
 * <p>使用实例：
 * <pre>
 * 1 JDK：
 *  ① {@link InputStream} {@link OutputStream} {@link Reader} {@link Writer} 所有非抽象方法
 *  ② {@link AbstractList} {@link AbstractSet} {@link AbstractMap} 所有非抽象方法
 *  ③ {@link HttpServlet} 所有默认发送 HTTP 405 错误相应的 doXXX()
 * 2 Spring：
 *  ① {@link JdbcTemplate}
 *  ② {@link RedisTemplate}
 *  ③ {@link TransactionTemplate}
 * </pre>
 * 角色：
 * <pre>
 * 抽象类 AbstractClass：
 * 1 基本方法：
 *   1.1 抽象方法：子类必须实现
 *   1.2 默认方法：子类可重写，注意不要违反里氏替换原则
 *   1.3 钩子方法：子类可重写，使得子类可以控制父类的行为
 *     1.3.1 用于逻辑判断的方法，返回类型通常为 boolean，方法名通常为 isXXX()
 *     1.3.2 空方法
 * 2 模板方法：按某种顺序调用基本方法
 * </pre>
 * 实现类 ConcreteClass：重写步骤方法
 * <p>优点：符合单一职责原则、开闭原则
 * <p>优化：函数式接口优化模板方法模式 {@link CallbackDemo}
 * <p>
 *
 * @author ljh
 * @see <a href="https://refactoringguru.cn/design-patterns/template-method">Template Method</a>
 * @see <a href="https://c.biancheng.net/view/p0vgdz7.html">Java设计模式</a>
 * @see <a href="https://gupaoedu-tom.blog.csdn.net/article/details/121194483">Tom|搞懂钩子方法</a>
 * @see SpringStrategyFactoryDemo
 * @since 2020/9/26 2:51
 */
@Service
public class TemplateMethodDemo {

    @Autowired
    private NotificationTemplate emailNotification;
    @Autowired
    private NotificationTemplate smsNotification;

    public void completeOrder(String orderId, String type) {
        // 1. 处理订单逻辑...
        System.out.println("订单 " + orderId + " 处理完成");

        // 2. 根据类型调用对应的模板子类
        NotificationTemplate template;
        if ("EMAIL".equalsIgnoreCase(type)) {
            template = emailNotification;
        } else if ("SMS".equalsIgnoreCase(type)) {
            template = smsNotification;
        } else {
            throw new IllegalArgumentException("暂不支持的通知类型: " + type);
        }
        // 执行模板方法（固定流程 + 钩子方法）
        template.execute("您的订单已完成！");
    }
}

/** AbstractClass */
abstract class NotificationTemplate {

    // 模板方法：定义算法骨架（final 防止子类破坏）
    public final void execute(String message) {
        validate();
        String content = buildContent(message);
        doSend(content);
        recordLog();
    }

    protected void validate() {
        System.out.println("参数校验通过");
    }

    protected String buildContent(String message) {
        return "[系统通知] " + message;
    }

    protected abstract void doSend(String content);

    protected void recordLog() {
        System.out.println("记录通知日志");
    }
}

/** ConcreteClass */
@Service
class EmailNotification extends NotificationTemplate {

    @Override
    protected void doSend(String content) {
        System.out.println("发送邮件通知: " + content);
    }
}

/** ConcreteClass */
@Service
class SmsNotification extends NotificationTemplate {

    @Override
    protected void doSend(String content) {
        System.out.println("发送短信通知: " + content);
    }
}
