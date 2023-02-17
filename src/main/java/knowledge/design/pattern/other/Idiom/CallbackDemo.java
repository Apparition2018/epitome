package knowledge.design.pattern.other.Idiom;

import knowledge.design.pattern.gof.behavioral.template.method.TemplateMethodDemo;

/**
 * <a href="https://java-design-patterns.com/patterns/callback/">Callback</a>   回调
 * <pre>
 * 1 同步回调：在函数返回之前执行回调函数，像模板方法模式
 *   共同目的：复用和扩展
 *   同步回调基于组合关系，把一个对象传递给另一个对象
 *   模板方法基于继承关系，子类重写父类方法
 *   设计模式之美：模板模式（下）：模板模式与Callback回调函数有何区别和联系？
 * 2 异步回调：在函数返回之后执行回调函数，像只有一个观察者的观察者模式
 * </pre>
 *
 * @author ljh
 * @since 2022/1/25 22:30
 */
public class CallbackDemo {

    /**
     * {@link TemplateMethodDemo}
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.play(() -> System.out.println("Football Game Init"),
                () -> System.out.println("Football Game Begin"),
                () -> System.out.println("Football Game Finished"));
    }

    private static class Game {
        void init(Runnable initCallback) {
            initCallback.run();
        }

        void begin(Runnable beginCallback) {
            beginCallback.run();
        }

        void finished(Runnable finishedCallback) {
            finishedCallback.run();
        }

        public final void play(Runnable initCallback,
                               Runnable beginCallback,
                               Runnable finishedCallback) {
            init(initCallback);
            begin(beginCallback);
            finished(finishedCallback);
        }
    }
}
