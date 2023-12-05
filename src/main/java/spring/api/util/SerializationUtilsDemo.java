package spring.api.util;

import org.springframework.util.SerializationUtils;

import java.util.Date;
import java.util.Objects;

/**
 * SerializationUtils
 *
 * @author ljh
 * @since 2021/9/8 16:40
 */
public class SerializationUtilsDemo {

    public static void main(String[] args) {
        final Date date = new Date();

        byte[] bytes = SerializationUtils.serialize(date);

        // 使用 Java 对象序列化，它允许运行任意代码，并且以是许多远程执行代码 （RCE） 漏洞的来源而闻名。
        // 最好使用外部工具（序列化为 JSON、XML 或任何其他格式），会定期检查和更新，以不允许 RCE。
        Object o = SerializationUtils.deserialize(bytes);

        System.out.println(Objects.equals(date, o)); // true
    }
}
