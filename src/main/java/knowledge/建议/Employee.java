package knowledge.建议;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.Comparator;

/**
 * 建议73：Comparable 可用做类的默认排序算法，Comparator 可用做扩展排序工具
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
class Employee implements Comparable<Employee> {

    // id 是根据进入公司的先后顺序编码的
    private int id;

    // 姓名
    private String name;

    // 职位
    private Position position;

    @Override
    public int compareTo(Employee o) {
        // 先按职位排序，再按工号排序
        return new CompareToBuilder().append(position, o.position).append(id, o.id).toComparison();
    }
}

enum Position {
    BOSS, MANAGER, STAFF
}

class PositionComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        // 按照职位降序排序
        return o1.getName().compareTo(o2.getName());
    }
}