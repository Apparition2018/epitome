package jar.jackson.custom;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import jar.jackson.entity.Person;
import jar.jackson.entity.Person;

import java.io.IOException;
import java.io.Serial;

/**
 * CustomDeserializer
 *
 * @author ljh
 * @since 2021/7/22 11:15
 */
public class CustomDeserializer extends StdDeserializer<Person> {
    @Serial
    private static final long serialVersionUID = 8522054763648986402L;

    public CustomDeserializer() {
        super(Person.class);
    }

    @Override
    public Person deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return null;
    }
}
