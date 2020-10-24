package jar.protostuff;

import com.google.common.collect.Lists;
import l.demo.Person;

public class ProtostuffDemo {

    public static void main(String[] args) {
        Person person = new Person("张三", 18);
        person.setOtherInfo(Lists.newArrayList("学生", "踢足球"));

        // 序列化
        byte[] personByteArray = ProtostuffUtil.serializer(person);

        // 反序列化
        person = ProtostuffUtil.deserializer(personByteArray, Person.class);
        System.out.println(person);

    }
}
