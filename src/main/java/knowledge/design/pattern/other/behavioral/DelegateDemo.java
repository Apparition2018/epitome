package knowledge.design.pattern.other.behavioral;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

import java.util.EnumMap;

/**
 * <a href="https://gupaoedu-tom.blog.csdn.net/article/details/121225325">委派模式</a>
 * <p>使用实例：
 * <pre>
 * 1 双亲委派
 * 2 {@link BeanDefinitionParserDelegate}
 * </pre>
 *
 * @author ljh
 * @since 2022/2/9 18:04
 */
public class DelegateDemo {

    public static void main(String[] args) {
        Boss boss = new Boss();
        Leader leader = new Leader();
        boss.command(TaskEnum.CRAWLER, leader);
        boss.command(TaskEnum.SELL, leader);
    }

    @Getter
    @AllArgsConstructor
    enum TaskEnum {
        CRAWLER("爬虫"), DESIGN("设计"), SELL("销售");
        private final String taskName;
    }

    interface IEmployee {
        void doing(TaskEnum task);
    }

    private static class EmployeeA implements IEmployee {
        protected String goodAt = "编程";

        public void doing(TaskEnum task) {
            System.out.printf("我是员工A，我擅长%s，现在开始做[%s]工作%n", goodAt, task.getTaskName());
        }
    }

    private static class EmployeeB implements IEmployee {
        protected String goodAt = "平面设计";

        public void doing(TaskEnum task) {
            System.out.printf("我是员工B，我擅长%s，现在开始做[%s]工作%n", goodAt, task.getTaskName());
        }
    }

    private static class Leader implements IEmployee {

        private final EnumMap<TaskEnum, IEmployee> employee = new EnumMap<>(TaskEnum.class);

        public Leader() {
            employee.put(TaskEnum.CRAWLER, new EmployeeA());
            employee.put(TaskEnum.DESIGN, new EmployeeB());
        }

        public void doing(TaskEnum task) {
            if (!employee.containsKey(task)) {
                System.out.println("这个任务[" + task.getTaskName() + "]超出我的能力范围");
                return;
            }
            employee.get(task).doing(task);
        }
    }

    private static class Boss {
        public void command(TaskEnum task, Leader leader) {
            leader.doing(task);
        }
    }
}
