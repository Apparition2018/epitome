package knowledge.design.pattern.gof.structural.adapter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;

/**
 * 适配器模式：使现有不兼容的接口可以一起工作
 * <p>使用场景：
 * <pre>
 * 1 现有接口不兼容
 * 2 复用无法添加到超类的通用功能的多个现有子类 ???
 * 3 只想使用接口中的某些方法 (Default Adapter，使用抽象类实现接口)
 * </pre>
 * 使用实例：
 * <pre>
 * {@code
 *                                              Adaptee             Adapter                     Target
 * Java IO                                      InputStream         InputStreamReader           Reader
 * Java IO                                      OutputStream        OutputStreamWriter          Writer
 * {@link Arrays#asList(Object[])}              E[]                 Arrays#ArrayList            AbstractList
 * {@link Collections#list(Enumeration)}        Enumeration<T>      ArrayList                   ArrayList
 * {@link Collections#enumeration(Collection)}  Collection<T>       实现 Enumeration             Enumeration
 * Spring AOP                                   MethodBeforeAdvice  MethodBeforeAdviceAdapter   AdvisorAdapter
 * Spring MVC                                   HttpRequestHandler  HttpRequestHandlerAdapter   HandlerAdapter}
 * </pre>
 * 角色：
 * <pre>
 * 目标 Target：与 Client 交互的接口
 * 被适配 Adaptee：现有的一些功能类，Client 与其不兼容
 * 适配器 Adapter：实现或继承 Target，类适配器继承 Adaptee，对象适配器持有 Adaptee 的引用
 * </pre>
 * 分类：
 * <pre>
 * 1 {@link ObjectPaymentAdapter 对象适配器}
 * 2 {@link ClassPaymentAdapter 类适配器}
 * 3 {@link DefaultPaymentAdapter 缺省适配器}
 * </pre>
 * 优点：符合单一职责原则、开闭原则
 * <p>扩展：同时实现或继承 Target 和 Adaptee，同时持有 Target 和 Adaptee 的引用，实现双向适配器
 *
 * @author ljh
 * @see <a href="https://refactoringguru.cn/design-patterns/adapter">Adapter</a>
 * @see <a href="https://c.biancheng.net/view/vb1xwi4.html">Java设计模式</a>
 * @see <a href="">设计模式之美：适配器模式：代理、适配器、桥接、装饰，这四个模式有何区别？</a>
 * @since 2020/9/26 2:51
 */
public class AdapterDemo {
}

/** Adaptee */
class OldBankApi {
    public void executeTransaction(String bankCode, double money, long id) {
        System.out.println("银行接口扣款：" + money);
    }
}

/** Target */
interface IPaymentService {
    void pay(Long orderId, BigDecimal amount);

    void onBeforePay();

    void onAfterPay();
}

/**
 * <pre>
 * 1 Default Adapter：通过空实现来实现
 * 2 Object/Class Adapter 的 Target
 * </pre>
 */
abstract class DefaultPaymentAdapter implements IPaymentService {
    @Override
    public void pay(Long orderId, BigDecimal amount) {
    }

    @Override
    public void onBeforePay() {
    }

    @Override
    public void onAfterPay() {
    }
}

/** Object Adapter：通过组合来实现 */
class ObjectPaymentAdapter extends DefaultPaymentAdapter {
    // 组合关系
    private final OldBankApi oldBankApi;

    public ObjectPaymentAdapter(OldBankApi oldBankApi) {
        this.oldBankApi = oldBankApi;
    }

    @Override
    public void pay(Long orderId, BigDecimal amount) {
        oldBankApi.executeTransaction("BK01", amount.doubleValue(), orderId);
    }
}

/** Class Adapter：通过多重继承实现（Java 不支持多重继承，所以通过继承并实现接口实现） */
class ClassPaymentAdapter extends OldBankApi implements IPaymentService {

    @Override
    public void pay(Long orderId, BigDecimal amount) {
        super.executeTransaction("BK01", amount.doubleValue(), orderId);
    }

    @Override
    public void onBeforePay() {
    }

    @Override
    public void onAfterPay() {
    }
}
