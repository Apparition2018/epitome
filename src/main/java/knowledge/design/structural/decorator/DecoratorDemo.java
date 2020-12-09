package knowledge.design.structural.decorator;

/**
 * 装饰器模式：允许向一个现有的对象添加新的功能，同时又不改变其结构，符合开闭原则（对扩展开放，对修改关闭）
 * 主要解决：我们为了扩展一个类经常使用继承方式实现，随着扩展功能的增多，子类会变得很多
 * 使用场景：
 * 1.IO：
 * -    抽象构建角色：InputStream
 * -    具体构建角色：FileInputStream，ByteArrayInputStream，PipedInputStream，StringBufferInputStream
 * -    抽象装饰角色：FilterInputStream
 * -    具体装饰角色：BufferedInputStream，DataInputStream
 * 关键代码：装饰类继承和引用 Component，重写父类方法
 * 优点：
 * 1.装饰类和被装饰类可以独立发展，不会相互耦合，装饰模式是继承的一个替代模式，装饰模式可以动态扩展或删除实现类的功能。
 * 2.通过对不同的装饰类排列组合，可以创造出很多不同的组合。
 * 缺点：多层装饰比较复杂；比继承产生更多的对象
 * 角色：
 * 抽象构建角色 Component
 * 具体构建角色 ConcreteComponent
 * 抽象装饰角色 Decorator
 * 具体装饰角色 ConcreteDecorator
 * <p>
 * 《JAVA与模式》之装饰模式：http://www.cnblogs.com/java-my-life/archive/2012/04/20/2455726.html
 * 装饰器模式 | 菜鸟教程：https://www.runoob.com/design-pattern/decorator-pattern.html
 * 装饰者模式 带你重回传奇世界：https://blog.csdn.net/lmj623565791/article/details/24269409
 *
 * @author Arsenal
 * created on 2020/9/26 2:51
 */
public class DecoratorDemo {

    /**
     * 案例：
     * 1.武器（攻击力50），盔甲（防御力50），饰品（攻击力25，防御力25）
     * 2.红宝石（攻击力30），蓝宝石（防御力30），黄宝石（攻击力20，防御力20）
     * 计算镶嵌了宝石的装备的攻击力和防御力
     */
    public static void main(String[] args) {
        Equip equip = new RedGem(new BlueGem(new YellowGem(new Weapon())));
        System.out.println("攻击力：" + equip.calAttack());
        System.out.println("防御力：" + equip.calDefence());
        System.out.println("描述：" + equip.desc());
    }

    /**
     * 抽象构建角色
     */
    interface Equip {
        int calAttack();

        int calDefence();

        String desc();
    }

    /**
     * 具体构建角色
     */
    private static class Weapon implements Equip {
        @Override
        public int calAttack() {
            return 50;
        }

        @Override
        public int calDefence() {
            return 0;
        }

        @Override
        public String desc() {
            return "武器";
        }
    }

    /**
     * 具体构建角色
     */
    private static class Armor implements Equip {
        @Override
        public int calAttack() {
            return 0;
        }

        @Override
        public int calDefence() {
            return 50;
        }

        @Override
        public String desc() {
            return "盔甲";
        }
    }

    /**
     * 具体构建角色
     */
    private static class Decoration implements Equip {
        @Override
        public int calAttack() {
            return 25;
        }

        @Override
        public int calDefence() {
            return 25;
        }

        @Override
        public String desc() {
            return "饰品";
        }
    }

    /**
     * 抽象装饰角色
     */
    interface GemDecorator extends Equip {
    }

    /**
     * 具体装饰角色
     */
    private static class RedGem implements GemDecorator {
        private Equip equip;


        public RedGem(Equip equip) {
            this.equip = equip;
        }

        @Override
        public int calAttack() {
            return equip.calAttack() + 30;
        }

        @Override
        public int calDefence() {
            return equip.calDefence();
        }

        @Override
        public String desc() {
            return equip.desc() + " 红宝石";
        }
    }

    /**
     * 具体装饰角色
     */
    private static class BlueGem implements GemDecorator {
        private Equip equip;


        public BlueGem(Equip equip) {
            this.equip = equip;
        }

        @Override
        public int calAttack() {
            return equip.calAttack();
        }

        @Override
        public int calDefence() {
            return equip.calDefence() + 30;
        }

        @Override
        public String desc() {
            return equip.desc() + " 蓝宝石";
        }
    }

    /**
     * 具体装饰角色
     */
    private static class YellowGem implements GemDecorator {
        private Equip equip;


        public YellowGem(Equip equip) {
            this.equip = equip;
        }

        @Override
        public int calAttack() {
            return equip.calAttack() + 20;
        }

        @Override
        public int calDefence() {
            return equip.calDefence() + 20;
        }

        @Override
        public String desc() {
            return equip.desc() + " 黄宝石";
        }
    }

}
