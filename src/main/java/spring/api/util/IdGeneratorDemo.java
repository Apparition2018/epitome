package spring.api.util;

import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.JdkIdGenerator;
import org.springframework.util.SimpleIdGenerator;

/**
 * IdGenerator
 *
 * @author ljh
 * created on 2020/10/16 12:46
 */
public class IdGeneratorDemo {

    public static void main(String[] args) {
        // JdkIdGenerator
        // 底层调用了 UUID.randomUUID()
        System.out.println(new JdkIdGenerator().generateId());              // 41bae9d0-47de-48a6-82c8-313269ced63b

        // AlternativeJdkIdGenerator
        // 底层调用了 SecureRandom
        System.out.println(new AlternativeJdkIdGenerator().generateId());   // 17ef448f-6ec1-bc58-8859-e3fb9fca2142

        // SimpleIdGenerator
        // 自增 id
        System.out.println(new SimpleIdGenerator().generateId());           // 00000000-0000-0000-0000-000000000001
    }
}
