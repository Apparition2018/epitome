package jar.caffeine;

/**
 * DataObject
 *
 * @author ljh
 * @since 2021/11/2 17:11
 */
public record DataObject(String data) {
    private static int objectCounter = 0;

    public static DataObject get(String data) {
        objectCounter++;
        return new DataObject(data);
    }
}
