package knowledge.建议;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

/**
 * 建议107：动态代理结合装饰器模式
 *
 * @author ljh
 * created on 2020/10/10 19:23
 */
class DecorateSonGoKu implements SonGoKu {
    private SonGoKu sgk;
    private Class<? extends Change> clz;

    DecorateSonGoKu(SonGoKu sgk, Class<? extends Change> clz) {
        this.sgk = sgk;
        this.clz = clz;
    }

    @Override
    public void ability() {
        InvocationHandler handler = (proxy, method, args) -> {
            Object obj = null;
            if (Modifier.isPublic(method.getModifiers())) {
                obj = method.invoke(clz.newInstance(), args);
            }
            sgk.ability();
            return obj;
        };
        Change proxy = (Change) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                clz.getInterfaces(),
                handler);
        proxy.ability();
    }

}

// 抽象构件
interface SonGoKu {
    void ability();
}

// 具体构件
class Monkey implements SonGoKu {
    @Override
    public void ability() {
        System.out.println("爬树");
    }
}

// 装饰
interface Change {
    public void ability();
}

// 具体装饰
class Fly implements Change {

    @Override
    public void ability() {
        System.out.println("飞天");
    }
}

// 具体装饰
class Burrow implements Change {
    @Override
    public void ability() {
        System.out.println("遁地");
    }
}