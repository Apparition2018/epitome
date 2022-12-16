package springboot.formatter;

import lombok.NonNull;
import org.springframework.context.support.EmbeddedValueResolutionSupport;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.util.HashSet;
import java.util.Set;

/**
 * BooleanFormatAnnotationFormatterFactory
 *
 * @author ljh
 * @since 2021/8/11 11:14
 */
public class BooleanFormatAnnotationFormatterFactory extends EmbeddedValueResolutionSupport implements AnnotationFormatterFactory<BooleanFormat> {
    @Override
    public @NonNull Set<Class<?>> getFieldTypes() {
        return new HashSet<Class<?>>() {
            private static final long serialVersionUID = 1403929137022266150L;

            {
                add(String.class);

                add(Boolean.class);
            }
        };
    }

    @Override
    public @NonNull Printer<?> getPrinter(BooleanFormat booleanFormat, @NonNull Class<?> aClass) {
        BooleanFormatter booleanFormatter = new BooleanFormatter();
        booleanFormatter.setValues(booleanFormat.values());
        return booleanFormatter;
    }

    @Override
    public @NonNull Parser<?> getParser(BooleanFormat booleanFormat, @NonNull Class<?> aClass) {
        BooleanFormatter booleanFormatter = new BooleanFormatter();
        booleanFormatter.setValues(booleanFormat.values());
        return booleanFormatter;
    }
}
