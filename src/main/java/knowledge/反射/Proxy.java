package knowledge.反射;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Proxy    代理
 * java.lang.reflect.Proxy: 创建动态代理类和实例的静态方法，还是由这些方法创建的所有动态代理类的超类
 * java.lang.reflect.InvocationHandler: 代理实例的调用处理程序实现的接口
 * <p>
 * Java 动态代理作用是什么？ - 知乎：https://www.zhihu.com/question/20794107
 *
 * @author ljh
 * created on 2020/11/9 17:51
 */
public class Proxy {
    
    private static class TimeIntervalInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }
    }
}
