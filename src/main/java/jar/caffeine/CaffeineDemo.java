package jar.caffeine;

import com.github.benmanes.caffeine.cache.*;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <a href="https://www.baeldung.com/java-caching-caffeine">Caffeine</a>
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
        Assertions.assertNull(dataObject);

        dataObject = cache.get(KEY, k -> DataObject.get("Data for A"));
        Assertions.assertNotNull(dataObject);
        Assertions.assertEquals("Data for A", dataObject.data());
    }

    /** 填充缓存-同步加载 */
    @Test
    public void testSynchronousLoading() {
        LoadingCache<String, DataObject> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build(k -> DataObject.get("Data for " + k));

        DataObject dataObject = cache.get(KEY);
        Assertions.assertNotNull(dataObject);
        Assertions.assertEquals("Data for " + KEY, dataObject.data());

        Map<String, DataObject> dataObjectMap = cache.getAll(List.of("A", "B", "C"));
        Assertions.assertEquals(3, dataObjectMap.size());
    }

    /** 值驱逐-基于大小 */
    @Test
    public void testSizeBased() {
        LoadingCache<String, DataObject> cache = Caffeine.newBuilder()
            .maximumSize(1)
            .build(k -> DataObject.get("Data for " + k));

        Assertions.assertEquals(0, cache.estimatedSize());

        cache.get("A");
        Assertions.assertEquals(1, cache.estimatedSize());

        cache.get("B");
        cache.cleanUp();
        Assertions.assertEquals(1, cache.estimatedSize());


        cache = Caffeine.newBuilder()
            .maximumWeight(4)
            .weigher((k, v) -> 2)
            .build(k -> DataObject.get("Data for " + k));

        Assertions.assertEquals(0, cache.estimatedSize());

        cache.get("A");
        Assertions.assertEquals(1, cache.estimatedSize());

        cache.get("B");
        cache.cleanUp();
        Assertions.assertEquals(2, cache.estimatedSize());

        cache.get("C");
        cache.cleanUp();
        Assertions.assertEquals(2, cache.estimatedSize());
    }

    /** 填充缓存-异步加载 */
    @Test
    public void testAsynchronousLoading() {
        AsyncLoadingCache<String, DataObject> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .buildAsync(k -> DataObject.get("Data for " + k));

        cache.get(KEY).thenAccept(dataObject -> {
            Assertions.assertNotNull(dataObject);
            Assertions.assertEquals("Data for " + KEY, dataObject.data());
        });

        cache.getAll(List.of("A", "B", "C")).thenAccept(dataObjectMap -> Assertions.assertEquals(3, dataObjectMap.size()));
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
                    public long expireAfterCreate(String key, DataObject value, long currentTime) {
                        return value.data().length() * DateUtils.MILLIS_PER_SECOND;
                    }

                    @Override
                    public long expireAfterUpdate(String key, DataObject value, long currentTime, long currentDuration) {
                        return currentDuration;
                    }

                    @Override
                    public long expireAfterRead(String key, DataObject value, long currentTime, long currentDuration) {
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

    /** 统计 */
    @Test
    public void testRefreshing() {
        LoadingCache<String, DataObject> cache = Caffeine.newBuilder()
                .refreshAfterWrite(1, TimeUnit.MINUTES)
                .build(k -> DataObject.get("Data for " + k));
    }

    /** 刷新 */
    @Test
    public void testStatistics() {
        LoadingCache<String, DataObject> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .recordStats()
                .build(k -> DataObject.get("Data for " + k));
        cache.get("A");
        cache.get("A");
        cache.get("A");

        Assertions.assertEquals(2, cache.stats().hitCount());
        Assertions.assertEquals(1, cache.stats().missCount());
    }
}
