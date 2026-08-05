package jar.jackson.custom;

import jar.jackson.entity.Person;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

/**
 * CustomSerializer
 *
 * @author ljh
 * @since 2021/7/22 11:11
 */
public class CustomSerializer extends StdSerializer<Person> {

    public CustomSerializer() {
        super(Person.class);
    }

    @Override
    public void serialize(Person value, JsonGenerator gen, SerializationContext ctxt) throws JacksonException {
    }
}
