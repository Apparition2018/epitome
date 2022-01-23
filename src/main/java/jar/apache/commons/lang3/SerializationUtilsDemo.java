package jar.apache.commons.lang3;

import l.demo.Demo;
import l.demo.Person;
import l.demo.Person.Home;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Objects;

/**
 * SerializationUtils
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/SerializationUtils.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class SerializationUtilsDemo extends Demo {

    private Person person;

    @BeforeEach
    public void init() {
        person = new Person().setName("ljh").setHome(new Home().setAddress("gx"));
    }

    /**
     * 浅拷贝
     * 1.基本类型：如果变量是基本类型，则拷贝其值；
     * 2.对象：如果变量是一个实例对象，则拷贝其地址引用，也就是说此时拷贝出的对象与原有对象共享该实例变量，不受访问权限的控制；
     * 3.String：这个比较特殊，拷贝的也是一个地址，是个引用，但是在修改时，它会从字符串池(String pool)中重新生成新的字符串，原有的字符串对象保持不变，在此处我们可以认为String是一个基本类型；
     */
    @Test
    public void testShallowClone() {
        Person clone = person.clone();
        p(Objects.equals(person, clone));               // true
        p(Objects.deepEquals(person, clone));           // true，注意并不是用来判断深拷贝
        p(person.getHome() == clone.getHome());         // true，引用相等，证明是浅拷贝
    }

    /**
     * 深拷贝
     */
    @Test
    public void testDeepCopy() {
        // static <T extends Serializable> T	        clone(T object)
        // 深拷贝
        Person clone = SerializationUtils.clone(person);
        p(Objects.equals(person, clone));               // true
        p(person.getHome() == clone.getHome());         // false，引用不相等，证明是深拷贝
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
        try (OutputStream os = new FileOutputStream(DEMO_PATH + "Serialization.obj");
             InputStream is = new FileInputStream(DEMO_PATH + "Serialization.obj")) {
            // static void	    serialize(Serializable obj, OutputStream outputStream)
            // 序列持久化，Object → bytes[] → File
            SerializationUtils.serialize(person, os);

            // static <T> T	    deserialize(InputStream inputStream)
            // 反序列持久化，File → bytes[] → T
            Person deserialize = SerializationUtils.deserialize(is);

            p(Objects.equals(person, deserialize));     // true
            p(person.getHome() == deserialize.getHome());// false
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
