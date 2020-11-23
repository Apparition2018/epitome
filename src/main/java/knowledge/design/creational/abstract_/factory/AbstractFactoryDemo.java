package knowledge.design.creational.abstract_.factory;

import java.util.Objects;

/**
 * 抽象工厂模式：提供一个创建一系列相关或相互依赖对象的接口，而无需指定它们具体的类
 * 抽象工厂模式 | 菜鸟教程：https://www.runoob.com/design-pattern/abstract-factory-pattern.html
 * 应用场景：系统的产品有多于一个的产品族，而系统只消费其中某一族的产品
 * 关键代码：抽象工厂
 * https://www.imooc.com/learn/261
 *
 * @author ljh
 * created on 2019/11/7 15:04
 */
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        PcFactory dellFactory = FactoryProducer.getFactory("DELL");
        Mouse dellMouse = Objects.requireNonNull(dellFactory).createMouse();
        dellMouse.click();

        PcFactory hpFactory = FactoryProducer.getFactory("HP");
        Keyboard hpKeyboard = Objects.requireNonNull(hpFactory).createKeyboard();
        hpKeyboard.press();
    }
}

/**
 * 抽象产品
 */
interface Mouse {
    void click();
}

class DellMouse implements Mouse {

    @Override
    public void click() {
        System.out.println("DellMouse.click");
    }
}

class HpMouse implements Mouse {

    @Override
    public void click() {
        System.out.println("HpMouse.click");
    }
}

/**
 * 抽象产品
 */
interface Keyboard {
    void press();
}

class DellKeyboard implements Keyboard {

    @Override
    public void press() {
        System.out.println("DellKeyboard.press");
    }
}

class HpKeyboard implements Keyboard {

    @Override
    public void press() {
        System.out.println("HpKeyboard.press");
    }
}

/**
 * 抽象工厂
 */
interface PcFactory {
    Mouse createMouse();

    Keyboard createKeyboard();
}

class DellFactory implements PcFactory {

    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }

    @Override
    public Keyboard createKeyboard() {
        return new DellKeyboard();
    }
}

class HpFactory implements PcFactory {

    @Override
    public Mouse createMouse() {
        return new HpMouse();
    }

    @Override
    public Keyboard createKeyboard() {
        return new HpKeyboard();
    }
}

/**
 * 工厂生成器
 */
class FactoryProducer {
    static PcFactory getFactory(String factoryName) {
        if ("DELL".equalsIgnoreCase(factoryName)) {
            return new DellFactory();
        } else if ("HP".equalsIgnoreCase(factoryName)) {
            return new HpFactory();
        }
        return null;
    }
}