package knowledge.datetime.time;

import org.junit.Test;

import java.time.ZoneId;
import java.time.ZoneOffset;
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
    
    @Test
    public void testZoneId() {
        ZoneId zone;
        // static ZoneId	    systemDefault()                             获取系统默认时区
        zone = ZoneId.systemDefault();
        // static ZoneId	    from(TemporalAccessor temporal)             从时间对象中获取ZoneId的实例
        zone = ZoneId.from(ZonedDateTime.now());
        // static ZoneId	    ofOffset(String prefix, ZoneOffset offset)  获取包装偏移量的ZoneId实例
        zone = ZoneId.ofOffset("UTC", ZoneOffset.UTC);
        // static ZoneId	    of(String zoneId[, Map<String,String> aliasMap])
        // 从一个ID中获取一个ZoneId的实例，确保这个ID是有效的并且可以使用
        // 使用别名映射获取ZoneId实例，以补充标准的zone ID
        zone = ZoneId.of("Asia/Shanghai");
        
        
        // static Set<String>	getAvailableZoneIds()                       获取可用的zone id集
        Set<String> set = ZoneId.getAvailableZoneIds();
        for (String zoneId : set) {
            p(zoneId);
        }
        
        // String           getDisplayName(TextStyle style, Locale locale)  获取区域的文本表示，如"英国时间"或"+02:00"
        p(zone.getDisplayName(TextStyle.FULL, Locale.CHINA));               // 中国时间
        p(zone.getDisplayName(TextStyle.FULL_STANDALONE, Locale.CHINA));    // 中国时间
        p(zone.getDisplayName(TextStyle.SHORT, Locale.CHINA));              // CT
        p(zone.getDisplayName(TextStyle.SHORT_STANDALONE, Locale.CHINA));   // CT
        p(zone.getDisplayName(TextStyle.NARROW, Locale.CHINA));             // Asia/Shanghai
        p(zone.getDisplayName(TextStyle.NARROW_STANDALONE, Locale.CHINA));  // CT
    }
}
