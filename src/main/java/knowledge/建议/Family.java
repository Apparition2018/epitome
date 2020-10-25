package knowledge.建议;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * 建议41：内部类实现多继承
 * 建议44：使用序列化对象的拷贝
 */
public class Family {


    interface Father {
        int strong();
    }

    interface Mother {
        int kind();
    }

    private static class FatherImpl implements Father {
        // 父亲的强壮指数为8
        @Override
        public int strong() {
            return 8;
        }
    }

    private static class MotherImpl implements Mother {
        // 母亲的温柔指数为8
        @Override
        public int kind() {
            return 8;
        }
    }

    public static class Son extends FatherImpl implements Mother, Serializable {

        @Override
        public int strong() {
            // 儿子比父亲强壮
            return super.strong() + 1;
        }

        @Override
        public int kind() {
            return new MotherSpecial().kind();
        }

        private static class MotherSpecial extends MotherImpl {
            @Override
            public int kind() {
                // 儿子的温柔指数降低了
                return super.kind() - 1;
            }
        }
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Daughter extends MotherImpl implements Father {

        private String name;

        @Override
        public int strong() {
            return new FatherImpl() {
                @Override
                public int strong() {
                    // 女儿的强壮指数降低了
                    return super.strong() - 2;
                }
            }.strong();
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        public boolean equals(Object obj) {
            if (null != obj && obj.getClass() == this.getClass()) {
                Daughter d = (Daughter) obj;
                return name.equalsIgnoreCase(d.getName().trim());
            }
            return false;
        }
    }
}