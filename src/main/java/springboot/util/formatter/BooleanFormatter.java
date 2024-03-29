package springboot.util.formatter;

import lombok.NonNull;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

/**
 * BooleanFormatter
 *
 * @author ljh
 * @since 2021/8/11 11:06
 */
public class BooleanFormatter implements Formatter<Boolean> {

    private String[] values;

    @Override
    public @NonNull Boolean parse(@NonNull String s, @NonNull Locale locale) throws ParseException {
        if (values != null && values.length > 0) {
            return List.of(values).contains(s);
        } else {
            switch (s.toLowerCase(Locale.CHINESE)) {
                case "1":
                case "true":
                case "yes":
                case "是":
                case "有":
                    return true;
            }
        }
        return false;
    }

    @Override
    public @NonNull String print(@NonNull Boolean aBoolean, @NonNull Locale locale) {
        return aBoolean ? Boolean.TRUE.toString() : Boolean.FALSE.toString();
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }
}
