package other.suggestions;

import lombok.Getter;
import lombok.Setter;

/**
 * 建议38：使用静态内部类提高封装性
 */
@Getter
@Setter
public class Person {
    // 姓名
    private String name;
    // 家庭
    private Home home;

    public Person(String _name) {
        name = _name;
    }

    @Getter
    @Setter
    public static class Home {
        // 家庭地址
        private String address;
        // 家庭电话
        private String tel;

        public Home(String _address, String _tel) {
            address = _address;
            tel = _tel;
        }
    }

    private static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }

}