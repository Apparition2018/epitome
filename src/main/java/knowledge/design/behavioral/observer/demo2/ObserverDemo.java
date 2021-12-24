package knowledge.design.behavioral.observer.demo2;

import java.util.ArrayList;
import java.util.List;

/**
 * 多种观察者
 *
 * @author ljh
 * created on 2020/9/26 2:51
 */
public class ObserverDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();

        new HexObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }

    static class Subject {

        private final List<Observer> observers = new ArrayList<>();
        private int state;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
            notifyAllObservers();
        }

        public void attach(Observer observer) {
            observers.add(observer);
        }

        private void notifyAllObservers() {
            for (Observer observer : observers) {
                observer.update();
            }
        }
    }

    private static abstract class Observer {
        protected Subject subject;

        public abstract void update();
    }

    static class BinaryObserver extends Observer {

        BinaryObserver(Subject subject) {
            this.subject = subject;
            this.subject.attach(this);
        }

        @Override
        public void update() {
            System.out.println("Binary String: " + Integer.toBinaryString(subject.getState()));
        }
    }

    static class OctalObserver extends Observer {

        OctalObserver(Subject subject) {
            this.subject = subject;
            this.subject.attach(this);
        }

        @Override
        public void update() {
            System.out.println("Octal String: " + Integer.toOctalString(subject.getState()));
        }
    }

    static class HexObserver extends Observer {

        HexObserver(Subject subject) {
            this.subject = subject;
            this.subject.attach(this);
        }

        @Override
        public void update() {
            System.out.println("Hex String: " + Integer.toHexString(subject.getState()).toUpperCase());
        }
    }
}
