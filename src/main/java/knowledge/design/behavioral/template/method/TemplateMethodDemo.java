package knowledge.design.behavioral.template.method;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSet;

/**
 * 模板方法模式：定义一个操作中的算法的骨架，而将一些步骤延迟到子类中。模板方法使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤
 * 使用场景：
 * 使用实例：
 * 1.{@link InputStream} {@link OutputStream} {@link Reader} {@link Writer} 所有非抽象方法
 * 2.{@link AbstractList} {@link AbstractSet} {@link AbstractMap} 所有非抽象方法
 * 3.{@link javax.servlet.http.HttpServlet} 所有默认发送 HTTP 405 错误相应的 doXXX()
 * <p>
 * 角色：
 * 抽象类 AbstractClass：声明算法步骤方法，以及依次调用它们的实际模板方法；算法步骤可声明为 abstract，也可提供默认
 * 实现类 ConcreteClass：
 * <p>
 * 优点：
 * <p>
 * Template Method：https://refactoringguru.cn/design-patterns/template-method
 * Java设计模式：http://c.biancheng.net/view/1376.html
 * 菜鸟教程：https://www.runoob.com/design-pattern/template-pattern.html
 *
 * @author ljh
 * created on 2020/9/26 2:51
 */
public class TemplateMethodDemo {

    @Test
    public void testTemplateMethod() {

    }
}
