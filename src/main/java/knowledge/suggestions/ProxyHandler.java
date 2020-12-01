package knowledge.suggestions;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 建议106：动态代理
 *
 * @author ljh
 * created on 2020/10/10 19:23
 */
class ProxyHandler implements InvocationHandler {

    // 被代理对象
    private Object target;

    /**
     * 动态地为目标对象创建代理对象，创建的这个代理对象也会被代理对象实现相同的接口
     * @param target 被代理对象
     * @return 代理对象
     */
    public Object newProxy(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }

    /**
     * 执行被代理对象的方法时，会默认调用此方法
     * @param proxy 代理对象
     * @param method 代表方法对象（通过此地想执行目标方法）
     * @param args 被代理对象中的实际参数的值
     * @return 被代理对象方法的返回值
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("预处理...");
        Object result = method.invoke(target, args);
        System.out.println("后处理...");
        return result;
    }
}

interface TeamService {
    int hello();
    int goodbye();
}

class TeamServiceImpl implements TeamService {

    @Override
    public int hello() {
        System.out.println("hello");
        return 1;
    }

    @Override
    public int goodbye() {
        System.out.println("goodbye");
        return 1;
    }
}