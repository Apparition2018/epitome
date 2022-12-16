package jar.hutool;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.file.LFUFileCache;
import cn.hutool.cache.impl.WeakCache;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * CacheUtil    缓存工具
 * <p>
 * static <K,V> NoCache<K,V>	    newNoCache(int capacity)                        无缓存
 * static <K,V> FIFOCache<K,V>	    newFIFOCache(int capacity[, long timeout])      FIFO (first in first out) 先进先出缓存
 * static <K,V> LFUCache<K,V>	    newLFUCache(int capacity[, long timeout])       LFU (least frequently used) 最少使用率缓存
 * static <K,V> LRUCache<K,V>	    newLRUCache(int capacity[, long timeout])       LRU (least recently used) 最近最久未使用缓存
 * static <K,V> TimedCache<K,V>	    newTimedCache(int capacity)                     定时缓存
 * static <K,V> WeakCache<K,V>	    newWeakCache(int capacity)                      弱引用缓存
 * <p>
 * https://hutool.cn/docs/#/cache/CacheUtil
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/cache/CacheUtil.html
 *
 * @author ljh
 * @since 2020/11/20 1:46
 */
public class CacheUtilDemo extends Demo {

    @Test
    public void testWeakCache() {
        WeakCache<String, String> weakCache = CacheUtil.newWeakCache(TimeUnit.SECONDS.toMillis(3));
    }

    /**
     * https://apidoc.gitee.com/dromara/hutool/cn/hutool/cache/file/LFUFileCache.html
     */
    @Test
    public void testFileCache() {
        //参数1：容量，能容纳的 byte 数
        //参数2：最大文件大小，byte 数，决定能缓存至少多少文件，大于这个值不被缓存直接读取
        //参数3：超时毫秒
        LFUFileCache cache = new LFUFileCache(1000, 500, TimeUnit.SECONDS.toMillis(2));
        // 获得缓存过的文件 bytes
        byte[] bytes = cache.getFileBytes(DEMO_PATH);
    }
}
