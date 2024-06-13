package knowledge.design.pattern.other.behavioral;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 规格模式
 * <p>角色：
 * <pre>
 * 抽象规格 Specification
 * 组合规格 CompositeSpecification
 * 与规格 AndSpecification
 * 或规格 OrSpecification
 * 非规格 NotSpecification
 * 业务规格 BizSpecification
 * </pre>
 * 参考：
 * <pre>
 * <a href="https://java-design-patterns.com/patterns/specification/">Specification</a>
 * <a href="https://www.kancloud.cn/sstd521/design/193648">Specification</a>
 * </pre>
 *
 * @author ljh
 * @since 2022/2/8 17:46
 */
public class SpecificationDemo {

    public static void main(String[] args) {
        List<User> userList = List.of(
            new User("Andy", 23),
            new User("Jack", 82),
            new User("King", 10)
        );

        UserProvider userProvider = new UserProvider(userList);
        UserByNameEqual nameEqualSpec = new UserByNameEqual("Andy");
        ISpecification ageThanSpec = new UserByAgeThan(25);

        for (User user : userProvider.findUser(nameEqualSpec.or(ageThanSpec))) {
            System.out.println(user.name());
        }
    }

    interface ISpecification {
        boolean isSatisfiedBy(User user);

        ISpecification and(ISpecification specification);

        ISpecification or(ISpecification specification);

        ISpecification not();
    }

    private static abstract class CompositeSpecification implements ISpecification {
        public abstract boolean isSatisfiedBy(User user);

        public ISpecification and(ISpecification spec) {
            return new AndSpecification(this, spec);
        }

        public ISpecification or(ISpecification spec) {
            return new OrSpecification(this, spec);
        }

        public ISpecification not() {
            return new NotSpecification(this);
        }
    }

    @AllArgsConstructor
    private static class AndSpecification extends CompositeSpecification {
        private final ISpecification left;
        private final ISpecification right;

        public boolean isSatisfiedBy(User user) {
            return left.isSatisfiedBy(user) && right.isSatisfiedBy(user);
        }
    }

    @AllArgsConstructor
    private static class OrSpecification extends CompositeSpecification {
        private final ISpecification left;
        private final ISpecification right;

        public boolean isSatisfiedBy(User user) {
            return left.isSatisfiedBy(user) || right.isSatisfiedBy(user);
        }
    }

    @AllArgsConstructor
    private static class NotSpecification extends CompositeSpecification {
        private final ISpecification specification;

        public boolean isSatisfiedBy(User user) {
            return !specification.isSatisfiedBy(user);
        }
    }

    /**
     * BizSpecification
     * 性名相同
     */
    @AllArgsConstructor
    private static class UserByNameEqual extends CompositeSpecification {
        private final String name;

        public boolean isSatisfiedBy(User user) {
            return Objects.equals(user.name(), name);
        }
    }

    /**
     * BizSpecification
     * 大于基准年龄
     */
    @AllArgsConstructor
    private static class UserByAgeThan extends CompositeSpecification {
        private final int age;

        public boolean isSatisfiedBy(User user) {
            return user.age > age;
        }
    }

    interface IUserProvider {
        List<User> findUser(ISpecification specification);
    }

    private record UserProvider(List<User> userList) implements IUserProvider {
        @Override
        public List<User> findUser(ISpecification specification) {
            List<User> result = new ArrayList<>();
            for (User user : userList) {
                if (specification.isSatisfiedBy(user)) result.add(user);
            }
            return result;
        }
    }

    private record User(String name, int age) {
    }
}
