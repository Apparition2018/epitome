package knowledge.design.structural.facade;

import static l.demo.Demo.p;

/**
 * 外观模式：又称门面模式，为子系统中的一组接口提供一个一致的界面，外观模式通过提供一个高层接口，隔离了外部系统与子系统间复杂的交互过程，使得复杂系统的子系统更易使用
 * 使用场景：日志
 * 优点：简化调用，降低客户端和系统的耦合，更好地划分访问层次，符合迪米特法则
 * 缺点：不符合开闭原则
 * 角色：
 * 门面角色 Facade
 * 子系统角色 SubSystem
 * <p>
 * 外观模式 | 菜鸟教程：https://www.runoob.com/design-pattern/facade-pattern.html
 *
 * @author Arsenal
 * created on 2020/9/26 2:51
 */
public class FacadeDemo {

    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.startup();
        computer.shutdown();
    }

    private static class Computer {
        private CPU cpu;
        private Memory memory;
        private Disk disk;

        public Computer() {
            cpu = new CPU();
            memory = new Memory();
            disk = new Disk();
        }

        public void startup() {
            p("start the computer!");
            cpu.on();
            memory.on();
            disk.on();
            p("start computer finished!");
        }

        public void shutdown() {
            p("close the computer!");
            cpu.off();
            memory.off();
            disk.off();
            p("computer closed!");
        }
    }

    private static class CPU {
        public void on() {
            p("CPU on!");
        }

        public void off() {
            p("CPU off!");
        }
    }

    private static class Memory {
        public void on() {
            p("Memory on!");
        }

        public void off() {
            p("Memory off!");
        }
    }

    private static class Disk {
        public void on() {
            p("Disk on!");
        }

        public void off() {
            p("Disk off!");
        }
    }

}
