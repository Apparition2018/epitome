package org.ljh.dependencies.group.apache.commons.io;

import com.ljh.commons.Person;
import org.apache.commons.io.serialization.ValidatingObjectInputStream;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.ljh.commons.Demo.DEMO_DIR_PATH;

/**
 * ValidatingObjectInputStream
 *
 * @author ljh
 * @since 2023/9/28 8:51
 */
public class ValidatingObjectInputStreamTests {

    /** @see java.io.ObjectInputFilter */
    @Test
    public void test() throws IOException, ClassNotFoundException {
        InputStream is = Files.newInputStream(Paths.get(DEMO_DIR_PATH + "person.obj"));
        ValidatingObjectInputStream vois = ValidatingObjectInputStream.builder()
                .setInputStream(is)
                // ValidatingObjectInputStream 		accept(final Class<?>... classes)
                // 接受指定的类进行反序列化，除非它们被拒绝
                .accept(Person.class)
                .accept(Integer.class)
                .accept(Number.class)
                .get();
        Person person = (Person) vois.readObject();
        System.err.println(person);
    }
}
