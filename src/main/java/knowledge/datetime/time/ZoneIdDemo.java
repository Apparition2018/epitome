package knowledge.datetime.time;

import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Set;

import static l.demo.Demo.p;

/**
 * ZoneId
 * 时区ID, 如Europe-Paris
 * <p>
 * boolean	        equals(Object obj)                  检查是否等
 * ZoneId	        normalized()                        规范化时区ID，在可能的情况下返回一个ZoneOffset
 * <p>
 * https://docs.oracle.com/javase/8/docs/api/java/time/ZoneId.html
 * https://www.yiibai.com/javatime/javatime_zoneid.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class ZoneIdDemo {

    private ZoneId zone;
    private ZoneId zone2;
    private ZoneId zone3;

    /**
     * 获取 ZoneId
     * static ZoneId	from(TemporalAccessor temporal)
     * static ZoneId	of(String zoneId[, Map<String,String> aliasMap])
     * static ZoneId	ofOffset(String prefix, ZoneOffset offset)
     * static ZoneId	systemDefault()
     */
    public ZoneIdDemo() {
        zone = ZoneId.systemDefault();
        zone2 = ZoneId.from(ZonedDateTime.now());
        zone3 = ZoneId.of("Asia/Shanghai");
    }

    /**
     * static Set<String>	getAvailableZoneIds()           获取可用的 zone IDs 集
     */
    @Test
    public void getAvailableZoneIds() {
        Set<String> set = ZoneId.getAvailableZoneIds();
        for (String s : set) {
            p(s);
        }
    }

    /**
     * String           getDisplayName(TextStyle style, Locale locale)
     * 获取区域的文本表示，如"英国时间"或"+02:00"
     */
    @Test
    public void getDisplayName() {
        p(zone.getDisplayName(TextStyle.FULL, Locale.CHINA));               // 中国时间
        p(zone.getDisplayName(TextStyle.FULL_STANDALONE, Locale.CHINA));    // 中国时间
        p(zone.getDisplayName(TextStyle.SHORT, Locale.CHINA));              // CT
        p(zone.getDisplayName(TextStyle.SHORT_STANDALONE, Locale.CHINA));   // CT
        p(zone.getDisplayName(TextStyle.NARROW, Locale.CHINA));             // Asia/Shanghai
        p(zone.getDisplayName(TextStyle.NARROW_STANDALONE, Locale.CHINA));  // CT
    }

}
