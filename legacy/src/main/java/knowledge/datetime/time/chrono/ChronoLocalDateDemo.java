package knowledge.datetime.time.chrono;

import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.util.Locale;

/**
 * ChronoLocalDate
 * <p>子类：LocalDate, HijrahDate(伊斯兰历), MinguoDate(中华民国历), JapanseDate(日本历), ThaiBuddhistDate(泰国佛教历)
 *
 * @author ljh
 * @since 2021/1/16 13:30
 */
public class ChronoLocalDateDemo {

    public static void main(String[] args) {
        Chronology jpChronology = Chronology.ofLocale(Locale.JAPANESE);
        ChronoLocalDate jpChronoLocalDate = jpChronology.dateNow();
        System.out.println(jpChronoLocalDate);
    }
}
