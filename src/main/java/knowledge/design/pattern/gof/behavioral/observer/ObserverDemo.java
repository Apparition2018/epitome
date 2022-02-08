package knowledge.design.pattern.gof.behavioral.observer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

/**
 * 观察者模式：定义了对象间的一种一对多依赖关系，使得每当一个对象改变时，所有依赖于它的对象都会得到通知并自动更新
 * 使用场景：关联行为、消息队列、事件
 * 使用实例：
 * 1.{@link java.util.Observer} 和 {@link java.util.Observable}
 * 2.{@link java.util.EventListener} 的所有实现，广泛应用于 Swing 组件
 * 3.{@link javax.servlet.http.HttpSessionBindingListener} 和 {@link javax.servlet.http.HttpSessionAttributeListener}
 * <p>
 * 角色：
 * 抽象主题 Subject：持有 Observer 集合的引用，定义 registry()，remove()，notify()
 * 具体主题 ConcreteSubject：实现 notify()
 * -    推模型：observer.update(info...)，把 ConcreteObserver 需要的数据传给 ConcreteObserver
 * -    拉模型：observer.update(subject)，把 Subject 传给 ConcreteObserver，让 ConcreteObserver 自己从 Subject 获取数据
 * 抽象观察者 Observer：定义 update()
 * 具体观察者 ConcreteObserver
 * <p>
 * 优点：符合开闭原则
 * <p>
 * Observer：https://refactoringguru.cn/design-patterns/observer
 * Java设计模式：http://c.biancheng.net/view/1390.html
 * 设计模式之美：观察者模式（上）：详解各种应用场景下观察者模式的不同实现方式
 * 设计模式之美：观察者模式（下）：如何实现一个异步非阻塞的EventBus框架？{@link jar.google.guava.EventBusDemo}
 *
 * @author ljh
 * created on 2020/9/26 2:51
 */
public class ObserverDemo {

    /**
     * 多人联机对战游戏
     */
    @Test
    public void testObserver() {
        AllyControlCenter acc = new AllyControlCenter("天龙八部");
        Player qf = new Player("乔峰");
        acc.join(qf, new Player("虚竹"), new Player("段誉"));
        qf.beAttacked(acc);
    }

    /**
     * Subject
     */
    @Getter
    @Setter
    static abstract class IAllyControlCenter {
        protected String allyName;
        private String beAttackName;
        protected List<IPlayer> players = new ArrayList<>();

        protected void join(IPlayer... iPlayers) {
            for (IPlayer player : iPlayers) {
                System.out.println(player.getName() + "加入" + this.allyName + "战队！");
                players.add(player);
            }
        }

        private void quit(IPlayer obs) {
            System.out.println(obs.getName() + "退出" + this.allyName + "战队！");
            players.remove(obs);
        }

        protected abstract void beAttackNotifyByPush(String beAttackName);

        protected abstract void beAttackedNotifyByPull(String beAttackName);
    }

    /**
     * ConcreteSubject
     */
    @Data
    static class AllyControlCenter extends IAllyControlCenter {

        public AllyControlCenter(String allyName) {
            System.out.println(allyName + "战队组件成功！");
            System.out.println("------------------------------");
            this.allyName = allyName;
        }

        /**
         * 推模型
         */
        @Override
        public void beAttackNotifyByPush(String beAttackName) {
            System.out.println(this.allyName + "战队紧急通知，盟友" + beAttackName + "遭受敌人攻击！");
            for (IPlayer player : players) {
                if (!beAttackName.equalsIgnoreCase(player.getName())) {
                    player.helpByPush(beAttackName);
                }
            }
        }

        /**
         * 拉模型
         */
        @Override
        public void beAttackedNotifyByPull(String beAttackName) {
            System.out.println(this.allyName + "战队紧急通知，盟友" + beAttackName + "遭受敌人攻击！");
            this.setBeAttackName(beAttackName);
            for (IPlayer player : players) {
                if (!beAttackName.equalsIgnoreCase(player.getName())) {
                    player.helpByPull(this);
                }
            }
        }
    }

    /**
     * Observer
     */
    interface IPlayer {
        String getName();

        void setName(String name);

        void helpByPush(String beAttackName);

        void helpByPull(IAllyControlCenter acc);

        void beAttacked(IAllyControlCenter acc);
    }

    /**
     * ConcreteObserver
     */
    @AllArgsConstructor
    @Getter
    @Setter
    static class Player implements IPlayer {
        private String name;

        /**
         * 推模型
         */
        @Override
        public void helpByPush(String beAttackName) {
            System.out.println(beAttackName + "坚持住，" + name + "来救你！");
        }

        /**
         * 拉模型
         */
        @Override
        public void helpByPull(IAllyControlCenter acc) {
            System.out.println(acc.getBeAttackName() + "坚持住，" + name + "来救你！");
        }

        @Override
        public void beAttacked(IAllyControlCenter acc) {
            System.out.println(name + "被攻击！");

            System.out.println("---------- Push 通知 ----------");
            acc.beAttackNotifyByPush(name);

            System.out.println("---------- Pull 通知 ----------");
            acc.beAttackedNotifyByPull(name);
        }
    }

    /**
     * Java 提供了一个 Observer 接口和 一个 Observable 类对观察者模式的支持
     */
    static class JdkObserverDemo {

        @Test
        public void test() {
            Watched watched = new Watched();
            watched.addObserver(new Watcher());
            watched.setData("start");
            watched.setData("run");
            watched.setData("stop");
        }

        /**
         * ConcreteSubject
         */
        static class Watched extends Observable {
            private String data;

            public String getData() {
                return data;
            }

            public void setData(String data) {
                if (!Objects.equals(this.data, data)) {
                    this.data = data;
                    // 将 Observable 对象的 changed 标记为 true
                    setChanged();
                }
                // 当 Observable 对象的 changed 标记为 true 才通知 Observer
                notifyObservers();
            }
        }

        /**
         * ConcreteObserver
         */
        static class Watcher implements java.util.Observer {
            @Override
            public void update(Observable o, Object arg) {
                System.out.println("observer state: " + ((Watched) o).getData());
            }
        }
    }
}
