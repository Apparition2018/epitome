package jar.protostuff;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Protostuff 使用示例：https://blog.csdn.net/chszs/article/details/80354356
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class ProtostuffUtil {

    private static final Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();

    private static <T> Schema<T> getSchema(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        Schema<T> schema = (Schema<T>) cachedSchema.get(clazz);
        if (null == schema) {
            // RuntimeSchema    用于在运行时从 Java 实体对象中生成所需要的模式 Schema
            schema = RuntimeSchema.getSchema(clazz);
            if (null != schema) {
                cachedSchema.put(clazz, schema);
            }
        }
        return schema;
    }

    /**
     * 将对象序列化
     */
    public static <T> byte[] serializer(T obj) {
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(clazz);
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    /**
     * 将字节数组数据反序列化
     */
    public static <T> T deserializer(byte[] data, Class<T> clazz) {
        try {
            T obj = clazz.newInstance();
            Schema<T> schema = getSchema(clazz);
            ProtostuffIOUtil.mergeFrom(data, obj, schema);
            return obj;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}