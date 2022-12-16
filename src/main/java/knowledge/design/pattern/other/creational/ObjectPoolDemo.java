package knowledge.design.pattern.other.creational;

/**
 * 对象池模式：对象池提供一组预先创建好的对象，需要使用时直接获取，不需要时归还到池中
 * 使用场景：重用创建成本高的对象
 * 使用实例：线程池、连接池等
 * <p>
 * 角色:
 * 可重用对象 Reusable
 * 重用池 ReusablePool：通常设计成 Singleton，持有 Reusable 集合的引用，定义 acquire(), release()
 * <p>
 * Object Pool：https://sourcemaking.com/design_patterns/object_pool
 * Object Pool：https://java-design-patterns.com/patterns/object-pool/
 * Object Pool：https://www.kancloud.cn/sstd521/design/193649
 *
 * @author ljh
 * @since 2022/2/8 11:58
 */
public class ObjectPoolDemo {
}
