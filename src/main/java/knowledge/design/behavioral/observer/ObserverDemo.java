package knowledge.design.behavioral.observer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式：定义了对象间的一种一对多依赖关系，使得每当一个对象改变时，所有依赖于它的对象都会得到通知并自动更新
 * 使用场景：
 * 1.事件多级触发场景
 * 2.跨系统的消息交换场景，如消息队列、事件总线的处理机制
 * 使用实例：
 * 1.{@link java.util.Observer} 和 {@link java.util.Observable}
 * 2.{@link java.util.EventListener} 的所有实现，广泛应用于 Swing 组件
 * 3.{@link javax.servlet.http.HttpSessionBindingListener} 和 {@link javax.servlet.http.HttpSessionAttributeListener}
 * <p>
 * 角色:
 * 抽象主题 Subject：定义了一个 Observer 集合，add()，remove()，notify()
 * 具体主题 ConcreteSubject：实现 notify() 通知 Observer
 * 抽象观察者 Observer：定义 update()
 * 具体观察者 ConcreteObserver
 * <p>
 * 优点：符合开闭原则
 * 两种模型：TODO-LJH: 2022/1/26
 * 1.推模型：Subject 向 Observer 推送 Subject 的全部或部分信息，不管 Observer 是否需要
 * 2.拉模型：Subject 在通知 Observer 时，只传递少量信息；Observer 如需更具体的信息，主动到 Subject 中获取
 * <p>
 * ----------------------------------------
 * 发布订阅模式 (Publish Subscribe)
 * 角色：
 * 发布者 Publisher
 * 经纪人 Broker
 * 订阅者 Subscriber
 * <p>
 * Observer：https://refactoringguru.cn/design-patterns/observer
 * Java设计模式：http://c.biancheng.net/view/1390.html
 * 菜鸟教程：https://www.runoob.com/design-pattern/observer-pattern.html
 * JAVA与模式：https://www.cnblogs.com/java-my-life/archive/2012/05/16/2502279.html
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
    abstract static class IAllyControlCenter {
        @Getter
        @Setter
        protected String allyName;
        protected List<IPlayer> players = new ArrayList<>();

        void join(IPlayer... iPlayers) {
            for (IPlayer player : iPlayers) {
                System.out.println(player.getName() + "加入" + this.allyName + "战队！");
                players.add(player);
            }
        }

        void quit(IPlayer obs) {
            System.out.println(obs.getName() + "退出" + this.allyName + "战队！");
            players.remove(obs);
        }

        abstract void notifyPlayer(String name);
    }

    /**
     * ConcreteSubject
     */
    static class AllyControlCenter extends IAllyControlCenter {

        public AllyControlCenter(String allyName) {
            System.out.println(allyName + "战队组件成功！");
            System.out.println("------------------------------");
            this.allyName = allyName;
        }

        @Override
        void notifyPlayer(@NonNull String name) {
            System.out.println(this.allyName + "战队紧急通知，盟友" + name + "遭受敌人攻击！");
            for (IPlayer player : players) {
                if (!name.equalsIgnoreCase(player.getName())) player.help();
            }
        }
    }

    /**
     * Observer
     */
    interface IPlayer {
        String getName();

        void setName(String name);

        void help();

        void beAttacked(AllyControlCenter acc);
    }

    /**
     * ConcreteObserver
     */
    @AllArgsConstructor
    @Getter
    @Setter
    static class Player implements IPlayer {
        private String name;

        @Override
        public void help() {
            System.out.println("坚持住，" + name + "来救你！");
        }

        @Override
        public void beAttacked(AllyControlCenter acc) {
            System.out.println(name + "被攻击！");
            acc.notifyPlayer(name);
        }
    }
}
