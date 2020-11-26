package knowledge.api.util;

import java.util.UUID;

/**
 * UUID (universally unique identifier)
 * 表示通用唯一标识符 (UUID) 的类。 UUID 表示一个 128 位的值。
 * https://www.runoob.com/manual/jdk1.6/java/util/UUID.html
 *
 * @author ljh
 * created on 2020/9/2 17:43
 */
public class UUIDDemo {

    public static void main(String[] args) {
        // static UUID  randomUUID()            获取类型 4（伪随机生成的）UUID 的静态工厂
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());    // 6a0ab1b5-5231-4872-907b-78894221d361

        // static UUID  fromString(String name) 根据 toString() 方法中描述的字符串标准表示形式创建 UUI
        uuid = UUID.fromString("6a0ab1b5-5231-4872-907b-78894221d361");
        System.out.println(uuid.toString());    // 6a0ab1b5-5231-4872-907b-78894221d361

        // int          variant()               与此 UUID 相关联的变体号
        System.out.println(uuid.variant());     // 2
        // int          version()               与此 UUID 相关联的版本号
        System.out.println(uuid.version());     // 4
    }
}
