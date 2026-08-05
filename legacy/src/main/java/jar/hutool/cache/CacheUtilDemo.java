package jar.hutool.cache;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.file.LFUFileCache;
import cn.hutool.cache.impl.WeakCache;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static l.demo.Demo.DEMO_DIR_PATH;

/**
 * <a href="https://doc.hutool.cn/pages/CacheUtil/">CacheUtil</a>   缓存工具
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/cache/CacheUtil.html">CacheUtil api</a>
 *
 * @author ljh
 * @since 2020/11/20 1:46
 */
public class CacheUtilDemo {

    @Test
    public void testWeakCache() {
        WeakCache<String, String> weakCache = CacheUtil.newWeakCache(TimeUnit.SECONDS.toMillis(3));
    }

    /** <a href="https://plus.hutool.cn/apidocs/cn/hutool/cache/file/LFUFileCache.html">LFUFileCache api</a> */
    @Test
    public void testFileCache() {
        // 参数1：容量，能容纳的 byte 数
        // 参数2：最大文件大小，byte 数，决定能缓存至少多少文件，大于这个值不被缓存直接读取
        // 参数3：超时毫秒
        LFUFileCache cache = new LFUFileCache(1000, 500, TimeUnit.SECONDS.toMillis(2));
        // 获得缓存过的文件 bytes
        byte[] bytes = cache.getFileBytes(DEMO_DIR_PATH);
    }
}
