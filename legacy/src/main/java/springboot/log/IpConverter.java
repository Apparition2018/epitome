package springboot.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IpConverter
 * <p>
 * <a href="https://blog.csdn.net/chenjie2000/article/details/8892764">logback 高级特性使用(二)</a>
 *
 * @author ljh
 * @since 2021/4/7 11:08
 */
public class IpConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
