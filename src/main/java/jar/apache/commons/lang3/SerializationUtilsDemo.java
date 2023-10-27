package jar.apache.commons.lang3;

import l.demo.Demo;
import l.demo.Person;
import l.demo.Person.Home;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * <a href="http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/SerializationUtils.html">SerializationUtils</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class SerializationUtilsDemo extends Demo {

    private Person person;

    @BeforeEach
    public void init() {
        person = new Person().setName(MY_NAME).setHome(new Home().setAddress("gx"));
    }

    /**
     * 浅克隆
     * <pre>
     * 1 基本类型：如果变量是基本类型，则拷贝其值；
     * 2 对象：如果变量是一个实例对象，则拷贝其地址引用，也就是说此时拷贝出的对象与原有对象共享该实例变量，不受访问权限的控制；
     * 3 String：这个比较特殊，拷贝的也是一个地址，是个引用，但是在修改时，它会从字符串池(String pool)中重新生成新的字符串，原有的字符串对象保持不变，在此处我们可以认为String是一个基本类型；
     * </pre>
     */
    @Test
    public void testShallowDeepClone() {
        // 浅克隆
        Person shallowClone = person.clone();
        p(Objects.equals(person, shallowClone));            // true
        p(person.getHome() == shallowClone.getHome());      // true，引用相等，证明是浅克隆

        // 深克隆
        Person deepClone = SerializationUtils.clone(person);
        p(Objects.equals(person, deepClone));               // true
        p(person.getHome() == deepClone.getHome());         // false，引用不相等，证明是深克隆
    }

    @Test
    public void testByteSerialize() {
        // static byte[]	    serialize(Serializable obj)
        // 序列化，Object → byte[]
        byte[] bytes = SerializationUtils.serialize(person);

        // static <T> T         deserialize(byte[] objectData)
        // 反序列化，byte[] → T
        Person deserialize = SerializationUtils.deserialize(bytes);

        p(Objects.equals(person, deserialize));         // true
        p(person.getHome() == deserialize.getHome());   // false
    }

    @Test
    public void testByteStreamSerialize() {
        Path path = Paths.get(DEMO_DIR_PATH + "Serialization.obj");
        try (OutputStream os = Files.newOutputStream(path);
             InputStream is = Files.newInputStream(path)) {
            // static void	    serialize(Serializable obj, OutputStream outputStream)
            // 序列持久化，Object → bytes[] → File
            SerializationUtils.serialize(person, os);

            // static <T> T	    deserialize(InputStream inputStream)
            // 反序列持久化，File → bytes[] → T
            Person deserialize = SerializationUtils.deserialize(is);

            p(Objects.equals(person, deserialize));     // true
            p(person.getHome() == deserialize.getHome());// false
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
