package knowledge.design.behavioral.visitor;

import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.ElementVisitor;
import javax.lang.model.type.TypeVisitor;

/**
 * 访问者模式：表示一个作用于某对象结构中的各元素的操作。它使你可以在不改变各元素的类的前提下定义作用与这些元素的新操作。即对于某个对象或者一组对象，不同的访问者，产生的结果不同，执行操作也不同
 * 使用场景：
 * 使用实例：
 * 1.{@link java.nio.file.FileVisitor} 和 {@link java.nio.file.SimpleFileVisitor}：http://c.biancheng.net/view/8501.html
 * 2.{@link AnnotationValueVisitor}, {@link ElementVisitor} 和 {@link TypeVisitor}
 * 3.{@link org.springframework.beans.factory.config.BeanDefinitionVisitor}：http://c.biancheng.net/view/8502.html
 * <p>
 * 角色：
 * 抽象访问者 Visitor
 * 具体访问者 ConcreteVisitor
 * 抽象元素 Element
 * 具体元素 ConcreteElement
 * 对象结构 ObjectStructure
 * <p>
 * 优点：
 * <p>
 * Visitor：https://refactoringguru.cn/design-patterns/visitor
 * Java设计模式：http://c.biancheng.net/view/1397.html
 * 菜鸟模式：https://www.runoob.com/design-pattern/visitor-pattern.html
 *
 * @author ljh
 * created on 2020/9/26 2:51
 */
public class VisitorDemo {

}
