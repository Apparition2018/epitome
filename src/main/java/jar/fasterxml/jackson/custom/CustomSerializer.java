package jar.fasterxml.jackson.custom;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import jar.fasterxml.jackson.entity.Person;

import java.io.IOException;
import java.io.Serial;

/**
 * CustomSerializer
 *
 * @author ljh
 * @since 2021/7/22 11:11
 */
public class CustomSerializer extends StdSerializer<Person> {
    @Serial
    private static final long serialVersionUID = 5013605440045804306L;

    public CustomSerializer() {
        super(Person.class);
    }

    @Override
    public void serialize(Person person, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    }
}
