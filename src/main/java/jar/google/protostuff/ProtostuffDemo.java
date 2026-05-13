package jar.google.protostuff;

import l.demo.Person;

import java.util.List;

/**
 * Protostuff
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class ProtostuffDemo {

    public static void main(String[] args) {
        Person person = new Person("张三", 18);
        person.setOtherInfo(List.of("学生", "踢足球"));

        // 序列化
        byte[] personByteArray = ProtostuffUtil.serializer(person);

        // 反序列化
        person = ProtostuffUtil.deserializer(personByteArray, Person.class);
        System.out.println(person);
    }
}
