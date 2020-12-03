package knowledge.design.behavioral.observer.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * 观察者模式：定义了对象间的一种一对多依赖关系，使得每当一个对象改变状态，则所有依赖于它的对象都会得到通知并被自动更新
 * 1.推模型：主题对象向观察者推送主题的详细信息，不管观察者是否需要，推送的信息通常是主题对象的全部或部分数据。
 * 2.拉模型：主题对象在通知观察者的时候，只传递少量信息。如果观察者需要更具体的信息，由观察者主动到主题对象中获取，相当于是观察者从主题对象中拉数据。
 * -    一般这种模型的实现中，会把主题对象自身传递给观察者，这样在观察者需要获取数据的时候，就可以通过这个引用来获取了。
 * 应用场合：主题的状态发生改变，所有观察者对象都将得到通知
 * 使用场景：Spring 事件驱动模型
 * 关键代码：主题有一个存放观察者的 List
 * 优点：主题和观察者之间是松耦合的
 * 缺点：
 * 1.如果过观察者很多，通知所有观察者将花费很多时间
 * 2.如果主题和观察者之间有循环依赖，会触发循环调用，导致系统崩溃
 * <p>
 * 抽象主题角色 Subject
 * 具体主题角色 ConcreteSubject
 * 抽象观察者角色 Observer
 * 具体观察者角色 ConcreteObserver
 * <p>
 * 《JAVA与模式》之观察者模式：http://www.cnblogs.com/java-my-life/archive/2012/05/16/2502279.html
 * 观察者模式 | 菜鸟教程：https://www.runoob.com/design-pattern/observer-pattern.html
 * https://zhuanlan.zhihu.com/p/85975439
 * https://zhuanlan.zhihu.com/p/51357583
 * https://www.zhihu.com/question/23486749
 *
 * @author ljh
 * created on 2020/9/26 2:51
 */
public class ObserverDemo {

    /**
     * 拉模型观察者模型
     * http://www.cnblogs.com/java-my-life/archive/2012/05/16/2502279.html
     */
    private static class PullObserver {
        public static void main(String[] args) {
            // 创建主题对象
            ConcreteSubject subject = new ConcreteSubject();
            // 创建观察者对象
            Observer observer = new ConcreteObserver();
            // 将观察者对象登记到主题对象上
            subject.attach(observer);
            // 改变主题对象的状态
            subject.change("new state");
        }

        /**
         * 抽象主题角色类
         */
        private static abstract class Subject {

            /**
             * 用来保存注册的观察者对象
             */
            private List<Observer> list = new ArrayList<>();

            /**
             * 注册观察者对象
             */
            public void attach(Observer observer) {
                list.add(observer);
                System.out.println("Attached an observer");
            }

            /**
             * 删除观察者对象
             */
            public void detach(Observer observer) {
                list.remove(observer);
            }

            /**
             * 通知所有注册的观察者对象
             */
            void notifyObservers() {
                for (Observer observer : list) {
                    observer.update(this);
                }
            }

        }

        /**
         * 具体主题角色类
         */
        private static class ConcreteSubject extends Subject {
            private String state;

            public String getState() {
                return state;
            }

            public void change(String newState) {
                state = newState;
                System.out.println("subject state: " + state);
                // 状态发生改变，通知各个观察者
                this.notifyObservers();
            }
        }

        /**
         * 推模型 抽象观察者角色类
         */
        interface Observer {
            /**
             * 更新接口
             */
            void update(Subject subject);
        }

        /**
         * 推模型 具体观察者角色类
         */
        private static class ConcreteObserver implements Observer {
            /**
             * 观察者的状态
             */
            private String observerState;

            @Override
            public void update(Subject subject) {
                // 更新观察者的状态，使其与目标的状态保持一致
                observerState = ((ConcreteSubject) subject).getState();
                System.out.println("observer state: " + observerState);
            }
        }
    }

    /**
     * JAVA 对观察者模式的支持
     * Java 提供了一个接口 Observer 和 一个类 Observable 对观察者模式的支持
     */
    private static class JdkObserver {
        public static void main(String[] args) {
            // 创建被观察者对象
            Watched watched = new Watched();
            // 创建观察者对象，并将被观察者对象登记
            java.util.Observer watcher = new Watcher(watched);
            // 给被观察者状态赋值
            watched.setData("start");
            watched.setData("run");
            watched.setData("stop");
        }

        public static class Watched extends Observable {
            private String data = "";

            public String getData() {
                return data;
            }

            public void setData(String data) {
                if (!this.data.equals(data)) {
                    this.data = data;
                    setChanged();
                }
                notifyObservers();
            }
        }

        public static class Watcher implements java.util.Observer {

            public Watcher(Observable o) {
                o.addObserver(this);
            }

            @Override
            public void update(Observable o, Object arg) {
                System.out.println("observer state: " + ((Watched) o).getData());
            }
        }
    }
}
