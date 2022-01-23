package knowledge.suggestions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 建议108：反射结合模板方法模式
 *
 * @author ljh
 * created on 2020/10/10 19:23
 */
// 抽象模板角色类
abstract class Initializer {

    // 模板方法
    public final void dataInitialing() throws InvocationTargetException, IllegalAccessException {
        Method[] methods = getClass().getMethods();
        for (Method m : methods) {
            if (isInitDataMethod(m)) {
                m.invoke(this);
            }
        }
        doSomething();
    }

    // 判断是否为数据初始化方法
    private boolean isInitDataMethod(Method m) {
        return m.getName().startsWith("init")
                && Modifier.isPublic(m.getModifiers())
                && Void.TYPE.equals(m.getReturnType())
                && !m.isVarArgs();
    }

    // 基本方法-具体方法
    private void doSomething() {
        System.out.println("do something ...");
    }

}

// 具体模板角色类
class UserInitializer extends Initializer {
    public void initUser() {
        System.out.println("init user");
    }

    public void initPassword() {
        System.out.println("init password");
    }

    public void initJobs() {
        System.out.println("init jobs");
    }
}