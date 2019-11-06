package other.设计模式.creational.factory.method;

/**
 * 工厂模式：定义一个接口来创建对象，让子类来决定哪些类需要被实例化，使一个类的实例化推迟到子类
 * <p>
 * 应用场合：
 * 1.有一组类似的对象需要创建
 * 2.在编码时不能预见需要创建那种类的实例
 * 3.系统需要考虑扩展性，不应依赖于产品类实例如何被创建、组合和表达的细节
 * <p>
 * 关键代码：
 * <p>
 * 工厂模式 | 菜鸟教程：https://www.runoob.com/design-pattern/factory-pattern.html
 * <p>
 * https://www.imooc.com/learn/261
 *
 * @author ljh
 * created on 2019/11/1 9:36
 */
public class FactoryMethodDemo {
}

interface Hair {
    void haircut();
}
