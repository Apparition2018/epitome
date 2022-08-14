package jar.jedis.data.type;

/**
 * RedisDataType
 * <p>
 * String
 * 1.大多数操作 O(1)；SUBSTR、GETRANGE、SETRANGE O(n)
 * 2.当需要将结构化数据存储为序列化字符串时，可考虑使用 Hash 和 RedisJSON
 * https://redis.io/docs/data-types/strings/
 * <p>
 * Hash
 * 1.大多数操作 O(1)；HKEYS、HVALS、HGETALL O(n)
 * 2.最大存储 2^32 - 1，实际上仅受托管 Redis 部署的 VM 上的总内存限制
 * https://redis.io/docs/data-types/hashes/
 * <p>
 * Redis Geospatial
 * 1.坐标
 * https://redis.io/docs/data-types/geospatial/
 *
 * @author ljh
 * created on 2022/8/12 2:51
 */
public class RedisDataType {
}
