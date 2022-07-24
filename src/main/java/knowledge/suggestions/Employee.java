package knowledge.suggestions;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.Comparator;

/**
 * 建议73：Comparable 可用做类的默认排序算法，Comparator 可用做扩展排序工具
 *
 * @author ljh
 * created on 2020/10/10 19:23
 */
@Data
@Accessors(chain = true)
class Employee implements Comparable<Employee> {

    private int id;
    private String name;
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

/**
 * 阿里编程规约：
 * 在 JDK7 版本及以上，Comparator 实现类要满足如下三个条件，不然 Arrays.sort，Collections.sort 会抛 IllegalArgumentException 异常
 * 1）x，y 的比较结果和 y，x 的比较结果相反。
 * 2）x > y，y > z，则 x > z。
 * 3）x = y，则 x，z 比较结果和 y，z 比较结果相同。
 */
class PositionComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
