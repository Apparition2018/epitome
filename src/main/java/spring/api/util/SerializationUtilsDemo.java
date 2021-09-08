package spring.api.util;

import org.junit.jupiter.api.Test;
import org.springframework.util.SerializationUtils;

import java.util.Date;

/**
 * SerializationUtils
 *
 * @author ljh
 * created on 2021/9/8 16:40
 */
public class SerializationUtilsDemo {

    @Test
    public void testSerializationUtils() {
        final Date date = new Date();

        byte[] bytes = SerializationUtils.serialize(date);

        Object o = SerializationUtils.deserialize(bytes);

        System.out.println(date.equals(o)); // true
    }
}
