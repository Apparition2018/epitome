package knowledge.design.pattern.gof.structural.bridge;

import java.sql.Driver;
import java.util.logging.Formatter;
import java.util.logging.Handler;

/**
 * 桥接模式：将类拆分为抽象部分和实现部分，使得二者可以独立地变化
 * <p>使用场景：在几个独立的维度上扩展类
 * <p>使用实例：
 * <pre>
 * 1 sql：{@link Driver}
 * 2 logging: {@link Handler} 和 {@link Formatter}
 * </pre>
 * 角色：
 * <pre>
 * 抽象部分：接收 Implementor 的引用
 *  1 抽象 Abstraction：定义与 Client 交互的高层操作
 *  2 精确抽象 RefinedAbstraction
 * 实现部分：
 *  1 实现 Implementor：定义底层操作
 *  2 具体实现 ConcreteImplementor
 * </pre>
 * 优点：符合单一职责原则、开闭原则、依赖倒置原则
 * <p>扩展：
 * <pre>
 * 1 与 Adapter 联合使用，解决 Implementor 与现有类接口不兼容的问题
 * 2 与 Abstract Factory 联合使用，使用 Factory 创建 Implementor 需要的不同 Abstraction
 * 3 与 Builder 联合使用，Director 充当 Abstraction，Builder 充当 Implementor
 * </pre>
 * 参考：
 * <pre>
 * <a href="https://refactoringguru.cn/design-patterns/bridge">Bridge</a>
 * <a href="http://c.biancheng.net/view/1364.html">Java设计模式</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/11/23 19:38
 */
public class BridgeDemo {

    /**
     * <a href="https://refactoringguru.cn/design-patterns/bridge/java/example">设备和远程控制之间的桥接</a>
     * <p>远程控制不同类型的设备，远程控制包括基础控制和高级控制，设备包括收音机和电视
     * <pre>
     * 1 抽象-远程控制
     * 2 实现-设备
     * </pre>
     */
    public static void main(String[] args) {
        remoteDevice(new Tv());
        remoteDevice(new Radio());
    }

    private static void remoteDevice(Device device) {
        System.out.println("Tests with basic remote.");
        BasicRemote basicRemote = new BasicRemote(device);
        basicRemote.power();
        device.printStatus();

        System.out.println("Tests with advanced remote.");
        AdvancedRemote advancedRemote = new AdvancedRemote(device);
        advancedRemote.power();
        advancedRemote.mute();
        device.printStatus();
    }

    /**
     * Implementor
     */
    interface Device {
        boolean isEnabled();

        void enable();

        void disable();

        int getVolume();

        void setVolume(int percent);

        int getChannel();

        void setChannel(int channel);

        void printStatus();
    }

    /**
     * ConcreteImplementor
     */
    private static class Radio implements Device {
        private boolean on = false;
        private int volume = 30;
        private int channel = 1;

        @Override
        public boolean isEnabled() {
            return on;
        }

        @Override
        public void enable() {
            on = true;
        }

        @Override
        public void disable() {
            on = false;
        }

        @Override
        public int getVolume() {
            return volume;
        }

        @Override
        public void setVolume(int volume) {
            if (volume > 100) this.volume = 100;
            else this.volume = Math.max(volume, 0);
        }

        @Override
        public int getChannel() {
            return channel;
        }

        @Override
        public void setChannel(int channel) {
            this.channel = channel;
        }

        @Override
        public void printStatus() {
            System.out.println("------------------------------------");
            System.out.println("| I'm radio.");
            System.out.println("| I'm " + (on ? "enabled" : "disabled"));
            System.out.println("| Current volume is " + volume + "%");
            System.out.println("| Current channel is " + channel);
            System.out.println("------------------------------------\n");
        }
    }

    /**
     * ConcreteImplementor
     */
    private static class Tv implements Device {
        private boolean on = false;
        private int volume = 30;
        private int channel = 1;

        @Override
        public boolean isEnabled() {
            return on;
        }

        @Override
        public void enable() {
            on = true;
        }

        @Override
        public void disable() {
            on = false;
        }

        @Override
        public int getVolume() {
            return volume;
        }

        @Override
        public void setVolume(int volume) {
            if (volume > 100) this.volume = 100;
            else this.volume = Math.max(volume, 0);
        }

        @Override
        public int getChannel() {
            return channel;
        }

        @Override
        public void setChannel(int channel) {
            this.channel = channel;
        }

        @Override
        public void printStatus() {
            System.out.println("------------------------------------");
            System.out.println("| I'm TV set.");
            System.out.println("| I'm " + (on ? "enabled" : "disabled"));
            System.out.println("| Current volume is " + volume + "%");
            System.out.println("| Current channel is " + channel);
            System.out.println("------------------------------------\n");
        }
    }

    /**
     * Abstraction
     */
    interface Remote {
        void power();

        void volumeDown();

        void volumeUp();

        void channelDown();

        void channelUp();
    }

    /**
     * RefinedAbstraction
     */
    private static class BasicRemote implements Remote {
        protected Device device;

        public BasicRemote() {
        }

        public BasicRemote(Device device) {
            this.device = device;
        }

        @Override
        public void power() {
            System.out.println("Remote: power toggle");
            if (device.isEnabled()) {
                device.disable();
            } else {
                device.enable();
            }
        }

        @Override
        public void volumeDown() {
            System.out.println("Remote: volume down");
            device.setVolume(device.getVolume() - 10);
        }

        @Override
        public void volumeUp() {
            System.out.println("Remote: volume up");
            device.setVolume(device.getVolume() + 10);
        }

        @Override
        public void channelDown() {
            System.out.println("Remote: channel down");
            device.setChannel(device.getChannel() - 1);
        }

        @Override
        public void channelUp() {
            System.out.println("Remote: channel up");
            device.setChannel(device.getChannel() + 1);
        }
    }

    /**
     * RefinedAbstraction
     */
    private static class AdvancedRemote extends BasicRemote {

        public AdvancedRemote(Device device) {
            super.device = device;
        }

        public void mute() {
            System.out.println("Remote: mute");
            device.setVolume(0);
        }
    }
}
