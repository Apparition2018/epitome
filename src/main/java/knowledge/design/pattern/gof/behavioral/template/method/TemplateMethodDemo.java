package knowledge.design.pattern.gof.behavioral.template.method;

import jakarta.servlet.http.HttpServlet;
import knowledge.design.pattern.other.Idiom.CallbackDemo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
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
 * 1 {@link InputStream} {@link OutputStream} {@link Reader} {@link Writer} 所有非抽象方法
 * 2 {@link AbstractList} {@link AbstractSet} {@link AbstractMap} 所有非抽象方法
 * 3 {@link HttpServlet} 所有默认发送 HTTP 405 错误相应的 doXXX()
 * 4 {@link JdbcTemplate}
 * 5 {@link RedisTemplate}
 * 6 {@link TransactionTemplate}
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
 * @see <a href="http://c.biancheng.net/view/1376.html">Java设计模式</a>
 * @see <a href="https://gupaoedu-tom.blog.csdn.net/article/details/121194483">Tom|搞懂钩子方法</a>
 * @since 2020/9/26 2:51
 */
public class TemplateMethodDemo {

    public static void main(String[] args) {
        Game football = new Football();
        football.play();
    }

    /**
     * AbstractClass
     */
    private static abstract class Game {
        protected abstract void init();

        protected abstract void begin();

        protected abstract void finished();

        /**
         * 模板方法
         */
        private void play() {
            init();
            begin();
            finished();
        }
    }

    /**
     * ConcreteClass
     */
    private static class Football extends Game {
        @Override
        public void init() {
            System.out.println("Football Game Init");
        }

        @Override
        public void begin() {
            System.out.println("Football Game Begin");
        }

        @Override
        public void finished() {
            System.out.println("Football Game Finished");
        }
    }

    /**
     * ConcreteClass
     */
    private static class Basketball extends Game {
        @Override
        public void init() {
            System.out.println("Basketball Game Init");
        }

        @Override
        public void begin() {
            System.out.println("Basketball Game Begin");
        }

        @Override
        public void finished() {
            System.out.println("Basketball Game Finished");
        }
    }
}
