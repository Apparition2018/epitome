package spring.api.util;

import l.demo.Demo;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.IdGenerator;
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

    private static final StopWatch WATCH = new StopWatch();

    public static void main(String[] args) {
        // JdkIdGenerator                           底层调用了 UUID.randomUUID()
        JdkIdGenerator jdkIdGenerator = new JdkIdGenerator();
        p(jdkIdGenerator.generateId());             // 41bae9d0-47de-48a6-82c8-313269ced63b
        timeUsed("JdkIdGenerator", jdkIdGenerator);

        // AlternativeJdkIdGenerator                底层调用了 SecureRandom，比 JdkIdGenerator 快
        AlternativeJdkIdGenerator alternativeJdkIdGenerator = new AlternativeJdkIdGenerator();
        p(alternativeJdkIdGenerator.generateId());  // 17ef448f-6ec1-bc58-8859-e3fb9fca2142
        timeUsed("AlternativeJdkIdGenerator", alternativeJdkIdGenerator);

        // SimpleIdGenerator                        自增 id
        SimpleIdGenerator simpleIdGenerator = new SimpleIdGenerator();
        p(simpleIdGenerator.generateId());          // 00000000-0000-0000-0000-000000000001
        timeUsed("SimpleIdGenerator", simpleIdGenerator);
    }

    private static void timeUsed(String generator, IdGenerator idGenerator) {
        WATCH.reset();
        WATCH.start();
        LongStream.rangeClosed(1, 100_0000L).forEach(i -> idGenerator.generateId());
        p(String.format("%s 耗时：%sms%n", generator, WATCH.getTime()));
    }

}
