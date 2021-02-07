package knowledge.datetime.time;

import org.junit.jupiter.api.Test;

import java.time.ZoneOffset;
import java.time.temporal.ChronoField;

import static l.demo.Demo.p;

/**
 * ZoneOffset
 * 格林威治/UTC偏移时区，如+02:00
 * <p>
 * boolean	        equals(Object obj)                  检查是否等
 * int	            compareTo(ZoneOffset other)         将此 offset 按降序与另一个 offset 进行比较
 *
 * <p>
 * https://docs.oracle.com  /javase/8/docs/api/java/time/ZoneOffset.html
 * https://www.yiibai.com/javatime/javatime_zoneoffset.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class ZoneOffsetDemo {

    private ZoneOffset zo;
    private ZoneOffset zo2;
    private ZoneOffset zo3;

    /**
     * 获取 ZoneOffset
     * static ZoneOffset	from(TemporalAccessor temporal)
     * static ZoneOffset	of(String offsetId)
     * static ZoneOffset	ofHours(int hours)
     * static ZoneOffset	ofHoursMinutes(int hours, int minutes)
     * static ZoneOffset	ofHoursMinutesSeconds(int hours, int minutes, int seconds)
     * static ZoneOffset	ofTotalSeconds(int totalSeconds)
     */
    public ZoneOffsetDemo() {
        zo = ZoneOffset.of("Z");
        zo2 = ZoneOffset.ofHours(7);
        zo3 = ZoneOffset.ofHoursMinutesSeconds(9, 9, 9);
    }

    @Test
    public void constant() {
        p(ZoneOffset.MAX);  // +18:00
        p(ZoneOffset.MIN);  // -18:00
        p(ZoneOffset.UTC);  // Z
    }

    /**
     * String	        getId()                             获取规范化 zone offset ID
     * long	            getLong(TemporalField field)        获取指定字段的值
     * ZoneRules	    getRules()                          获取关联的 time-zone 规则
     * int	            getTotalSeconds()                   获取以秒为单位的总偏移量
     */
    @Test
    public void get() {
        p(zo.getId());                              // Z
        p(zo2.getId());                             // +07:00
        p(zo3.getId());                             // +09:09:09

        p(zo.getLong(ChronoField.OFFSET_SECONDS));  // 0
        p(zo2.getLong(ChronoField.OFFSET_SECONDS)); // 25200
        p(zo3.getLong(ChronoField.OFFSET_SECONDS)); // 32949

        p(zo.getRules());                           // ZoneRules[currentStandardOffset=Z]
        p(zo2.getRules());                          // ZoneRules[currentStandardOffset=+07:00]
        p(zo3.getRules());                          // ZoneRules[currentStandardOffset=+09:09:09]

        p(zo.getTotalSeconds());                    // 0
        p(zo2.getTotalSeconds());                   // 25200
        p(zo3.getTotalSeconds());                   // 32949
    }
}
