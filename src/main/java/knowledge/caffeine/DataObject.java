package knowledge.caffeine;

import lombok.Data;

/**
 * DataObject
 *
 * @author ljh
 * @since 2021/11/2 17:11
 */
@Data
public class DataObject {
    private final String data;

    private static int objectCounter = 0;

    public static DataObject get(String data) {
        objectCounter++;
        return new DataObject(data);
    }
}
