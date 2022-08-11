package spring.api.util;

import l.demo.Demo;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.JdkIdGenerator;
import org.springframework.util.SimpleIdGenerator;

import java.util.stream.LongStream;

/**
 * IdGenerator
 *
 * @author ljh
 * created on 2020/10/16 12:46
 */
public class IdGeneratorDemo extends Demo {

    public static void main(String[] args) {
        // JdkIdGenerator                           底层调用了 UUID.randomUUID()
        stopWatch.start("JdkIdGenerator");
        JdkIdGenerator jdkIdGenerator = new JdkIdGenerator();
        p(jdkIdGenerator.generateId());             // 41bae9d0-47de-48a6-82c8-313269ced63b
        LongStream.rangeClosed(1, 100_0000L).forEach(i -> jdkIdGenerator.generateId());
        stopWatch.stop();

        // AlternativeJdkIdGenerator                底层调用了 SecureRandom，比 JdkIdGenerator 快
        stopWatch.start("AlternativeJdkIdGenerator");
        AlternativeJdkIdGenerator alternativeJdkIdGenerator = new AlternativeJdkIdGenerator();
        p(alternativeJdkIdGenerator.generateId());  // 17ef448f-6ec1-bc58-8859-e3fb9fca2142
        LongStream.rangeClosed(1, 100_0000L).forEach(i -> alternativeJdkIdGenerator.generateId());
        stopWatch.stop();

        // SimpleIdGenerator                        自增 id
        stopWatch.start("SimpleIdGenerator");
        SimpleIdGenerator simpleIdGenerator = new SimpleIdGenerator();
        p(simpleIdGenerator.generateId());          // 00000000-0000-0000-0000-000000000001
        LongStream.rangeClosed(1, 100_0000L).forEach(i -> simpleIdGenerator.generateId());
        stopWatch.stop();

        p(stopWatch.prettyPrint());
        // ---------------------------------------------
        // ns         %     Task name
        // ---------------------------------------------
        // 407623100  091%  JdkIdGenerator
        // 031363500  007%  AlternativeJdkIdGenerator
        // 010158700  002%  SimpleIdGenerator
    }
}
