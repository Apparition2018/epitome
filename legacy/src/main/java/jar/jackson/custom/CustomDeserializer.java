package jar.jackson.custom;

import jar.jackson.entity.Person;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.deser.std.StdDeserializer;

/**
 * CustomDeserializer
 *
 * @author ljh
 * @since 2021/7/22 11:15
 */
public class CustomDeserializer extends StdDeserializer<Person> {

    public CustomDeserializer() {
        super(Person.class);
    }

    @Override
    public Person deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        return null;
    }
}
