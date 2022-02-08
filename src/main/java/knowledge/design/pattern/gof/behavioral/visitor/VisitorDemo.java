package knowledge.design.pattern.gof.behavioral.visitor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.ElementVisitor;
import javax.lang.model.type.TypeVisitor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 访问者模式：将作用于某种数据结构中的各元素的操作分离出来封装成独立的类，使其在不改变数据结构的前提下可以添加作用于这些元素的新的操作，为数据结构中的每个元素提供多种访问方式
 * 使用场景：解耦数据结构和数据操作
 * 使用实例：
 * 1.{@link java.nio.file.FileVisitor} 和 {@link java.nio.file.SimpleFileVisitor}：http://c.biancheng.net/view/8501.html
 * 2.{@link AnnotationValueVisitor}, {@link ElementVisitor} 和 {@link TypeVisitor}
 * 3.{@link org.springframework.beans.factory.config.BeanDefinitionVisitor}：http://c.biancheng.net/view/8502.html
 * <p>
 * 角色：
 * 抽象访问者 Visitor：为每个 ConcreteElement 都定义一个访问接口 visit(ConcreteElement element)
 * 具体访问者 ConcreteVisitor
 * 抽象元素 Element：定义接受访问接口 accept(Visitor visitor)
 * 具体元素 ConcreteElement：实现 accept(Visitor visitor)，其中通常包含 visitor.visit(this)
 * 对象结构 ObjectStructure：持有 Element 集合的引用，提供遍历每个 Element 并调用 accept(Visitor visitor) 的方法
 * <p>
 * 优点：符合单一职责原则
 * 缺点：
 * 1.开闭原则：只增加 ConcreteVisitor 符合；增加 ConcreteElement 违反
 * 2.违反依赖倒置原则
 * 3.可能破坏封装 ???
 * 扩展：当 ConcreteElement 为 LeafElement/CompositeElement 时，与 Composite 联合使用
 * <p>
 * Visitor：https://refactoringguru.cn/design-patterns/visitor
 * Java设计模式：http://c.biancheng.net/view/1397.html
 * 菜鸟模式：https://www.runoob.com/design-pattern/visitor-pattern.html
 * 设计模式之美：访问者模式（上）：手把手带你还原访问者模式诞生的思维过程
 * 设计模式之美：访问者模式（下）：为什么支持双分派的语言不需要访问者模式？
 *
 * @author ljh
 * created on 2020/9/26 2:51
 */
public class VisitorDemo {

    /**
     * OA 系统
     * https://blog.csdn.net/LoveLion/article/details/7433576
     */
    @Test
    public void testVisitor() {
        EmployeeList employeeList = new EmployeeList();
        employeeList.addEmployee(new FullTimeEmployee("Liam", 4000.0, 45));
        employeeList.addEmployee(new PartTimeEmployee("Olivia", 80, 8));
        employeeList.accept(new FADepartment());
        employeeList.accept(new HRDepartment());

        Employee employee = new FullTimeEmployee("Liam", 4000.0, 45);
        employee.accept(new FADepartment());
    }

    /**
     * Element
     * 员工
     */
    interface Employee {
        void accept(Department dept);
    }

    /**
     * ConcreteElement
     * 全职员工
     */
    @Getter
    @AllArgsConstructor
    static class FullTimeEmployee implements Employee {
        private final String name;
        private final double weeklyWage;
        private final int workTime;

        @Override
        public void accept(Department dept) {
            dept.visit(this);
        }
    }

    /**
     * ConcreteElement
     * 兼职员工
     */
    @Getter
    @AllArgsConstructor
    static class PartTimeEmployee implements Employee {
        private final String name;
        private final double hourlyWage;
        private final int workTime;

        @Override
        public void accept(Department dept) {
            dept.visit(this);
        }
    }

    /**
     * Visitor
     * 部门
     */
    static abstract class Department {
        public abstract void visit(FullTimeEmployee employee);

        public abstract void visit(PartTimeEmployee employee);
    }

    /**
     * ConcreteVisitor
     * 财务部门
     */
    static class FADepartment extends Department {
        @Override
        public void visit(FullTimeEmployee employee) {
            int workTime = employee.getWorkTime();
            double weeklyWage = employee.getWeeklyWage();

            if (workTime > 40) {
                weeklyWage = weeklyWage + (workTime - 40) * 100;
            } else if (workTime < 40) {
                weeklyWage = weeklyWage - (40 - workTime) * 80;
                if (weeklyWage < 0) {
                    weeklyWage = 0;
                }
            }
            System.out.printf("正式员工 %s 实际工资为：%s元。%n", employee.getName(), weeklyWage);
        }

        @Override
        public void visit(PartTimeEmployee employee) {
            int workTime = employee.getWorkTime();
            double hourlyWage = employee.getHourlyWage();
            System.out.printf("临时工 %s 实际工资为：%s元。%n", employee.getName(), workTime * hourlyWage);
        }
    }

    /**
     * ConcreteVisitor
     * 人力资源部门
     */
    static class HRDepartment extends Department {
        public void visit(FullTimeEmployee employee) {
            int workTime = employee.getWorkTime();
            System.out.printf("正式员工 %s 实际工作时间为：%s小时。%n", employee.getName(), workTime);
            if (workTime > 40) {
                System.out.printf("正式员工 %s 加班时间为：%s小时。%n", employee.getName(), workTime - 40);
            } else if (workTime < 40) {
                System.out.printf("正式员工 %s 请假时间为：%s小时。%n", employee.getName(), 40 - workTime);
            }
        }

        public void visit(PartTimeEmployee employee) {
            int workTime = employee.getWorkTime();
            System.out.printf("临时工 %s 实际工作时间为：%s小时。%n", employee.getName(), workTime);
        }
    }

    /**
     * ObjectStructure
     */
    static class EmployeeList {
        private final List<Employee> list = new ArrayList<>();

        public void accept(Department dept) {
            for (Employee employee : list) {
                employee.accept(dept);
            }
        }

        public void addEmployee(Employee... employees) {
            list.addAll(Arrays.asList(employees));
        }
    }

}