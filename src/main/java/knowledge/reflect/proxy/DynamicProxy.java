package knowledge.reflect.proxy;

import knowledge.reflect.proxy.domain.Man;
import knowledge.reflect.proxy.domain.People;
import l.demo.Demo;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * DynamicProxy    动态代理
 * java.lang.reflect.Proxy: 创建动态代理类和实例的静态方法，还是由这些方法创建的所有动态代理类的超类
 * java.lang.reflect.InvocationHandler: 代理实例的调用处理程序实现的接口
 * <p>
 * Java 动态代理作用是什么？ - 知乎：https://www.zhihu.com/question/20794107
 *
 * @author ljh
 * created on 2020/11/9 17:51
 */
public class DynamicProxy extends Demo {

    @Test
    public void testDynamicProxy1() {
        StopWatchProxyHandler timeIntervalHandler = new StopWatchProxyHandler();
        People proxy = (People) timeIntervalHandler.newProxy(new Man());
        proxy.work();
        proxy.sleep();
    }

    @Test
    public void testDynamicProxy2() {
        Man man = new Man();
        People peopleProxy = (People) Proxy.newProxyInstance(
                man.getClass().getClassLoader(),
                man.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    long start = System.currentTimeMillis();
                    Object result = method.invoke(man, args);
                    long end = System.currentTimeMillis();
                    p(method.getName() + " execute spend [" + (end - start) + "]ms.");
                    return result;
                });
        peopleProxy.work();
        peopleProxy.sleep();
    }

    private static class StopWatchProxyHandler implements InvocationHandler {

        /**
         * 目标对象
         */
        private Object target;

        /**
         * 动态地为目标对象创建代理对象，
         * 代理对象也会与被代理对象实现相同的接口。
         *
         * @param target 目标对象
         * @return 返回目标对象的代理对象
         */
        public Object newProxy(Object target) {
            this.target = target;
            return Proxy.newProxyInstance(
                    target.getClass().getClassLoader(), // 目标对象的加载器
                    target.getClass().getInterfaces(),  // 目标对象实现的接口
                    this); // 代理对象
        }

        /**
         * 在执行目标对象方法时，会默认调用此方法
         *
         * @param proxy  代理对象
         * @param method 代表方法对象
         * @param args   用于接收目标方法中的实际参数的值
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
