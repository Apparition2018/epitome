package knowledge.datetime.time.chrono;

import org.junit.jupiter.api.Test;

import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.util.Locale;

/**
 * ChronoLocalDate
 * 子类：LocalDate, HijrahDate(伊斯兰历), MinguoDate(中华民国历), JapanseDate(日本历), ThaiBuddhistDate(泰国佛教历)
 *
 * @author ljh
 * created on 2021/1/16 13:30
 */
public class ChronoLocalDateDemo {

    @Test
    public void testChronoLocalDate() {
        Chronology jpChronology = Chronology.ofLocale(Locale.JAPANESE);
        ChronoLocalDate jpChronoLocalDate = jpChronology.dateNow();
        System.out.println(jpChronoLocalDate);
    }
}
