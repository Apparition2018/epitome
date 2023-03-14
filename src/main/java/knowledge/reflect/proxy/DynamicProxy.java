package knowledge.reflect.proxy;

import knowledge.reflect.proxy.domain.Man;
import knowledge.reflect.proxy.domain.People;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static l.demo.Demo.p;

/**
 * DynamicProxy     动态代理
 * <pre>
 * java.lang.reflect.Proxy              创建动态代理类和实例的静态方法，还是由这些方法创建的所有动态代理类的超类
 * java.lang.reflect.InvocationHandler  代理实例的调用处理程序实现的接口
 * </pre>
 * 参考：<a href="https://www.zhihu.com/question/20794107">Java 动态代理作用是什么？</a>
 *
 * @author ljh
 * @since 2020/11/9 17:51
 */
public class DynamicProxy {

    @Test
    public void testDynamicProxy1() {
        StopWatchProxyHandler<People> timeIntervalHandler = new StopWatchProxyHandler<>();
        People proxy = timeIntervalHandler.getProxy(new Man());
        proxy.work();
        proxy.sleep();
    }

    @Test
    public void testDynamicProxy2() {
        Man man = new Man();
        People peopleProxy = (People) Proxy.newProxyInstance(
                // 目标对象的加载器
                man.getClass().getClassLoader(),
                // 目标对象实现的接口
                man.getClass().getInterfaces(),
                // 代理对象
                (proxy, method, args) -> {
                    long start = System.currentTimeMillis();
                    Object result = null;
                    try {
                        result = method.invoke(man, args);
                    } catch (Exception e) {
                        p(e.getCause().getMessage());
                    }
                    long end = System.currentTimeMillis();
                    p(method.getName() + " execute spend [" + (end - start) + "]ms.");
                    return result;
                });
        peopleProxy.work();
        peopleProxy.sleep();
    }

    private static class StopWatchProxyHandler<T> implements InvocationHandler {

        /**
         * 目标对象
         */
        private T target;

        /**
         * 获取代理对象
         * 代理对象会实现被代理对象相同的接口
         *
         * @param target 目标对象
         * @return 返回目标对象的代理对象
         */
        @SuppressWarnings("unchecked")
        public T getProxy(T target) {
            this.target = target;
            return (T) Proxy.newProxyInstance(
                    // 目标对象的加载器
                    target.getClass().getClassLoader(),
                    // 目标对象实现的接口
                    target.getClass().getInterfaces(),
                    // 代理对象
                    this);
        }

        /**
         * @param proxy  代理对象，一般情况下，invoke() 中都不使用该对象
         * @param method 正在被调用的方法
         * @param args   调用方法时的参数
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) {
            String methodName = method.getName();
            long start = System.currentTimeMillis();
            p(methodName + " start.time = " + start);
            Object result = null;
            try {
                result = method.invoke(target, args);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                p(e.getCause().getMessage());
            }
            long end = System.currentTimeMillis();
            p(methodName + " end.time = " + end);
            p(methodName + " execute spend [" + (end - start) + "]ms.\n");
            return result;
        }
    }
}
