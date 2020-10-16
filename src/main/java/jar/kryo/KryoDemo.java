package jar.kryo;

import com.google.common.collect.Lists;
import l.demo.Person;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class KryoDemo {

    @Test
    public void testKryo() {
        Person person = new Person("张三", 18);
        person.setOtherInfo(Lists.newArrayList("学生", "踢足球"));

        // 序列化
        byte[] personByteArray = KryoUtil.writeToByteArray(person);

        // 反序列化
        person = KryoUtil.readFromByteArray(personByteArray);
        System.out.println(person);

        // 序列化 String (Base64)
        String personBase64 = KryoUtil.writeToString(person);
        System.out.println(personBase64);
    }

}

