package jar.apache.commons.io;

import l.demo.Person;
import org.apache.commons.io.serialization.ValidatingObjectInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static l.demo.Demo.DEMO_DIR_PATH;

/**
 * ValidatingObjectInputStream
 *
 * @author ljh
 * @since 2023/9/28 8:51
 */
public class ValidatingObjectInputStreamDemo {

	/** @see spring.api.io.ObjectInputFilterDemo */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		InputStream is = Files.newInputStream(Paths.get(DEMO_DIR_PATH + "person.obj"));
		ValidatingObjectInputStream vois = new ValidatingObjectInputStream(is)
				// ValidatingObjectInputStream 		accept(final Class<?>... classes)
				// 接受指定的类进行反序列化，除非它们被拒绝
				.accept(Person.class)
				.accept(Integer.class)
				.accept(Number.class);
		Person person = (Person) vois.readObject();
		System.err.println(person);
	}
}
