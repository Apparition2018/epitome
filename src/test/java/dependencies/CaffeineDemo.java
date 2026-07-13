package dependencies;

import com.github.benmanes.caffeine.cache.*;
import org.apache.commons.lang3.time.DateUtils;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <a href="https://www.baeldung.com/java-caching-caffeine">Caffeine</a>
 *
 * <p>同步策略：不主动同步，未命中查 Redis/DB 回填：
 * <pre>
 * 1 设置过期时间（容忍不一致）
 * 2 写 DB 时发 MQ 广播淘汰
 * </pre>
 *
 * <pre>
 * 使用场景         过期时间            刷新策略                        说明
 * 防重放/防刷       按情况             expire
 * 超热点数据        3s~10s      refresh(3s)+expire(10s)     异步刷新不阻塞请求，过期兜底
 * Token/Session    30s~10m     主动淘汰+expire             变更时主动淘汰，过期兜底
 * 配置信息         30m~24h     refresh(30m)+expire(24h)    异步刷新不阻塞请求，过期兜底
 * Redis 降级         24h+            expire
 * </pre>
 *
 * <p>Caffeine + Redis 二级缓存：
 * <pre>
 * 1 自定义 CacheManager
 * 2 Redisson
 * </pre>
 *
 * @author ljh
 * @since 2021/11/2 17:41
 */
public class CaffeineDemo {

    private final static String KEY = "A";

    /** 填充缓存-手动填充 */
    @Test
    public void testManualPopulating() {
        Cache<String, DataObject> cache = Caffeine.newBuilder()
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .maximumSize(100)
            .build();

        DataObject dataObject = cache.getIfPresent(KEY);
        assertNull(dataObject);

        dataObject = cache.get(KEY, k -> DataObject.get("Data for A"));
        assertNotNull(dataObject);
        assertEquals("Data for A", dataObject.data());
    }

    /** 填充缓存-同步加载 */
    @Test
    public void testSynchronousLoading() {
        LoadingCache<String, DataObject> cache = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .build(k -> DataObject.get("Data for " + k));

        DataObject dataObject = cache.get(KEY);
        assertNotNull(dataObject);
        assertEquals("Data for " + KEY, dataObject.data());

        Map<String, DataObject> dataObjectMap = cache.getAll(List.of("A", "B", "C"));
        assertEquals(3, dataObjectMap.size());
    }

    /** 填充缓存-异步加载 */
    @Test
    public void testAsynchronousLoading() {
        AsyncLoadingCache<String, DataObject> cache = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .buildAsync(k -> DataObject.get("Data for " + k));

        cache.get(KEY).thenAccept(dataObject -> {
            assertNotNull(dataObject);
            assertEquals("Data for " + KEY, dataObject.data());
        });

        cache.getAll(List.of("A", "B", "C")).thenAccept(dataObjectMap -> assertEquals(3, dataObjectMap.size()));
    }

    /** 值驱逐-基于大小 */
    @Test
    public void testSizeBased() {
        LoadingCache<String, DataObject> cache = Caffeine.newBuilder()
            .maximumSize(1)
            .build(k -> DataObject.get("Data for " + k));

        assertEquals(0, cache.estimatedSize());

        cache.get("A");
        assertEquals(1, cache.estimatedSize());

        cache.get("B");
        cache.cleanUp();
        assertEquals(1, cache.estimatedSize());


        cache = Caffeine.newBuilder()
            .maximumWeight(4)
            .weigher((k, v) -> 2)
            .build(k -> DataObject.get("Data for " + k));

        assertEquals(0, cache.estimatedSize());

        cache.get("A");
        assertEquals(1, cache.estimatedSize());

        cache.get("B");
        cache.cleanUp();
        assertEquals(2, cache.estimatedSize());

        cache.get("C");
        cache.cleanUp();
        assertEquals(2, cache.estimatedSize());
    }

    /** 值驱逐-基于时间 */
    @Test
    public void testTimeBased() {
        LoadingCache<String, DataObject> cache = Caffeine.newBuilder()
            // 1. 访问后到期
            .expireAfterAccess(5, TimeUnit.MINUTES)
            .build(k -> DataObject.get("Data for " + k));

        cache = Caffeine.newBuilder()
            // 2. 写入后到期
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .weakKeys()
            .weakValues()
            .build(k -> DataObject.get("Data for " + k));

        cache = Caffeine.newBuilder()
            // 3. 自定义策略
            .expireAfter(new Expiry<String, DataObject>() {
                @Override
                public long expireAfterCreate(@NonNull String key, @NonNull DataObject value, long currentTime) {
                    return value.data().length() * DateUtils.MILLIS_PER_SECOND;
                }

                @Override
                public long expireAfterUpdate(@NonNull String key, @NonNull DataObject value, long currentTime, long currentDuration) {
                    return currentDuration;
                }

                @Override
                public long expireAfterRead(@NonNull String key, @NonNull DataObject value, long currentTime, long currentDuration) {
                    return currentDuration;
                }
            }).build(k -> DataObject.get("Data for " + k));
    }

    /** 值驱逐-基于引用 */
    @Test
    public void testReferenceBased() {
        LoadingCache<String, DataObject> cache = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .weakKeys()
            .weakValues()
            .build(k -> DataObject.get("Data for " + k));

        cache = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .softValues()
            .build(k -> DataObject.get("Data for " + k));
    }

    /** 刷新 */
    @Test
    public void testRefreshing() {
        LoadingCache<String, DataObject> cache = Caffeine.newBuilder()
            .refreshAfterWrite(1, TimeUnit.MINUTES)
            .build(k -> DataObject.get("Data for " + k));
    }

    /** 统计 */
    @Test
    public void testStatistics() {
        LoadingCache<String, DataObject> cache = Caffeine.newBuilder()
            .maximumSize(100)
            // 开启统计，方便调优
            .recordStats()
            .build(k -> DataObject.get("Data for " + k));
        cache.get("A");
        cache.get("A");
        cache.get("A");

        assertEquals(2, cache.stats().hitCount());
        assertEquals(1, cache.stats().missCount());
    }

    record DataObject(String data) {
        private static int objectCounter = 0;

        public static DataObject get(String data) {
            objectCounter++;
            return new DataObject(data);
        }
    }
}
