package knowledge.design.pattern.gof.behavioral.observer;

import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingListener;
import jar.google.guava.evenbus.EventBusDemo;
import springboot.service.TransactionalService;

import java.io.File;
import java.util.*;

/**
 * 观察者模式：定义了对象间的一种一对多依赖关系，使得每当一个对象改变时，所有依赖于它的对象都会得到通知并自动更新
 * <p>使用场景：关联行为、消息队列、事件
 * <p>使用实例：
 * <pre>
 * 1 JDK：
 *  ① {@link JdkObserverDemo Observer 和 Observable}
 *  ② {@link EventListener} 的所有实现，广泛应用于 Swing 组件
 *  ③ {@link HttpSessionBindingListener} 和 {@link HttpSessionAttributeListener}
 * 2 Spring：
 *  ① {@link SpringApplicationEventDemo}
 *  ② {@link TransactionalService#beforeCommit @TransactionalEventListener}
 * </pre>
 * 角色：
 * <pre>
 * 抽象主题 Subject：持有 Observer 集合的引用，定义 registry()，remove()，notify()
 * 具体主题 ConcreteSubject：实现 notify()
 *      推模型：observer.update(info...)，把 ConcreteObserver 需要的数据传给 ConcreteObserver
 *      拉模型：observer.update(subject)，把 Subject 传给 ConcreteObserver，让 ConcreteObserver 自己从 Subject 获取数据
 * 抽象观察者 Observer：定义 update()
 * 具体观察者 ConcreteObserver
 * </pre>
 * 优点：符合开闭原则
 *
 * @author ljh
 * @see <a href="https://refactoringguru.cn/design-patterns/observer">Observer</a>
 * @see <a href="http://c.biancheng.net/view/1390.html">Java设计模式</a>
 * @see <a href="">设计模式之美：观察者模式（上）：详解各种应用场景下观察者模式的不同实现方式</a>
 * @see <a href="">设计模式之美：观察者模式（下）：如何实现一个异步非阻塞的EventBus框架？{@link EventBusDemo}</a>
 * @since 2020/9/26 2:51
 */
public class ObserverDemo {

    /** <a href="https://refactoringguru.cn/design-patterns/observer/java/example">事件订阅</a> */
    public static void main(String[] args) throws Exception {
        Editor editor = new Editor();
        editor.events.subscribe("open", new LogRecordListener("file.txt"));
        editor.events.subscribe("save", new EmailNotificationListener("admin@example.com"));

        editor.openFile("test.txt");
        editor.saveFile();
    }
}

/** Subject/ConcreteSubject */
class EventManager {
    Map<String, List<MyEventListener>> listeners = new HashMap<>();

    public EventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, MyEventListener listener) {
        List<MyEventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(String eventType, MyEventListener listener) {
        List<MyEventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(String eventType, File file) {
        List<MyEventListener> users = listeners.get(eventType);
        for (MyEventListener listener : users) {
            listener.update(eventType, file);
        }
    }
}

class Editor {
    public EventManager events;
    private File file;

    public Editor() {
        this.events = new EventManager("open", "save");
    }

    public void openFile(String filePath) {
        this.file = new File(filePath);
        events.notify("open", file);
    }

    public void saveFile() throws Exception {
        if (this.file != null) {
            events.notify("save", file);
        } else {
            throw new Exception("Please open a file first.");
        }
    }
}

/** Observer */
interface MyEventListener {
    void update(String eventType, File file);
}

/**
 * ConcreteObserver
 * 收到通知后发送邮件
 */
record EmailNotificationListener(String email) implements MyEventListener {
    @Override
    public void update(String eventType, File file) {
        System.out.println("Email to " + email + ": Someone has performed " + eventType + " operation with the following file: " + file.getName());
    }
}

/**
 * ConcreteObserver
 * 收到通知后在日志中记录一条消息
 */
class LogRecordListener implements MyEventListener {
    private final File log;

    public LogRecordListener(String fileName) {
        this.log = new File(fileName);
    }

    @Override
    public void update(String eventType, File file) {
        System.out.println("Save to log " + log + ": Someone has performed " + eventType + " operation with the following file: " + file.getName());
    }
}
