package springboot.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IpConverter
 * <p>
 * logback 高级特性使用(二)：https://blog.csdn.net/chenjie2000/article/details/8892764
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
            e.printStackTrace();
        }
        return null;
    }
}
