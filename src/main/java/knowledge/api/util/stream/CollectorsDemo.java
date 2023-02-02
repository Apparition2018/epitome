package knowledge.api.util.stream;

import l.demo.Demo;
import l.demo.Person;
import org.junit.jupiter.api.Test;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <a href="https://www.cnblogs.com/felordcn/p/collectors.html">Collectors</a>
 * <p>实现了各种归约操作
 *
 * @author ljh
 * @since 2021/1/14 14:51
 */
public class CollectorsDemo extends Demo {

    @Test
    public void toXXX() {
        // List<Object> -> List<String>
        List<String> idList = personList.stream().map(Person::getName).collect(Collectors.toList());
        p(idList);  // [张三, 李四, 王五]

        // List<Object> -> Map<Integer, Object>
        // 阿里编程规约：
        // 1.在使用 Collectors 类的 toMap() 方法转为 Map 集合时，一定要使用参数类型为 BinaryOperator，参数名为 mergeFunction 的方法，否则当出现相同 key 时会抛出 IllegalStateException 异常
        personList.add(new Person(3, "赵六"));
        Map<Integer, Object> personMap = personList.stream().collect(Collectors.toMap(Person::getId, Function.identity(),
                (duplicate1, duplicate2) -> duplicate2, // 合并函数，解决相同 key 冲突问题
                TreeMap::new)                           // 自定义返回 Map 类型
        );
        p(personMap);   // {1=Person{id=1, name='张三'}, 2=Person{id=2, name='李四'}, 3=Person{id=3, name='赵六'}}
        // personMap = personList.stream().collect(Collectors.toMap(Person::getId, Function.identity())); // Duplicate key Person{id=3, name='王五'}
        // 2.在使用 Collectors 类的 toMap() 方法转为 Map 集合时，一定要注意当 value 为 null 时会抛 NPE 异常
        personList.add(new Person(4, null));
        Map<Integer, String> personMap2 = personList.stream().collect(Collectors.toMap(Person::getId, entry -> Optional.ofNullable(entry.getName()).orElse("未知"), (d1, d2) -> d2));
        p(personMap2);  // {1=张三, 2=李四, 3=赵六, 4=未知}
        // personMap2 = personList.stream().collect(Collectors.toMap(Person::getId, Person::getName, (d1, d2) -> d2));   // NullPointerException

        // Map<String, Object> → Map<String, Object>
        map = map.entrySet().stream().filter(entry -> entry.getKey() % 2 == 1).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (d1, d2) -> d2));
        p(map);         // {1=A, 3=C}
    }

    @Test
    public void testCollectors() {
        List<String> names = Arrays.asList("Luna", "Olivia", "Cora", "Leo", "Henry");

        // toCollection(), toList(), toSet(), toMap(), toConcurrentMap()
        p(names.stream().collect(Collectors.toList()));

        // joining()                                连接字符串
        p(names.stream().collect(Collectors.joining(", ")));            // Luna, Olivia, Cora, Leo, Henry
        p(names.stream().collect(Collectors.joining(", ", "[", "]")));  // [Luna, Olivia, Cora, Leo, Henry]

        // collectingAndThen()                      先执行归约操作，再执行 Function 操作
        p(names.stream().collect(Collectors.collectingAndThen(Collectors.joining(", "), String::toUpperCase)));
        // LUNA, OLIVIA, CORA, LEO, HENRY

        // mapping()                                先执行 Function 操作，再进行归约操作
        p(names.stream().collect(Collectors.mapping(String::toUpperCase, Collectors.toList())));
        // [LUNA, OLIVIA, CORA, LEO, HENRY]

        // groupingBy() / groupingByConcurrent()    分组，Function
        p(names.stream().collect(Collectors.groupingBy(String::length)));
        // {3=[Leo], 4=[Luna, Cora], 5=[Henry], 6=[Olivia]}

        // partitioningBy()                         分区，Predicate
        p(names.stream().collect(Collectors.partitioningBy(name -> name.length() > 4)));
        // {false=[Luna, Cora, Leo], true=[Olivia, Henry]}

        // maxBy() / minBy()                        最大/最小
        p(names.stream().collect(Collectors.maxBy(Comparator.comparingInt(String::length)))); // Optional[Olivia]
        p(names.stream().collect(Collectors.minBy(Comparator.comparingInt(String::length)))); // Optional[Leo]

        // summingXXX()                             和
        p(names.stream().collect(Collectors.summingInt(String::length)));   // 22

        // averagingXXX()                           平均值
        p(names.stream().collect(Collectors.averagingInt(String::length))); // 4.4

        // summarizingXXX()                         统计
        IntSummaryStatistics stats = names.stream().collect(Collectors.summarizingInt(String::length));
    }

    /**
     * Java 官方例子：统计每个城市个子最高的人
     */
    @Test
    public void testReducing() {
        List<Person> personList = new ArrayList<>() {
            @Serial
            private static final long serialVersionUID = -1481510473440954731L;

            {
                add(new Person("张三", 20, "男"));
                add(new Person("李四", 21, "女"));
                add(new Person("王五", 22, "男"));
                add(new Person("赵六", 23, "女"));
            }
        };

        Person identity = new Person();
        identity.setName("identity");
        identity.setAge(22);

        Function<Person, Person> round = person -> {
            Integer age = person.getAge();
            BigDecimal decimal = new BigDecimal(age);
            age = decimal.setScale(0, RoundingMode.HALF_UP).intValue();
            person.setAge(age);
            return person;
        };

        Comparator<Person> compareAge = Comparator.comparing(Person::getAge);

        Map<String, Person> collect = personList.stream().collect(Collectors.groupingBy(Person::getGender,
                Collectors.reducing(identity, round, BinaryOperator.maxBy(compareAge))));
        p(collect); // {女=Person{name='赵六', age=23, gender='女'}, 男=Person{name='identity', age=22}}
    }
}
