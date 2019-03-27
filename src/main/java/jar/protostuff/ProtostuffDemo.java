package jar.protostuff;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProtostuffDemo {

    @Test
    public void test() {
        User u3 = new User("张三", 30, 333);
        User u4 = new User("李四", 30, 444);
        User u5 = new User("王五", 30, 555);
        List<User> friends = new ArrayList<>();
        friends.add(u4);
        friends.add(u5);
        u3.setFriends(friends);

        // 序列化
        byte[] u3ByteArray = ProtostuffUtil.serializer(u3);

        // 反序列化
        u3 = ProtostuffUtil.deserializer(u3ByteArray, User.class);
        System.out.println(u3);
    }
}

@Getter
@Setter
@ToString
class User {
    private String name;
    private int age;
    private long phone;
    private List<User> friends;

    public User() {
    }

    public User(String name, int age, long phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }
}
