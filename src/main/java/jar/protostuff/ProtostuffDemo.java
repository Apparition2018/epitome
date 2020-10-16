package jar.protostuff;

import com.google.common.collect.Lists;
import l.demo.Person;
import org.junit.Test;

public class ProtostuffDemo {

    @Test
    public void testProtostuff() {
        Person person = new Person("张三", 18);
        person.setOtherInfo(Lists.newArrayList("学生", "踢足球"));

        // 序列化
        byte[] personByteArray = ProtostuffUtil.serializer(person);

        // 反序列化
        person = ProtostuffUtil.deserializer(personByteArray, Person.class);
        System.out.println(person);

    }
}
