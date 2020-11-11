package knowledge.reflect.proxy;

import knowledge.reflect.proxy.domain.Man;
import knowledge.reflect.proxy.domain.People;
import l.demo.Demo;

/**
 * StaticProxy  静态代理
 * 静态代理：自己写代理类，代理类与被代理对象实现共同的接口
 * 优点：可以很方便的为被代理对象实现功能扩展
 * 缺点：如果有很多接口的实现类都需要这样的功能扩展，那就需要创建很多个代理类
 *
 * @author ljh
 * created on 2020/11/10 13:53
 */
public class StaticProxy extends Demo {

    public static void main(String[] args) {
        People proxy = new StopWatchProxy(new Man());
        proxy.work();
        proxy.sleep();
    }

    /**
     * 代理类
     * 1.与被代理类实现相同的接口
     * 2.代理被代理对象实现功能的扩展
     */
    private static class StopWatchProxy implements People {

        private Man man;

        public StopWatchProxy(Man man) {
            this.man = man;
        }

        @Override
        public void work() {
            long start = System.currentTimeMillis();
            p("work start.time = " + start);
            man.work();
            long end = System.currentTimeMillis();
            p("work end.time = " + end);
            p("work execute spend [" + (end - start) + "]ms.\n");
        }

        @Override
        public void sleep() {
            long start = System.currentTimeMillis();
            p("sleep start.time = " + start);
            try {
                man.sleep();
            } catch (Exception e) {
                p(e.getMessage());
            }
            long end = System.currentTimeMillis();
            p("sleep end.time = " + end);
            p("sleep execute spend [" + (end - start) + "]ms.\n");
        }
    }
}
