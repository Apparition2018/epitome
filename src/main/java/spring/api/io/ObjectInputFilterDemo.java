package spring.api.io;

import l.demo.Person;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ObjectInputFilter;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static l.demo.Demo.DEMO_DIR_PATH;

/**
 * <a href="https://davidvlijmincx.com/posts/creating_context_specific_deserialization_filters/">ObjectInputFilter</a> 反序列化过滤器
 *
 * @author ljh
 * @see <a href="https://snyk.io/blog/serialization-and-deserialization-in-java/">解释 Java 反序列化漏洞</a>
 * @see <a href="https://cryin.github.io/blog/secure-development-java-deserialization-vulnerability/">JAVA 反序列化漏洞之殇</a>
 * @since 2023/9/28 9:23
 */
public class ObjectInputFilterDemo {

    /** @see jar.apache.commons.io.ValidatingObjectInputStreamDemo */
    @Test
    public void testCreateFilter() throws IOException, ClassNotFoundException {
        ObjectInputFilter oif = ObjectInputFilter.Config.createFilter("java.lang.*;l.demo.Person;!*");
        ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(DEMO_DIR_PATH + "person.obj")));
        ois.setObjectInputFilter(oif);
        Person person = (Person) ois.readObject();
        System.err.println(person);
    }

    @Test
    public void testCustomerFilter() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(DEMO_DIR_PATH + "person.obj")));
        ois.setObjectInputFilter(new MyObjectInputFilter());
        Person person = (Person) ois.readObject();
        System.err.println(person);
    }

    static class MyObjectInputFilter implements ObjectInputFilter {
        private final long maxDepth = 3;
        private final long maxReferences = 10;
        private final long maxStreamBytes = 300;

        /** 白名单 */
        private final List<Class<?>> whiteList = List.of(Person.class, Number.class, Integer.class);

        @Override
        public Status checkInput(FilterInfo filterInfo) {
            Class<?> clazz = filterInfo.serialClass();

            if (filterInfo.depth() < 0 ||
                filterInfo.depth() > maxDepth ||
                filterInfo.references() < 0 ||
                filterInfo.references() > maxReferences ||
                filterInfo.streamBytes() < 0 ||
                filterInfo.streamBytes() > maxStreamBytes) {
                return Status.REJECTED;
            } else if (clazz != null && filterInfo.depth() <= maxDepth) {
                System.err.println(clazz);
                System.err.println(filterInfo.depth());
                System.err.println(filterInfo.references());
                System.err.println(filterInfo.streamBytes());
                System.err.println();
            }

            if (clazz != null) {
                if (whiteList.contains(clazz)) {
                    return Status.ALLOWED;
                } else {
                    return Status.REJECTED;
                }
            }

            return Status.UNDECIDED;
        }
    }
}
