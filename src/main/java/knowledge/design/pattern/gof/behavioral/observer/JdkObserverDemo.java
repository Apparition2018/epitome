package knowledge.design.pattern.gof.behavioral.observer;

import lombok.Getter;

import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

/**
 * Java 提供了一个 Observer 接口和 一个 Observable 类对观察者模式的支持
 *
 * @author ljh
 * @since 2026/4/27 13:14
 */
public class JdkObserverDemo {

    public static void main(String[] args) {
        Watched watched = new Watched();
        watched.addObserver(new Watcher());
        watched.setData("start");
        watched.setData("run");
        watched.setData("stop");
    }
}

/** ConcreteSubject */
@Getter
class Watched extends Observable {
    private String data;

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

/** ConcreteObserver */
class Watcher implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("observer state: " + ((Watched) o).getData());
    }
}
