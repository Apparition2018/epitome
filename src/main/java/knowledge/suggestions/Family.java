package knowledge.suggestions;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * 建议41：内部类实现多继承
 * 建议44：使用序列化对象的拷贝
 *
 * @author ljh
 * created on 2020/10/10 19:23
 */
class Family {


    interface Father {
        int strong();
    }

    interface Mother {
        int kind();
    }

    static class FatherImpl implements Father {
        // 父亲的强壮指数为8
        @Override
        public int strong() {
            return 8;
        }
    }

    static class MotherImpl implements Mother {
        // 母亲的温柔指数为8
        @Override
        public int kind() {
            return 8;
        }
    }

    static class Son extends FatherImpl implements Mother, Serializable {
        private static final long serialVersionUID = 568586027964879053L;

        @Override
        public int strong() {
            // 儿子比父亲强壮
            return super.strong() + 1;
        }

        @Override
        public int kind() {
            return new MotherSpecial().kind();
        }

        static class MotherSpecial extends MotherImpl {
            @Override
            public int kind() {
                // 儿子的温柔指数降低了
                return super.kind() - 1;
            }
        }
    }

    @Data
    static class Daughter extends MotherImpl implements Father {

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
                return name.equalsIgnoreCase(d.getName());
            }
            return false;
        }
    }
}