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

        Object o = SerializationUtils.deserialize(bytes);

        System.out.println(Objects.equals(date, o)); // true
    }
}
